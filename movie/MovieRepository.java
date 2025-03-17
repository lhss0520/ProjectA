import java.util.ArrayList;
import java.util.List;

/**
 * MovieRepository 클래스
 * Movie 객체를 메모리에 저장하고 조회하는 기능을 담당합니다.
 */
public class MovieRepository {
    private List<Movie> movies = new ArrayList<>();

    // 생성자에서 임의로 몇 개의 영화를 미리 등록해둡니다.
    public MovieRepository() {
        movies.add(new Movie("어벤져스"));
        movies.add(new Movie("인셉션"));
        movies.add(new Movie("라라랜드"));
    }

    public List<Movie> getAllMovies() {
        return movies;
    }
}