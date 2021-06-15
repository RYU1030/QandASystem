

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
		Question question = (Question) request.getAttribute("question");
		if (question != null) {
			dispatcher.forward(request, response);
		} else {
			System.out.println("/editへの直接アクセスは受け付けない。");
			response.sendRedirect("list");
			return;
		}
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
				// POSTされた「編集・削除キー」がDBの値と一致した場合は、取得した質問情報をJSPに渡し、編集画面に遷移する
				if (editDeleteKey.equals(verifyKey)) {
					request.setAttribute("question", question);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAEdit.jsp");
					dispatcher.forward(request, response);
				} else {
					// 入力された「編集・削除キー」と、DBの値が一致しない場合は、errorIdを設定し、元の画面にリダイレクト
					response.sendRedirect("/QandASystem/confirm?questionId=" + questionId + "&errorId=" + 1);
				}
			} catch (Exception e) {
				// 予期せぬエラーをキャッチした時も同様に、エラー画面に遷移させ処理を終わらせる
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAError.jsp");
				dispatcher.forward(request, response);
				return;
			}
		} else {
			// 「編集・削除キー」を未入力でPOSTした際は、DBへの問い合わせを行わず、質問確認画面へリダイレクトさせる
			response.sendRedirect("/QandASystem/confirm?questionId=" + questionId + "&errorId=" + 2);
		}
	}
}
