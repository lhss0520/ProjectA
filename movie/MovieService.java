import java.util.List;
import java.util.Scanner;

/**
 * MovieService 클래스
 * 영화 목록을 보여주고 사용자의 선택을 받아서 영화를 반환하는 로직을 담당합니다.
 */
public class MovieService {
    private Scanner scanner;
    private MovieRepository movieRepository;

    public MovieService(Scanner scanner, MovieRepository movieRepository) {
        this.scanner = scanner;
        this.movieRepository = movieRepository;
    }

    /**
     * 등록된 모든 영화 목록을 보여주고, 사용자가 선택한 영화를 반환합니다.
     * '뒤로가기'나 잘못된 선택 시 null을 반환합니다.
     */
    public Movie selectMovie() {
        System.out.println("===== 영화 선택 =====");
        List<Movie> allMovies = movieRepository.getAllMovies();
        if (allMovies.isEmpty()) {
            System.out.println("현재 등록된 영화가 없습니다.\n");
            return null;
        }

        int index = 1;
        for (Movie movie : allMovies) {
            System.out.println(index + ". " + movie.getTitle());
            index++;
        }
        System.out.println(index + ". 뒤로가기");

        System.out.print("영화 선택 (번호): ");
        int choice = Integer.parseInt(scanner.nextLine());

        // '뒤로가기' 선택 시 null 반환
        if (choice == index) {
            System.out.println("영화 선택이 취소되었습니다.\n");
            return null;
        }
        // 범위를 벗어난 경우 null
        if (choice < 1 || choice > allMovies.size()) {
            System.out.println("잘못된 선택입니다.\n");
            return null;
        }

        Movie selectedMovie = allMovies.get(choice - 1);
        System.out.println("선택한 영화: " + selectedMovie.getTitle() + "\n");
        return selectedMovie;
    }
}