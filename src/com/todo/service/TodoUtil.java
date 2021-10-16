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
				+ ">>> ���ο� �׸� �߰�\n"
				+ "> ���ο� ������ ī�װ� �̸��� �Է��ϼ���. ");
		category = sc.next();
		
		System.out.println("\n> ���ο� ������ ������ �Է��ϼ���. ");
		title = sc.next();
		sc.nextLine();
		if(l.isDuplicate(title)) {
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}
		
		System.out.println("\n> ���Ͽ� ���� ������ �Է��ϼ���. ");
		desc = sc.nextLine().trim();
		
		System.out.println("\n> ������ �������ڸ� �Է��ϼ���.(YYMMDD) ");
		due_date = sc.nextLine().trim();
		
		System.out.println("\n> ������ �߿䵵�� �Է��ϼ���.(���� �߿�1~5) ");
		int importance = sc.nextInt();
		
		TodoItem t = new TodoItem(title, desc, category, due_date, importance);
		if(l.addItem(t)>0)
			System.out.println("\n�� ! �߰� �Ϸ� ! ��");
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n"
				+ ">>> �׸� ����\n"
				+ "> ������ ������ ��ȣ�� ����� �����Ͽ� ��� �Է��ϼ���.");
		String num_s = sc.nextLine();
		String[] index = num_s.split(" ");
		int[] indexInt = new int[index.length];
		for (int i = 0; i < index.length; i++) {
				indexInt[i] = Integer.parseInt(index[i]);
	        }
		if(l.deleteItem(indexInt)>0)
			System.out.println("\n�� ! ���� �Ϸ� ! ��");
			
	}
		

	public static void updateItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ ">>> �׸� ����\n"
				+ "> ������ ������ ��ȣ�� �Է��ϼ���. ");
		int index = sc.nextInt();
		
		System.out.println("\n> ������ ���ο� ī�װ� �̸��� �Է��ϼ���. ");
		String new_category = sc.next();
		sc.nextLine();
		
		System.out.println("\n> �ش� ������ ���ο� �̸��� �Է��ϼ���. ");
		String new_title = sc.next().trim();
		sc.nextLine();
		
		System.out.println("\n> �ش� ������ ���ο� ������ �Է��ϼ���. ");
		String new_description = sc.nextLine().trim();
		
		System.out.println("\n> ������ ���ο� �������ڸ� �Է��ϼ���.(YYMMDD) ");
		String new_due_date = sc.nextLine().trim();
		
		System.out.println("\n> ������ ���ο� �߿䵵�� �Է��ϼ���.(���� �߿�1~5) ");
		int new_importance = sc.nextInt();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date, new_importance);
		t.setId(index);
		t.setUpdated(l.getUpdateNum(index) + 1);
		if(l.updateItem(t)>0)
			System.out.println("\n�� ! ���� �Ϸ� ! ��");
	}

	public static void completeItem(TodoList l, String num) {;
		StringTokenizer st = new StringTokenizer(num);
		int i = 0;
		while(st.hasMoreTokens()){
			int index = Integer.parseInt(st.nextToken());

			if(l.completeItem(index)>0)
				System.out.printf("�� ! %d �� �Ϸ� ! ��", index, "\n");
		}
	}
	
	
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count ++;
		}
		System.out.printf("\n�� ! �� %d���� ī�װ� ! ��\n", count);
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("\n"
				+ "�� ! ���� ���, �� %d �� ! ��\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("\n"
				+ "�� ! ���� ���, �� %d �� ! ��\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listCom(TodoList l, String column, int num) {
		int count = 0;
		System.out.println("");
		for (TodoItem item : l.getList(column, num)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� ! �� %d���� �׸��� ã�ҽ��ϴ�. ! ��\n\n", count);
	}

	public static void todayDuedate(TodoList l, int days) {
		SimpleDateFormat f = new SimpleDateFormat("yyMMdd");
	    String current_date=f.format(new Date());
		int count = 0;
		System.out.println("");
		for (TodoItem item : l.getTodayDuedate(current_date, days)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� ! �� %d���� �׸��� ã�ҽ��ϴ�. ! ��\n", count);
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		System.out.println("");
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� ! �� %d���� �׸��� ã�ҽ��ϴ�. ! ��\n", count);
	}

	public static void findCateList(TodoList l, String cate) {
		int count=0;
		System.out.println("");
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.print(item.toString() + "\n");
			count ++;
		}
		System.out.printf("\n�� ! �� %d���� �׸��� ã�ҽ��ϴ�. ! ��\n", count);
	}
	
	public static void findDue(TodoList l, String due) {
		int count=0;
		System.out.println("");
		for (TodoItem item : l.getListCertainDate(due)) {
			System.out.printf(item.toString() + "\n");
			count ++;
		}
		System.out.printf("\n�� ! �� %d���� �׸��� ã�ҽ��ϴ�. ! ��\n", count);
	}
	
}
