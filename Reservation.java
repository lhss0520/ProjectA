package Kosa.movie;

public class Reservation {
	private User user;
	private Movie movie;
	
	public Reservation() {}

	public Reservation(User user, Movie movie) {
		super();
		this.user = user;
		this.movie = movie;
	}
//	public void movieReservation() {
//		
//		if(user.getAge() < movie.getScreeningAge()) {
//			System.out.println("�󿵺Ұ��մϴ�.");
//		}else {
//			System.out.println("���� �����մϴ�.");
//		}
//	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	
	


}
