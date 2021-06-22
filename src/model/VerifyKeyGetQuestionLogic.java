package model;

import java.sql.SQLException;

import dao.QuestionsDAO;

public class VerifyKeyGetQuestionLogic {
	public Question execute(int questionId) throws SQLException, ClassNotFoundException {
		Question question = new Question();
		QuestionsDAO dao = new QuestionsDAO();
		question = dao.questionEdit(questionId);
		return question;
	}
}
