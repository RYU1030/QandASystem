package model;

import dao.QuestionsDAO;

public class SelectQuestionLogic  {
	public Question execute(int questionId) {
		Question question = new Question();
		QuestionsDAO dao = new QuestionsDAO();
		question = dao.questionConfirm(questionId);
		return question;
	}
}
