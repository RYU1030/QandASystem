package model;

import java.io.Serializable;

/**
 * Questionは、データ挿入時に必要となるQuestionのカラムを定義するクラスです。
 * @param handle_name 質問者ハンドルネーム
 * @param title 質問タイトル
 * @param contents 質問内容
 * @param urgency 緊急度
 * @param edit_delete_key 編集・削除キー
 */

public class Question implements Serializable {
	private String handle_name;
	private String title;
	private String contents;
	private int urgency;
	private String edit_delete_key;

	public Question(){}
	  public Question (String handle_name, String title, String contents, int urgency, String edit_delete_key) {
	    this.handle_name = handle_name;
	    this.title = title;
	    this.contents = contents;
	    this.urgency = urgency;
	    this.edit_delete_key = edit_delete_key;
	  }

	  public String getHandleName() {
	    return this.handle_name;
	  }
	  public String getTitle() {
	    return this.title;
	  }
	  public String getContents() {
	    return this.contents;
	  }
	  public int getUrgency() {
	    return this.urgency;
	  }
	  public String getEditDeleteKey() {
	    return this.edit_delete_key;
	  }
}
