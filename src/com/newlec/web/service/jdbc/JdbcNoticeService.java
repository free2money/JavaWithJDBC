package com.newlec.web.service.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newlec.web.entity.Notice;
import com.newlec.web.service.NoticeService;

public class JdbcNoticeService implements NoticeService {

	@Override
	public List<Notice> getNoticeList() {
		return getNoticeList(1, "title", "");
	}

	@Override
	public List<Notice> getNoticeList(int page) {
		return getNoticeList(page, "title", "");
	}

	@Override
	public List<Notice> getNoticeList(String field, String query) {
		return getNoticeList(1, "field", "query");
	}

	@Override
	public List<Notice> getNoticeList(int page, String field, String query) {
		List<Notice> list = new ArrayList<Notice>();
		String sql = "select * \r\n" /**/
				+ "from notice \r\n" /**/
				+ "where " + field + " like ? \r\n"/**/
				+ " order by regdate desc\r\n"/**/
				+ " offset ? rows fetch next ? rows only";

		try {
			PreparedStatement st = JdbcContext.getPreparedStatement(sql);
			st.setString(1, "%" + query + "%");
			st.setInt(2, (page - 1) * 10);
			st.setInt(3, page * 10);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regDate = rs.getDate("REGDATE");
				int hit = rs.getInt("HIT");
				String content = rs.getString("CONTENT");

				list.add(new Notice(id, title, content, writerId, regDate, hit));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Notice getNotice(int id) {
		// id ���� ������ rownum���� �ϸ�...?
		String sql = "select * \r\n" /**/
				+ "from notice \r\n" /**/
				+ "where = " + id;

		Notice notice = null;

		try {
			PreparedStatement st = JdbcContext.getPreparedStatement(sql);
			ResultSet rs = st.executeQuery();

			rs.next();
			String title = rs.getString("TITLE");
			String writer_id = rs.getString("WRITER_ID");
			Date regdate = rs.getDate("REGDATE");
			int hit = rs.getInt("HIT");
			String content = rs.getString("CONTENT");

			notice = new Notice(id, title, content, writer_id, regdate, hit);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}

	@Override
	public Notice getNextNotice(int id) {
		// �������� �� �ۺ��� �ֽű�?
		// id�� ������ ���ɼ��� �ִ�.
		// rownum���� ������ �����ϴ� ���� �ٶ����ҵ�.
		//
		String sql = "select * from "/**/
				+ "(select rownum num, n.* "/**/
				+ "from (select * from notice order by regdate desc) n)\r\n"/**/
				+ "where num = " /**/
				+ "(select num from (select rownum num, n.* from "/**/
				+ "(select * from notice order by regdate desc) n) "/**/
				+ "where id = " + id + ") - 1";

		Notice notice = null;

		try {
			PreparedStatement st = JdbcContext.getPreparedStatement(sql);
			ResultSet rs = st.executeQuery();

			st = JdbcContext.getPreparedStatement(sql);
			rs = st.executeQuery();

			rs.next();
			int thisId = rs.getInt("id");
			String title = rs.getString("TITLE");
			String writer_id = rs.getString("WRITER_ID");
			Date regdate = rs.getDate("REGDATE");
			int hit = rs.getInt("HIT");
			String content = rs.getString("CONTENT");

			notice = new Notice(thisId, title, content, writer_id, regdate, hit);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}

	@Override
	public Notice getPreviewNotice(int id) {
		String sql = "select * from "/**/
				+ "(select rownum num, n.* "/**/
				+ "from (select * from notice order by regdate desc) n)\r\n"/**/
				+ "where num = " /**/
				+ "(select num from (select rownum num, n.* from "/**/
				+ "(select * from notice order by regdate desc) n) "/**/
				+ "where id = " + id + ") + 1";

		Notice notice = null;

		try {
			PreparedStatement st = JdbcContext.getPreparedStatement(sql);
			ResultSet rs = st.executeQuery();

			st = JdbcContext.getPreparedStatement(sql);
			rs = st.executeQuery();

			rs.next();
			int thisId = rs.getInt("id");
			String title = rs.getString("TITLE");
			String writer_id = rs.getString("WRITER_ID");
			Date regdate = rs.getDate("REGDATE");
			int hit = rs.getInt("HIT");
			String content = rs.getString("CONTENT");

			notice = new Notice(thisId, title, content, writer_id, regdate, hit);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}

	@Override
	public int deleteNotices(int[] ids) {
		int result = 0;
		for (int i = 0; i < ids.length; i++) {
			String sql = "delete from notice where id = " + ids[i];
			try {
				PreparedStatement st = JdbcContext.getPreparedStatement(sql);
				result = st.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int pubNotice(int[] ids) {
		return 0;
	}

	@Override
	public int regNotice(Notice notice) {
		int result = 0;
		String sql = "insert into notice "/**/
				+ "(id,title, writer_id, content) "/**/
				+ "values(notice_seq.nextval,?,?,?)";

		try {
			PreparedStatement st = JdbcContext.getPreparedStatement(sql);
			result = st.executeUpdate();
			st.setString(2, notice.getTitle());
			st.setString(3, notice.getWriter_id());
			st.setString(4, notice.getContent());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateNotice(Notice notice) {
		// ���� ���� id�� �����ͼ� ������� ��� �ؾ��ҰŰ�����...
		int result = 0;
		String sql = "update notice set title = ?, content = ? where id = ?";
		try {
			PreparedStatement st = JdbcContext.getPreparedStatement(sql);
			result = st.executeUpdate();
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setInt(3, notice.getId());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteNotice(int id) {
		int result = 0;
		String sql = "delete from notice where id = " + id;
		try {
			PreparedStatement st = JdbcContext.getPreparedStatement(sql);
			result = st.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
