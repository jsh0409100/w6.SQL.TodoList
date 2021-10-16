package com.todo.dao;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.todo.service.DbConnect;


import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	Connection conn;
	
	public TodoList() {
		this.conn = DbConnect.getConnection();
	}

	public int addItem(TodoItem t) {
		String sql = "insert into List (title, desc, category, current_date, due_date, importance)" + "values (?,?,?,?,?,?);";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			pstmt.setInt(6, t.getImportance());
			count = pstmt.executeUpdate();
			pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return count;
	}

	public int deleteItem(int[] index) {
		String sql = "delete from list where id=? ;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < index.length ; i++) {
				int num = index[i];
				pstmt.setInt(1, num);
				count = pstmt.executeUpdate();
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int getUpdateNum(int id) {
		
		PreparedStatement pstmt;
		int updated = 0;
		
		try {
			String sql = "select updated from list  where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				updated = rs.getInt("updated");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}
	
	
	public int updateItem(TodoItem t) {
		String sql = "update list set title=?, desc=?, category=?, current_date=?, due_date=?, importance=?, updated=?" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			pstmt.setInt(6, t.getImportance());
			pstmt.setInt(7, t.getUpdated());
			pstmt.setInt(8, t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int completeItem(int index) {
		String sql = "update list set complete = 1" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql1 = "select * from list";
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				int id = rs1.getInt("id");
				String category = rs1.getString("category");
				String title = rs1.getString("title");
				String desc = rs1.getString("desc");
				String current_date = rs1.getString("current_date");
				String due_date = rs1.getString("due_date");
				int complete = rs1.getInt("complete");
				int importance = rs1.getInt("importance");
				int updated = rs1.getInt("updated");
				
				TodoItem t = new TodoItem(id, title, desc, category, due_date, complete, importance, updated);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getList(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";
		try {
			String sql = "select * from list where title like ? or desc like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int complete = rs.getInt("complete");
				int importance = rs.getInt("importance");
				int updated = rs.getInt("updated");
				TodoItem t = new TodoItem(id, title, desc, category, due_date, complete, importance, updated);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getList(String column, int num) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		String sql = "select * from list where complete = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, column);
			pstmt.setInt(1, num);
			ResultSet rs1 = pstmt.executeQuery();
			while (rs1.next()) {
				int id = rs1.getInt("id");
				String category = rs1.getString("category");
				String title = rs1.getString("title");
				String desc = rs1.getString("desc");
				String current_date = rs1.getString("current_date");
				String due_date = rs1.getString("due_date");
				int complete = rs1.getInt("complete");
				int importance = rs1.getInt("importance");
				int updated = rs1.getInt("updated");
				
				TodoItem t = new TodoItem(id, title, desc, category, due_date, complete, importance, updated);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "select count(id) from list;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
/*
	public void importData(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String sql = "insert into list (title, description, category, current_date, due_date)" + "values (?,?,?,?,?);";
			int records = 0;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String category = st.nextToken();
				String title  = st.nextToken();
				String desc = st.nextToken();
				String due_date  = st.nextToken();
				String current_date  = st.nextToken();
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, desc);
				pstmt.setString(3, category);
				pstmt.setString(4, current_date);
				pstmt.setString(5, due_date);
				int count = pstmt.executeUpdate();
				if(count>0) records++;
				pstmt.close();
			}
			System.out.println(records +" records read!");
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	public ArrayList<String> getCategories(){
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String category = rs.getString("category");
				list.add(category);
			}
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<TodoItem> getListCategory(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "select * from list WHERE category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int complete = rs.getInt("complete");
				int importance = rs.getInt("importance");
				int updated = rs.getInt("updated");
				TodoItem t = new TodoItem(id, title, desc, category, due_date, complete, importance, updated);
				t.setCurrent_date(current_date);
				list.add(t);
				
			}
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getListCertainDate(String Due){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "select * from list WHERE due_date = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Due);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int complete = rs.getInt("complete");
				int importance = rs.getInt("importance");
				int updated = rs.getInt("updated");
				TodoItem t = new TodoItem(id, title, desc, category, due_date, complete, importance, updated);
				t.setCurrent_date(current_date);
				list.add(t);
				
			}
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getTodayDuedate(String today, int days){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		int today_int = Integer.parseInt(today);
		try {
			String sql = "select * from list WHERE due_date = ?";
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i <= days; i++) {
				int search = today_int + i;
				String search_string = String.valueOf(search);
				pstmt.setString(1, search_string);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					String category = rs.getString("category");
					String title = rs.getString("title");
					String desc = rs.getString("desc");
					String due_date = rs.getString("due_date");
					String current_date = rs.getString("current_date");
					int complete = rs.getInt("complete");
					int importance = rs.getInt("importance");
					int updated = rs.getInt("updated");
					TodoItem t = new TodoItem(id, title, desc, category, due_date, complete, importance, updated);
					t.setCurrent_date(current_date);
					list.add(t);
				}
			}
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "select * from list ORDER BY "+ orderby;
			if (ordering == 0)
				sql+= " desc";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int complete = rs.getInt("complete");
				int importance = rs.getInt("importance");
				int updated = rs.getInt("updated");
				TodoItem t = new TodoItem(id, title, desc, category, due_date, complete, importance, updated);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Boolean isDuplicate(String title) {
		for (TodoItem item : getList()) {
			if (title.equals(item.getTitle())) 
				return true;
		}
		return false;
	}
	
}
