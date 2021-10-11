package com.todo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("\n"
				+ ">>> 새로운 항목 추가\n"
				+ "> 새로운 할일의 카테고리 이름을 입력하세요. ");
		category = sc.next();
		
		System.out.println("\n> 새로운 할일의 제목을 입력하세요. ");
		title = sc.next();
		sc.nextLine();
		if(l.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		
		System.out.println("\n> 할일에 대한 설명을 입력하세요. ");
		desc = sc.nextLine().trim();
		
		System.out.println("\n> 할일의 마감일자를 입력하세요. ");
		due_date = sc.nextLine().trim();
		
		
	    String current_date = null;
		TodoItem t = new TodoItem(title, desc, current_date, category, due_date);
		if(l.addItem(t)>0)
			System.out.println("\n♡ ! 추가 완료 ! ♡");
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);

		System.out.println("\n"
				+ ">>> 항목 삭제\n"
				+ "> 삭제할 할일의 번호를 입력하세요.");
		int index = sc.nextInt();
		
		if(l.deleteItem(index)>0)
			System.out.println("\n♡ ! 삭제 완료 ! ♡");
			
	}
		

	public static void updateItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ ">>> 항목 수정\n"
				+ "> 수정할 할일의 번호를 입력하세요. ");
		int index = sc.nextInt();
		
		System.out.println("\n> 할일의 새로운 카테고리 이름을 입력하세요. ");
		String new_category = sc.next();
		sc.nextLine();
		
		System.out.println("\n> 해당 할일의 새로운 이름을 입력하세요. ");
		String new_title = sc.next().trim();
		
		System.out.println("\n> 해당 할일의 새로운 설명을 입력하세요. ");
		String new_description = sc.nextLine().trim();
		
		System.out.println("\n> 할일의 새로운 마감일자를 입력하세요. ");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
		t.setId(index);
		if(l.updateItem(t)>0)
			System.out.println("\n♡ ! 수정 완료 ! ♡");
	}

	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("♡ ! 총 %d개의 항목을 찾았습니다. ! ♡\n\n", count);
	}

	public static void listCateAll(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count ++;
		}
		System.out.printf("\n♡ ! 총 %d개의 카테고리 ! ♡\n", count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.print(item.toString());
			count ++;
		}
		System.out.printf("\n♡ ! 총 %d개의 항목을 찾았습니다. ! ♡\n", count);
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("\n"
				+ "♡ ! 할일 목록, 총 %d 개 ! ♡\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("\n"
				+ "♡ ! 할일 목록, 총 %d 개 ! ♡\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
}
