package Kosa.movie;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		MovieTheater mvt = new MovieTheater();
		mvt.showMovie(); //������ ��ȭ���
		System.out.println("==========================");
		
		while(true) {
			System.out.print("����� ID�� �Է��ϼ���: ");
	        String userID = sc.nextLine();

	        System.out.print("���̸� �Է��ϼ���: ");
	        int age = Integer.parseInt(sc.nextLine());
	        
	        
	        mvt.showMovie2(age);
		}
		
		
	}

}
