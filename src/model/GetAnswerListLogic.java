package model;

import java.util.List;

import dao.AnswersDAO;

public class GetAnswerListLogic {
	public List<Answer> execute(int questionId) {
		AnswersDAO dao = new AnswersDAO();
		List<Answer> answerList = dao.findAll(questionId);
		return answerList;
	}
}
