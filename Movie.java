package Kosa.movie;

public class Movie {
	private String movieName;
	private String movieDate;
	private int screeningAge;
	
	public Movie() {}
	
	public Movie(String movieName, String movieDate, int screeningAge) {
		super();
		this.movieName = movieName;
		this.movieDate = movieDate;
		this.screeningAge = screeningAge;
	}
	
	public void show() {
		System.out.println("Á¦¸ñ : " + movieName + "," + movieDate + "(" + screeningAge + ")");
	}
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieDate() {
		return movieDate;
	}
	public void setMovieDate(String movieDate) {
		this.movieDate = movieDate;
	}
	public int getScreeningAge() {
		return screeningAge;
	}
	public void setScreeningAge(int screeningAge) {
		this.screeningAge = screeningAge;
	}
	
	

}
