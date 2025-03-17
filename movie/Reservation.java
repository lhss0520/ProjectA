/**
 * Reservation 클래스
 * 사용자와 영화 정보를 바탕으로 한 예약 정보를 담는 클래스입니다.
 */
public class Reservation {
    private User user;      // 예약자
    private Movie movie;    // 예약된 영화

    public Reservation(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }
}