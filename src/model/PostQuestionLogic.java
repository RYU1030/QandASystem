package model;

import dao.QuestionsDAO;

public class PostQuestionLogic {
	public void excecute (Question question) {
		QuestionsDAO dao = new QuestionsDAO();
		dao.create(question);
	}

}
