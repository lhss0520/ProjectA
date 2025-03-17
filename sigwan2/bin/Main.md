import java.util.Scanner;

/**
 * Main 클래스 (프로그램의 진입점)
 * 1) 메인 메뉴 반복
 *   - 1. 사용자 정보 입력 및 영화 예약
 *   - 2. 종료
 */
public class Main {
    public static void main(String[] args) {
        // 공용 Scanner 및 리포지토리 객체 생성
        Scanner scanner = new Scanner(System.in);
        UserRepository userRepo = new UserRepository();
        MovieRepository movieRepo = new MovieRepository();
        ReservationRepository reservationRepo = new ReservationRepository();

        // 각 기능을 담당하는 서비스 생성
        UserService userService = new UserService(scanner, userRepo);
        MovieService movieService = new MovieService(scanner, movieRepo);
        ReservationService reservationService = new ReservationService(reservationRepo);

        while (true) {
            System.out.println("===== 메인 메뉴 =====");
            System.out.println("1. 사용자 정보 입력 및 영화 예약");
            System.out.println("2. 종료");
            System.out.print("선택 (번호): ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                // (1) 사용자 확인
                User user = userService.getOrCreateUser();

                // (2) 영화 선택
                Movie selectedMovie = movieService.selectMovie();
                if (selectedMovie == null) {
                    // 영화 선택이 취소되었거나 잘못된 번호라면 메인 메뉴로 돌아감
                    continue;
                }

                // (3) 예약 생성
                Reservation reservation = reservationService.createReservation(user, selectedMovie);

                // (4) 예약 정보 확인
                reservationService.showReservationInfo(reservation);

                // 메인 메뉴로 복귀
            }
            else if (choice == 2) {
                // 종료 선택
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            else {
                // 잘못된 선택
                System.out.println("잘못된 선택입니다. 다시 시도하세요.\n");
            }
        }

        // 자원 정리
        scanner.close();
    }
}
