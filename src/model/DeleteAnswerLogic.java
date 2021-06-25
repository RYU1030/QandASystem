package model;

import java.sql.SQLException;

import dao.AnswersDAO;

/**
 * DeleteAnswerLogicは、AnswersDAOで定義されているdelete()メソッドを使用して、回答情報の削除を行うクラスです
 * @param questionId 削除対象となる回答情報の「question_id」
 */

public class DeleteAnswerLogic {
	public void execute (int questionId) throws SQLException, ClassNotFoundException  {
		AnswersDAO dao = new AnswersDAO();
		dao.delete(questionId);
	}
}
