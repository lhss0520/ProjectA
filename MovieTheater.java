package Kosa.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieTheater {
	List<Movie>movieList;
	
	Scanner sc = new Scanner(System.in);
	
	public MovieTheater() {
		movieList = new ArrayList<Movie>();
		
		movieList.add(new Movie("뽀로로","2025-03-03",0));
		movieList.add(new Movie("컨저링", "2025-03-03", 15));
	}
	public void showMovie() {
		System.out.println("<<상영중인영화목록>>");
		for(int i=0;i<movieList.size();i++) {
			movieList.get(i).show();
		}
	}
	public void showMovie2(int age) {
	    System.out.println("<<상영가능한 영화 목록>>");
	    int count = 0;
	    for(int i=0;i<movieList.size();i++) {
	    	if(age >= movieList.get(i).getScreeningAge()) {
	    		count++;
	    		movieList.get(i).show();
	    	}
	    }
	    if(count == 0){
	        System.out.println("상영가능한 영화가 없습니다.");
	        return;
	    }
	}
	
}
