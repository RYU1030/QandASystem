package model;

import java.util.List;

import dao.QuestionsDAO;

public class GetQuestionListLogic {
	public List<Question> execute() {
		QuestionsDAO dao = new QuestionsDAO();
		List<Question> questionList = dao.findAll();
		return questionList;
	}
}
