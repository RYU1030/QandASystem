package model;

import java.sql.SQLException;

import dao.AnswersDAO;

/**
 * PostAnswerLogicは、AnswersDAOで定義されたcreateメソッドを利用して、DBへの質問登録処理を行います。
 */

public class PostAnswerLogic {
	public void execute (Answer answer) throws SQLException, ClassNotFoundException  {
		AnswersDAO dao = new AnswersDAO();
		dao.create(answer);
	}

}
