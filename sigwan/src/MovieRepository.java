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
        movieList.add(new Movie("전체_관람영화_1", "2025-03-17", 0));
        movieList.add(new Movie("15세_관람영화_1", "2025-03-17", 15));
        movieList.add(new Movie("12세_관람영화_1", "2025-03-18", 12));
        movieList.add(new Movie("19세_관람영화_1", "2025-03-18", 19));
        movieList.add(new Movie("전체_관람영화_1", "2025-03-19", 0));
        movieList.add(new Movie("12세_관람영화_2", "2025-03-19", 12));
        movieList.add(new Movie("12세_관람영화_3", "2025-03-20", 12));
        movieList.add(new Movie("18세_관람영화_1", "2025-03-20", 18));
        movieList.add(new Movie("15세_관람영화_2", "2025-03-21", 15));
        movieList.add(new Movie("12세_관람영화_4", "2025-03-21", 12));
        movieList.add(new Movie("12세_관람영화_5", "2025-03-22", 12));
        movieList.add(new Movie("전체_관람영화_2", "2025-03-22", 0));
        movieList.add(new Movie("19세_관람영화_1", "2025-03-23", 19));
        movieList.add(new Movie("12세_관람영화_6", "2025-03-23", 12));
        movieList.add(new Movie("15세_관람영화_1", "2025-03-24", 15));
        movieList.add(new Movie("12세_관람영화_7", "2025-03-24", 12));
        movieList.add(new Movie("12세_관람영화_8", "2025-03-25", 12));
        movieList.add(new Movie("12세_관람영화_9", "2025-03-25", 12));
        movieList.add(new Movie("12세_관람영화_10", "2025-03-26", 12));
        movieList.add(new Movie("12세_관람영화_11", "2025-03-26", 12));
        movieList.add(new Movie("12세_관람영화_12", "2025-03-27", 12));
        movieList.add(new Movie("12세_관람영화_13", "2025-03-27", 12));
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
