package model;

import java.io.Serializable;

public class Answer implements Serializable {
	private int questionId;
	private int seq;
	private String handleName;
	private String contents;
	private String registDateTime;

	public Answer(){}
	public Answer(int questionId, int seq, String handleName, String contents, String registDateTime) {
		this.questionId = questionId;
		this.seq = seq;
		this.contents = contents;
		this.registDateTime = registDateTime;
	}

	public int getQuestionId() { return this.questionId; }
	public void setQuestionId(int questionId) { this.questionId = questionId; }

	public int getSeq() { return this.seq; }
	public void setSeq(int seq) { this.seq = seq; }

	public String getHandleName() { return this.handleName; }
	public void setHanldeName(String handleName) { this.handleName = handleName; }

	public String getContents() { return this.contents; }
	public void setContents(String contents) { this.contents = contents; }

	public String getRegistDateTime() { return this.registDateTime; }
	public void setRegistDateTime(String registDateTime) { this.registDateTime = registDateTime; }

}
