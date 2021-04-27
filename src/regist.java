
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class regist
 * @author Ryunosuke Fukuda
 * @version 1.0
 */
@WebServlet("/regist")
public class regist extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public regist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * フォワード先（/WEB-INF/JSP/QandARegist.jsp）
	 */

	Connection conn = null;
	String url = "jdbc:postgresql://localhost/QandA";
	String user = "ryunosukefukuda";
	String password = "password";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandARegist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		// response.setContentType("text/html;charset=Shift_JIS");
		response.setCharacterEncoding("UTF-8");
		String questionerName = request.getParameter("user-input-questioner-name");
		String questionTitle = request.getParameter("user-input-question-title");
		String questionContent = request.getParameter("user-input-question-content");
		int questionUrgency = Integer.parseInt(request.getParameter("user-input-question-urgency"));

		try {
			// JDBCドライバを読み込む
			Class.forName("org.postgresql.Driver");

			// DBへ接続
			conn = DriverManager.getConnection(url, user, password);

			// INSERT文を用意
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO questions (question_id, handle_name, title, contents, urgency, edit_delete_key, regist_timestamp, update_timestamp) VALUES (nextval('question_id_seq'), ?, ?, ?, ?, ?, Now(), Now())";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, questionerName);
			pstmt.setString(2, questionTitle);
			pstmt.setString(3, questionContent);
			pstmt.setInt(4, questionUrgency);
			if (request.getParameter("user-input-register-cancel-key") != null) {
				String EditDeleteKey = request.getParameter("user-input-register-cancel-key");
				pstmt.setString(5, EditDeleteKey);
			} else {
				pstmt.setString(5, null);
			}

			stmt.executeUpdate(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// DB切断
			if (conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// TODO Auto-generated method stub
		// doGet(request, response);
		// response.sendRedirect("/QandASystem/list");
	}

}
