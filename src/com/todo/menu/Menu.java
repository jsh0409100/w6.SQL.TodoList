package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println();
        System.out.println("♡  Todo List Program  ♡\n투두리스트를 관리하는 프로그램입니다.\n프로그램을 실행하려면 화살표 다음의 명령어를 입력하세요.");
        System.out.println("＊ 이 사용법을 다시 보려면\t→ help");
        System.out.println("\n＊ 새 할일을 추가하려면\t→ add");
        System.out.println("＊ 등록된 할일을 삭제하려면\t→ del");
        System.out.println("＊ 등록된 할일을 수정하려면\t→ edit");
        System.out.println("＊ 등록된 할일을 완료하려면\t→ com 완료된 할일의 번호을 띄어쓰기로 구분하여 모두 넣으세요");
        
        System.out.println("\n   ＊＊ 목록 출력 포맷 ＊＊");
        System.out.println("   <번호.[완료유무][카테고리]할일이름 설명:  마감일: YYMMDD  등록일: YY/MM/DD  중요도: 가장중요1~5  수정횟수:   >");
        System.out.println("\n   ＊＊ 목록 출력 방식 ＊＊");
        System.out.println("＊ 전체 할일 목록을 보려면\t\t→ ls");
        System.out.println("＊ 완료한 목록을 보려면\t\t→ ls_com");
        System.out.println("＊ 완료되지 않은 목록을 보려면\t\t→ ls_ncom");
        System.out.println("＊ 중요도 순서로 목록을 보려면\t\t→ ls_imprt");
        System.out.println("＊ 마감일자가 빠른 순서대로 목록을 보려면\t→ ls_inhurry");
        System.out.println("＊ 오늘을 기준부터 입력한 일수만큼 마감일이 남은 목록을 보려면\t→ ls_days 남은 일수를 숫자만 넣으세요");
        System.out.println("＊ 할일 목록을 이름 오름차순으로 보려면\t→ ls_name_a");
        System.out.println("＊ 할일 목록을 이름 내림차순으로 보려면\t→ ls_name_d");
        System.out.println("＊ 할일 목록을 추가한 순서대로 보려면\t→ ls_date_a");
        System.out.println("＊ 할일 목록을 추가한 순서 반대로 보려면\t→ ls_date_d");
        System.out.println("＊ 할일 목록의 카테고리 목록을 보려면  → ls_cate");
        System.out.println("\n   ＊＊ 검색 방법 ＊＊");
        System.out.println("＊ 제목과 설명에서 키워드로 검색하려면\t→ find 키워드를 넣으세요");
        System.out.println("＊ 카테고리를 키워드로 검색하려면\t→ find_cate 키워드를 넣으세요");
        System.out.println("＊ 마감날짜로 해야 하는 할일을 검색하려면\t→ find_due 날짜를 YYMMDD형식으로 넣으세요");
        System.out.println("\n   ＊＊ 프로그램 종료\t→ exit");
    }
    
    public static void promt() {
    	System.out.println("\n♥ Command");
    
    }
}
