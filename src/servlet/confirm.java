package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Answer;
import model.GetAnswerListLogic;
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
		// 変数questionIdを初期化
		int questionId = 0;
		try {
			// URLパラメータからquestionIdを取得する
			questionId = Integer.parseInt(request.getParameter("questionId"));
		} catch (NumberFormatException e) {
			response.sendRedirect("list");
			return;
		}

		GetAnswerListLogic getAnswerListLogic = new GetAnswerListLogic();
		try {
			// questionIdを引数に、回答リストを取得してリクエストスコープに保存
			List<Answer> answerList = getAnswerListLogic.execute(questionId);
			request.setAttribute("answerList", answerList);
		} catch (SQLException | ClassNotFoundException e) {
			// DB関連のエラーをキャッチした時は、エラー画面に遷移させ処理を終わらせる
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAError.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			// 予期せぬエラーをキャッチした時も同様に、エラー画面に遷移させ処理を終わらせる
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAError.jsp");
			dispatcher.forward(request, response);
			return;
		}

		SelectQuestionLogic SelectQuestionLogic = new SelectQuestionLogic();
		try {
			// questionIdを引数に、該当の質問を取得する
			Question question = SelectQuestionLogic.execute(questionId);
			// 取得した質問の件数が1以上であれば、questionをJSPに渡す
			if (question.getQuestionId() > 0) {
				request.setAttribute("question", question);
			} else {
				// 指定されたquestionIdに該当する質問が存在しない場合は、/listにリダイレクト
				response.sendRedirect("list");
				return;
			}
		} catch (SQLException | ClassNotFoundException e) {
			// DB関連のエラーをキャッチした時は、エラー画面に遷移させ処理を終わらせる
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAError.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			// 予期せぬエラーをキャッチした時も同様に、エラー画面に遷移させ処理を終わらせる
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAError.jsp");
			dispatcher.forward(request, response);
			return;
		}
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
