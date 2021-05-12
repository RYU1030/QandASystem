package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Questionは、データ挿入時に必要となるQuestionのカラムを定義するクラスです。
 * @param handle_name 質問者ハンドルネーム
 * @param title 質問タイトル
 * @param contents 質問内容
 * @param urgency 緊急度
 * @param edit_delete_key 編集・削除キー
 */

public class Question implements Serializable {
	private int question_id;
	private String handle_name;
	private String title;
	private String contents;
	private int urgency;
	private String urgency_message;
	private String edit_delete_key;
	private Date registered_date;
	private Date updated_date;

	public Question(){}
	  public Question (String handle_name, String title, String contents, int urgency, String edit_delete_key) {
	    this.handle_name = handle_name;
	    this.title = title;
	    this.contents = contents;
	    this.urgency = urgency;
	    this.edit_delete_key = edit_delete_key;
	  }
	  public Question (int question_id, String handle_name, String title, String contents, String urgency_message, String edit_delete_key, Date registered_date, Date updated_date) {
		  this.question_id = question_id;
		  this.handle_name = handle_name;
		  this.title = title;
		  this.contents = contents;
		  this.urgency_message = urgency_message;
		  this.edit_delete_key = edit_delete_key;
		  this.registered_date = registered_date;
		  this.updated_date = updated_date;
	  }

	  public int getQuestion_id() { return this.question_id; }
	  public void setQuestion_id(int question_id) {this.question_id = question_id; }

	  public String getHandle_name() { return this.handle_name; }
	  public void setHandle_name(String handle_name) { this.handle_name = handle_name; }

	  public String getTitle() { return this.title; }
	  public void setTitle(String title) { this.title = title; }

	  public String getContents() { return this.contents; }
	  public void setContents(String contents) { this.contents = contents; }

	  public int getUrgency() { return this.urgency; }
	  public void setUrgency(int urgency) { this.urgency = urgency; }

	  public String getUrgency_message() {return this.urgency_message; }
	  public void setUrgency_message(String urgency_message) {this.urgency_message = urgency_message; }

	  public String getEdit_delete_key() { return this.edit_delete_key; }
	  public void setEdit_delete_key(String edit_delete_key) { this.edit_delete_key = edit_delete_key; }

	  public Date getRegistered_date() { return this.registered_date; }
	  public void setRegistered_date(Date registered_date) { this.registered_date = registered_date; }

	  public Date getUpdated_date() { return this.updated_date; }
	  public void setUpdated_date(Date updated_date) { this.updated_date = updated_date; }
}
