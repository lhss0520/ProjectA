import java.util.ArrayList;
import java.util.List;

/**
 * MovieRepository 클래스는 영화 데이터를 저장하고 관리합니다.
 */
public class MovieRepository {
    private List<Movie> movieList;
    
    // 생성자: 기본 영화 데이터를 추가합니다.
    public MovieRepository() {
        movieList = new ArrayList<>();
        movieList.add(new Movie("뽀로로", "2025-03-03", 0));
        movieList.add(new Movie("컨저링", "2025-03-03", 15));
    }
    
    // 모든 영화 목록을 반환합니다.
    public List<Movie> getAllMovies() {
        return movieList;
    }
    
    // 사용자의 나이에 맞고, 지정한 날짜에 상영하는 영화 목록을 반환합니다.
    public List<Movie> getMoviesByDateAndAge(java.time.LocalDate date, int age) {
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getScreeningLocalDate().equals(date) && age >= movie.getScreeningAge()) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    
    // 새로운 영화를 추가합니다.
    public void addMovie(Movie movie) {
        movieList.add(movie);
    }
}
