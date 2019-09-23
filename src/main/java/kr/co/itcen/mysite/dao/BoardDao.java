package kr.co.itcen.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kr.co.itcen.mysite.db.DBStart;
import kr.co.itcen.mysite.vo.BoardVo;

public class BoardDao {
	public Boolean insert(BoardVo vo, Boolean flag) {
		Boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBStart.getConnection();

			if(!flag) {
				String sql = "insert into board values (null, ?, ?, ?, now(), (select ifnull(max(g_no+1), 1) from board as b) , ?, ?, ?, 1)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setInt(3, vo.getHit());
				pstmt.setInt(4, vo.getoNo());
				pstmt.setInt(5, vo.getDepth());
				pstmt.setLong(6, vo.getUserNo());
			} else {
				String sql = "insert into board values (null, ?, ?, ?, now(), ?, ?, ?, ?, 1)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setInt(3, vo.getHit());
				pstmt.setInt(4, vo.getgNo());
				pstmt.setInt(5, vo.getoNo());
				pstmt.setInt(6, vo.getDepth());
				pstmt.setLong(7, vo.getUserNo());
			}
			

			int count = pstmt.executeUpdate();
			result = (count == 1);

			stmt = connection.createStatement();
			rs = stmt.executeQuery("select last_insert_id()");
			if (rs.next()) {
				Long no = rs.getLong(1);
				vo.setNo(no);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> getList(String keyword) {
		List<BoardVo> result = new ArrayList<BoardVo>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String kwd = "%" + keyword + "%";
		try {
			connection = DBStart.getConnection();

			String sql = "   select b.no, b.title, u.name, b.hit, date_format(b.reg_date, '%Y-%m-%d %h:%i:%s'), b.depth" +
					     "     from board b, user u" + 
					     "    where b.user_no = u.no " + 
					     "      and b.status = 1" +
					     "      and (b.title like ? or b.contents like ?)" +
					     " order by g_no desc, o_no";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, kwd);	
			pstmt.setString(2, kwd);	
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				int depth = rs.getInt(6);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserName(name);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setDepth(depth);
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/* view.jsp를 위한 getList overload */
	public BoardVo getList(Long no) {
		BoardVo result = new BoardVo();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = DBStart.getConnection();

			String sql = "   select no, title, contents, g_no, o_no, depth, hit" + 
						 "     from board " + 
						 "    where no = ?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				String title = rs.getString(2);
				String content = rs.getString(3);
				int gNo = rs.getInt(4);
				int oNo = rs.getInt(5);
				int depth = rs.getInt(6);
				int hit = rs.getInt(7);
				
				result.setNo(no);
				result.setTitle(title);
				result.setContents(content);
				result.setgNo(gNo);
				result.setoNo(oNo);
				result.setDepth(depth);
				result.setHit(hit);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public void delete(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = DBStart.getConnection();

			String sql = " update board" + "    set status = 2" + "  where no = ?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Boolean update(BoardVo vo) {
		Boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBStart.getConnection();

			String sql = "update board set title=?, contents=? where no = ?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
			int count = pstmt.executeUpdate();
			result = (count == 1);

			stmt = connection.createStatement();
			rs = stmt.executeQuery("select last_insert_id()");
			if (rs.next()) {
				Long no = rs.getLong(1);
				vo.setNo(no);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	public Boolean replyUpdate(BoardVo vo) {
		Boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBStart.getConnection();

			String sql = "update board " +
					 	 "   set o_no = o_no+1 " +
					 	 " where g_no = ?" +
					 	 "   and o_no >= ?";
					 	 
					 	 

			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, vo.getgNo());
			pstmt.setInt(2, vo.getoNo());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);

			stmt = connection.createStatement();
			rs = stmt.executeQuery("select last_insert_id()");
			if (rs.next()) {
				Long no = rs.getLong(1);
				vo.setNo(no);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	public Boolean hitUpdate(BoardVo vo) {
		Boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = DBStart.getConnection();

			String sql = "update board " +
					 	 "   set hit = hit+1 " +
					 	 " where no = ?";
					 	 
					 	 

			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			
			
			int count = pstmt.executeUpdate();
			result = (count == 1);

			stmt = connection.createStatement();
			rs = stmt.executeQuery("select last_insert_id()");
			if (rs.next()) {
				Long no = rs.getLong(1);
				vo.setNo(no);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
