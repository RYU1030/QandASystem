package model;

import java.sql.SQLException;

import dao.QuestionsDAO;

public class DeleteQuestionLogic {
	public void execute (int questionId) throws SQLException, ClassNotFoundException  {
		QuestionsDAO dao = new QuestionsDAO();
		dao.delete(questionId);
	}
}
