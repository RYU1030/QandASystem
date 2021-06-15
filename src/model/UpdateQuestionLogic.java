package model;

import java.sql.SQLException;

import dao.QuestionsDAO;

/**
 * UpdateQuestionLogicは、QuestionsDAOで定義されたupdateメソッドを利用して、質問更新処理を行います。
 */

public class UpdateQuestionLogic  {
	public void update (Question question) throws SQLException, ClassNotFoundException {
		QuestionsDAO dao = new QuestionsDAO();
		dao.update(question);
	}
}
