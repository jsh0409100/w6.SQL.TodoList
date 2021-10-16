package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
    private String title;
    private String desc;
    private String category;
    private String current_date;
    private String due_date;
    private int complete;
    private int importance;
    private int updated;


	public TodoItem(int id, String title, String desc, String category, String due_date, int complete, int importance, int updated){
        this.id = id;
		this.title=title;
        this.desc=desc;
        this.category = category;
        this.due_date = due_date;
        this.complete = complete;
        this.importance = importance;
        this.updated = updated;
    }
  
    
    public TodoItem(String title, String desc, String category, String due_date, int importance){
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yy/MM/dd");
        this.current_date=f.format(new Date());
        this.category = category;
        this.due_date = due_date;
        this.importance = importance;
    }
   
    
    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
	public void setId(int id) {
		this.id = id;
	}
	
    public int getId() {
		return id;
	}
    public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public String getComplete() {
		if (complete == 1)
				return "O";
		else return "X";
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id + "." + "[" + getComplete() + "]" + "[" + this.category + "]" + this.getTitle() + "\t설명: " + this.getDesc() + "\t마감일: " + this.due_date + "\t등록일: " + this.getCurrent_date() + "\t중요도: " + this.getImportance() + "\t수정횟수: " + this.getUpdated();
	}
	
	public String toSaveString() {
		return category + "##" + title + "##" + desc + "##" + due_date + "##" + current_date + "\n";
	}
}
