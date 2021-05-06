package model;

import dao.QuestionsDAO;

/**
 * PostQuestionLogicは、QuestionsDAOで定義されたcreateメソッドを利用して、DBへの質問登録処理を行います。
 */

public class PostQuestionLogic {
	public void execute (Question question) {
		QuestionsDAO dao = new QuestionsDAO();
		dao.create(question);
	}

}
