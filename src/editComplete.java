

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Question;
import model.UpdateQuestionLogic;
import sandbox.Hashing;

/**
 * Servlet implementation class editComplete
 */
@WebServlet("/editComplete")
public class editComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public editComplete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");

		int questionId = Integer.parseInt(request.getParameter("questionId"));
		String questionerName = request.getParameter("questioner-name");
		String questionTitle = request.getParameter("question-title");
		String questionContent = request.getParameter("question-content");
		// 緊急度はデフォルトで0をセットする
		int questionUrgency = 0;
		if (request.getParameter("urgency") != null) {
			questionUrgency = Integer.parseInt(request.getParameter("urgency"));
		}
		String EditDeleteKey = request.getParameter("edit-delete-key");

		if (questionerName != "" && questionTitle != "" && questionContent != "" && questionUrgency != 0 && EditDeleteKey != "") {
			try {
				Hashing Hashing = new Hashing();
				EditDeleteKey = Hashing.hash(EditDeleteKey);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAError.jsp");
				dispatcher.forward(request, response);
	    	return;
			}
			Question question = new Question(questionId, questionerName, questionTitle, questionContent, questionUrgency, EditDeleteKey);
			UpdateQuestionLogic UpdateQuestionLogic = new UpdateQuestionLogic();
			try {
				UpdateQuestionLogic.update(question);
			} catch (SQLException | ClassNotFoundException e) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAError.jsp");
				dispatcher.forward(request, response);
				return;
			} catch (Exception e) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAError.jsp");
				dispatcher.forward(request, response);
			}
			response.sendRedirect("/QandASystem/confirm?questionId=" + questionId);
		} else {
			// 必須項目一つでも未入力の場合は、エラーメッセージを定義の上、質問登録画面にフォワードする。
			request.setAttribute("errorMsg", "必須項目のいずれか（名前/タイトル/内容/緊急度）が未入力/未選択です。");
			Question question = new Question(questionId, questionerName, questionTitle, questionContent, questionUrgency, EditDeleteKey);
			request.setAttribute("question", question);
			request.setAttribute("errorMsg", "更新できませんでした。全項目入力の上、再度更新ボタンを押してください。");
			// response.sendRedirect("/QandASystem/edit");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAEdit.jsp");
			dispatcher.forward(request, response);
		}
	}
}
