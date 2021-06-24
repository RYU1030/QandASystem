

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeleteAnswerLogic;
import model.DeleteQuestionLogic;
import model.Question;
import model.VerifyKeyGetQuestionLogic;
import sandbox.Hashing;

/**
 * Servlet implementation class delete
 */
@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete() {
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
		String editDeleteKey = request.getParameter("edit-delete-key");
		// 「編集・削除キー」がNULLではない場合に限り、DBへの問い合わせを行う
		if (questionId > 0 && editDeleteKey != "") {
			VerifyKeyGetQuestionLogic VerifyKeyGetQuestionLogic = new VerifyKeyGetQuestionLogic();
			try {
				// POSTされたquestionIdをキーに質問情報を取得する
				Question question = VerifyKeyGetQuestionLogic.execute(questionId);
				// 取得した質問情報から「編集・削除キー」を変数に格納する
				String verifyKey = question.getEditDeleteKey();
				try {
					Hashing Hashing = new Hashing();
					editDeleteKey = Hashing.hash(editDeleteKey);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
					return;
				}
				if (editDeleteKey.equals(verifyKey)) {
					try {
						DeleteAnswerLogic DeleteAnswerLogic = new DeleteAnswerLogic();
						DeleteQuestionLogic DeleteQuestionLogic = new DeleteQuestionLogic();
						DeleteAnswerLogic.execute(questionId);
						DeleteQuestionLogic.execute(questionId);
					} catch (Exception e) {
						// 予期せぬエラーをキャッチした時も同様に、エラー画面に遷移させ処理を終わらせる
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAError.jsp");
						dispatcher.forward(request, response);
						return;
					}
					response.sendRedirect("/QandASystem/list");
				} else {
					// 入力された「編集・削除キー」と、DBの値が一致しない場合は、errorIdを設定し、元の画面にリダイレクト
					response.sendRedirect("/QandASystem/confirm?questionId=" + questionId + "&errorId=" + 2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 「編集・削除キー」を未入力でPOSTした際は、DBへの問い合わせを行わず、質問確認画面へリダイレクトさせる
			response.sendRedirect("/QandASystem/confirm?questionId=" + questionId + "&errorId=" + 2);
		}
	}
}
