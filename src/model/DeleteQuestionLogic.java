package model;

import java.sql.SQLException;

import dao.QuestionsDAO;

/**
 * DeleteQuestionLogicは、QuestionsDAOで定義されているdelete()メソッドを使用して、質問情報の削除を行うクラスです
 * @param questionId 削除対象となる質問情報の「question_id」
 */

public class DeleteQuestionLogic {
	public void execute (int questionId) throws SQLException, ClassNotFoundException  {
		QuestionsDAO dao = new QuestionsDAO();
		dao.delete(questionId);
	}
}
