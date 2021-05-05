
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostQuestionLogic;
import model.Question;

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
		request.setCharacterEncoding("UTF-8");

		String questionerName = request.getParameter("user-input-questioner-name");
		String questionTitle = request.getParameter("user-input-question-title");
		String questionContent = request.getParameter("user-input-question-content");
		int questionUrgency = Integer.parseInt(request.getParameter("user-input-question-urgency"));
		String EditDeleteKey = request.getParameter("user-input-register-cancel-key");

		if (questionerName != "" && questionTitle != "" && questionContent != "" && questionUrgency != 0 ) {
			Question question = new Question(questionerName, questionTitle, questionContent, questionUrgency, EditDeleteKey);
			PostQuestionLogic postQuestionLogic = new PostQuestionLogic();
			postQuestionLogic.excecute(question);

			response.sendRedirect("/QandASystem/list");

		} else {
			request.setAttribute("errorMsg", "必須項目のいずれか（名前/タイトル/内容/緊急度）が未入力/未選択です。");
			request.setAttribute("questionerName", questionerName);
			request.setAttribute("questionTitle", questionTitle);
			request.setAttribute("questionContent", questionContent);
			request.setAttribute("EditDeleteKey", EditDeleteKey);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandARegist.jsp");
			dispatcher.forward(request, response);
		}
	}
}
