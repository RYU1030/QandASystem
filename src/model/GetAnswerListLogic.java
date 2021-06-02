package model;

import java.sql.SQLException;
import java.util.List;

import dao.AnswersDAO;

public class GetAnswerListLogic {
	public List<Answer> execute(int questionId) throws SQLException, ClassNotFoundException {
		AnswersDAO dao = new AnswersDAO();
		List<Answer> answerList = dao.findAll(questionId);
		return answerList;
	}
}
