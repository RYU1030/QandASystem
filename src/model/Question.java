package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Questionは、データ挿入時に必要となるQuestionのカラムを定義するクラスです。
 * @param handleName 質問者ハンドルネーム
 * @param title 質問タイトル
 * @param contents 質問内容
 * @param urgency 緊急度
 * @param editDeleteKey 編集・削除キー
 */

public class Question implements Serializable {
	private int questionId;
	private String handleName;
	private String title;
	private String contents;
	private int urgency;
	private String urgencyMessage;
	private String editDeleteKey;
	private Date registeredDate;
	private Date updatedDate;

	public Question(){}
	  public Question (String handleName, String title, String contents, int urgency, String editDeleteKey) {
	    this.handleName = handleName;
	    this.title = title;
	    this.contents = contents;
	    this.urgency = urgency;
	    this.editDeleteKey = editDeleteKey;
	  }
	  public Question (int questionId, String handleName, String title, String contents, String urgencyMessage, String editDeleteKey, Date registeredDate, Date updatedDate) {
		  this.questionId = questionId;
		  this.handleName = handleName;
		  this.title = title;
		  this.contents = contents;
		  this.urgencyMessage = urgencyMessage;
		  this.editDeleteKey = editDeleteKey;
		  this.registeredDate = registeredDate;
		  this.updatedDate = updatedDate;
	  }

	  public int getQuestionId() { return this.questionId; }
	  public void setQuestionId(int questionId) {this.questionId = questionId; }

	  public String getHandleName() { return this.handleName; }
	  public void setHandleName(String handleName) { this.handleName = handleName; }

	  public String getTitle() { return this.title; }
	  public void setTitle(String title) { this.title = title; }

	  public String getContents() { return this.contents; }
	  public void setContents(String contents) { this.contents = contents; }

	  public int getUrgency() { return this.urgency; }
	  public void setUrgency(int urgency) { this.urgency = urgency; }

	  public String getUrgencyMessage() {return this.urgencyMessage; }
	  public void setUrgencyMessage(String urgencyMessage) {this.urgencyMessage = urgencyMessage; }

	  public String getEditDeleteKey() { return this.editDeleteKey; }
	  public void setEditDeleteKey(String editDeleteKey) { this.editDeleteKey = editDeleteKey; }

	  public Date getRegisteredDate() { return this.registeredDate; }
	  public void setRegisteredDate(Date registeredDate) { this.registeredDate = registeredDate; }

	  public Date getUpdatedDate() { return this.updatedDate; }
	  public void setUpdatedDate(Date updatedDate) { this.updatedDate = updatedDate; }
}
