import java.time.LocalDate;

/**
 * Reservation 클래스는 예약 정보를 저장합니다.
 */
public class Reservation {
    private User user;
    private Movie movie;
    private LocalDate date;
    private int seatCount;
    
    // 생성자: 예약 정보를 초기화합니다.
    public Reservation(User user, Movie movie, LocalDate date, int seatCount) {
        this.user = user;
        this.movie = movie;
        this.date = date;
        this.seatCount = seatCount;
    }
    
    public User getUser() {
        return user;
    }
    
    public Movie getMovie() {
        return movie;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public int getSeatCount() {
        return seatCount;
    }
    
    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
