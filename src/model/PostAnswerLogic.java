package model;

import dao.AnswersDAO;

/**
 * PostAnswerLogicは、AnswersDAOで定義されたcreateメソッドを利用して、DBへの質問登録処理を行います。
 */

public class PostAnswerLogic {
	public void execute (Answer answer) {
		AnswersDAO dao = new AnswersDAO();
		dao.create(answer);
	}

}
