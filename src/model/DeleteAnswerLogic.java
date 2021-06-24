package model;

import java.sql.SQLException;

import dao.AnswersDAO;

public class DeleteAnswerLogic {
	public void execute (int questionId) throws SQLException, ClassNotFoundException  {
		AnswersDAO dao = new AnswersDAO();
		dao.delete(questionId);
	}
}
