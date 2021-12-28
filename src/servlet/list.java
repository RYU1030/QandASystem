package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GetQuestionListLogic;
import model.Question;

/**
 * listは、(/list)へのHttpリクエストを(/WEB-INF/JSP/QandAList.jsp)にフォワードさせるためのサーブレットクラスです。
 * @author 福田龍之介
 */

@WebServlet("/list")
public class list extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public list() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * フォワード先（/WEB-INF/JSP/QandAList.jsp）
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 質問リストを取得して、リクエストスコープに保存
		GetQuestionListLogic getQuestionListLogic = new GetQuestionListLogic();
		List<Question> questionList = getQuestionListLogic.execute();
		request.setAttribute("questionList", questionList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/QandAList.jsp");
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
