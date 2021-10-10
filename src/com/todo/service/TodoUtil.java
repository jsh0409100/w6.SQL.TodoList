package com.todo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
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
		
		System.out.println("\n> ������ ī�װ��� �Է��ϼ���. ");
		category = sc.nextLine().trim();
		
		System.out.println("\n> ���Ͽ� ���� ������ �Է��ϼ���. ");
		desc = sc.nextLine().trim();
		
		System.out.println("\n> ������ �������ڸ� �Է��ϼ���. ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(l.addItem(t)>0)
			System.out.println("\n�� ! �߰� �Ϸ� ! ��");
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);

		System.out.println("\n"
				+ ">>> �׸� ����\n"
				+ "> ������ ������ ��ȣ�� �Է��ϼ���.");
		int index = sc.nextInt();
		
		if(l.deleteItem(index)>0)
			System.out.println("\n�� ! ���� �Ϸ� ! ��");
			
	}
		

	public static void updateItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ ">>> �׸� ����\n"
				+ "> ������ ������ ��ȣ�� �Է��ϼ���. ");
		int index = sc.nextInt();
		
		System.out.println("\n> �ش� ������ ���ο� �̸��� �Է��ϼ���. ");
		String new_title = sc.next().trim();
		
		System.out.println("\n> ���ο� ������ ī�װ� �̸��� �Է��ϼ���. ");
		String new_category = sc.next();
		sc.nextLine();
		
		System.out.println("\n> �ش� ������ ���ο� ������ �Է��ϼ���. ");
		String new_description = sc.nextLine().trim();
		
		System.out.println("\n> ������ �������ڸ� �Է��ϼ���. ");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
		if(l.addItem(t)>0)
			System.out.println("\n�� ! ���� �Ϸ� ! ��");

	}

	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}

	public static void listCateAll(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count ++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n", count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.print(item.toString());
			count ++;
		}
		System.out.printf("\n�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("\n"
				+ "�� ! ���� ���, �� ", l.getCount(), "�� ! ��");
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("\n"
				+ "�� ! ���� ���, �� ", l.getCount(), "�� ! ��");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
}
