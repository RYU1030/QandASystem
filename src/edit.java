

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Question;
import model.VerifyKeyGetQuestionLogic;

/**
 * Servlet implementation class edit
 * @author Ryunosuke Fukuda
 * @version 1.0
 */
@WebServlet("/edit")
public class edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * フォワード先（/WEB-INF/JSP/QandAEdit.jsp）
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAEdit.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// フォーム送信によってpostされた値を変数に格納
		int questionId = Integer.parseInt(request.getParameter("question_id"));
		String editDeleteKey = request.getParameter("edit-delete-key");

		if (questionId > 0 && editDeleteKey != "") {
			VerifyKeyGetQuestionLogic VerifyKeyGetQuestionLogic = new VerifyKeyGetQuestionLogic();
			try {
				Question question = VerifyKeyGetQuestionLogic.execute(questionId);
				String verifyKey = question.getEditDeleteKey();

				if (editDeleteKey.equals(verifyKey)) {
					request.setAttribute("question", question);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAEdit.jsp");

					System.out.println(question);
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("/QandASystem/confirm?questionId=" + questionId + "&errorId=" + 1);
				}
			} catch (Exception e) {
				// 予期せぬエラーをキャッチした時も同様に、エラー画面に遷移させ処理を終わらせる
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAError.jsp");
				dispatcher.forward(request, response);
				return;
			}
		} else {
			response.sendRedirect("/QandASystem/confirm?questionId=" + questionId + "&errorId=" + 2);
		}
	}
}
