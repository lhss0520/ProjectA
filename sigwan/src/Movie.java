import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Movie 클래스는 영화 제목, 상영일, 관람 가능 나이를 저장합니다.
 */
public class Movie {
    private String title;         // 영화 제목
    private String screeningDate; // 상영일 (예: "2025-03-03")
    private int screeningAge;     // 관람 가능 최소 나이
    
    // 생성자: 영화 정보를 초기화합니다.
    public Movie(String title, String screeningDate, int screeningAge) {
        this.title = title;
        this.screeningDate = screeningDate;
        this.screeningAge = screeningAge;
    }
    
    // 영화 제목을 반환합니다.
    public String getTitle() {
        return title;
    }
    
    // 상영일을 문자열로 반환합니다.
    public String getScreeningDate() {
        return screeningDate;
    }
    
    // 관람 가능 나이를 반환합니다.
    public int getScreeningAge() {
        return screeningAge;
    }
    
    // 영화 정보를 콘솔에 출력합니다.
    public void show() {
        System.out.println("제목: " + title + ", 상영일: " + screeningDate + ", 관람 가능 나이: " + screeningAge + "+");
    }
    
    // 상영일을 LocalDate로 변환하여 반환합니다.
    public LocalDate getScreeningLocalDate() {
        return LocalDate.parse(screeningDate, DateTimeFormatter.ISO_DATE);
    }
}
