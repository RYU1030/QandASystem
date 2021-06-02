package model;

import java.sql.SQLException;

import dao.QuestionsDAO;

public class SelectQuestionLogic  {
	public Question execute(int questionId) throws SQLException, ClassNotFoundException {
		Question question = new Question();
		QuestionsDAO dao = new QuestionsDAO();
		question = dao.questionConfirm(questionId);
		return question;
	}
}
