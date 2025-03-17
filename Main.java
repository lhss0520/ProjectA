package Kosa.movie;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		MovieTheater mvt = new MovieTheater();
		mvt.showMovie(); //상영중인 영화목록
		System.out.println("==========================");
		
		while(true) {
			System.out.print("사용자 ID를 입력하세요: ");
	        String userID = sc.nextLine();

	        System.out.print("나이를 입력하세요: ");
	        int age = Integer.parseInt(sc.nextLine());
	        
	        
	        mvt.showMovie2(age);
		}
		
		
	}

}
