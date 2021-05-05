package model;

import java.io.Serializable;

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
