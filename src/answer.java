

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Answer;
import model.PostAnswerLogic;

/**
 * Servlet implementation class answer
 */
@WebServlet("/answer")
public class answer extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public answer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// フォーム送信によってpostされた値を変数に格納
		int questionId = Integer.parseInt(request.getParameter("question_id"));
		String answererName = request.getParameter("answerer-name");
		String answerContent = request.getParameter("answer-content");

		// 必要な情報が全てpostされた場合にのみ、回答登録の処理に移行する
		if (questionId >= 0 && answererName != "" && answerContent != "") {
			// 登録する回答情報をコンストラクトする
			Answer answer = new Answer(questionId, answererName, answerContent);
			PostAnswerLogic postAnswerLogic = new PostAnswerLogic();
			postAnswerLogic.execute(answer);

			// 回答登録完了後、送信前画面にリダイレクトする
			response.sendRedirect(request.getContextPath() + "/confirm?questionId=" + answer.getQuestionId());

		} else {
			// 必須項目一つでも未入力の場合は、エラーメッセージを定義の上、送信前の画面にフォワードする
			request.setAttribute("errorMsgAnswer", "必須項目のいずれか（名前/タイトル）が未入力/未選択です。");
			request.setAttribute("answererName", answererName);
			request.setAttribute("answerContent", answerContent);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/confirm?questionId=" + questionId);
			dispatcher.forward(request, response);
		}
	}

}
