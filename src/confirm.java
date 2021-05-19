

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Question;
import model.SelectQuestionLogic;

/**
 * Servlet implementation class confirm
 * @author Ryunosuke Fukuda
 * @version 1.0
 */
@WebServlet("/confirm")
public class confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * フォワード先（/WEB-INF/JSP/QandAConfirm.jsp）
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int questionId = Integer.parseInt(request.getParameter("questionId"));
		// 質問リストを取得して、リクエストスコープに保存
		SelectQuestionLogic SelectQuestionLogic = new SelectQuestionLogic();
		Question question = SelectQuestionLogic.execute(questionId);
		request.setAttribute("question", question);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAConfirm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
