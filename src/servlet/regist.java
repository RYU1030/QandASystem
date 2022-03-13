package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostQuestionLogic;
import model.Question;
import sandbox.Hashing;

/**
 * registは、/registにアクセスされた際の処理を定義するサーブレットクラスです。
 */
@WebServlet("/regist")
public class regist extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public regist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * フォワード先（/WEB-INF/JSP/QandARegist.jsp）
	 */

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

		String questionerName = request.getParameter("questioner-name");
		String questionTitle = request.getParameter("question-title");
		String questionContent = request.getParameter("question-content");
		// 緊急度はデフォルトで0をセットする
		int questionUrgency = 0;
		if (request.getParameter("urgency") != null) {
			questionUrgency = Integer.parseInt(request.getParameter("urgency"));
		}

		String EditDeleteKey = request.getParameter("register-cancel-key");

		if (questionerName != "" && questionTitle != "" && questionContent != "" && questionUrgency != 0 ) {
			// 編集・削除キーが空でなければ、キーのハッシュ化を行う
			if (EditDeleteKey != "") {
				try {
					Hashing Hashing = new Hashing();
					EditDeleteKey = Hashing.hash(EditDeleteKey);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAError.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}
			Question question = new Question(questionerName, questionTitle, questionContent, questionUrgency, EditDeleteKey);
			PostQuestionLogic postQuestionLogic = new PostQuestionLogic();
			postQuestionLogic.execute(question);

			response.sendRedirect("/QandASystem/list");
		} else {
			// 必須項目一つでも未入力の場合は、エラーメッセージを定義の上、質問登録画面にフォワードする。
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
