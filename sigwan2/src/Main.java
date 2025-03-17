import java.util.List;
import java.util.Scanner;

/**
 * Main 클래스
 * 1) 메인 메뉴 (무한 루프)
 *   - 1. 예약하기
 *   - 2. 예약 확인
 *   - 3. 종료
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

        // 메인 메뉴: 종료를 선택하기 전까지 반복
        while (true) {
            System.out.println("===== 메인 메뉴 =====");
            System.out.println("1. 예약하기");
            System.out.println("2. 예약 확인");
            System.out.println("3. 종료");
            System.out.print("선택 (번호): ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.\n");
                continue;
            }

            if (choice == 1) {
                // ===== 1) 예약하기 =====
                // (1) 사용자 ID 입력 및 등록/조회
                User user = userService.getOrCreateUser();

                // (2) 영화 선택
                Movie selectedMovie = movieService.selectMovie();
                if (selectedMovie == null) {
                    // 뒤로가기 혹은 잘못된 선택 → 메인 메뉴로 복귀
                    continue;
                }

                // (3) 예약 생성
                Reservation reservation = reservationService.createReservation(user, selectedMovie);

                // (4) 예약 정보 확인
                reservationService.showReservationInfo(reservation);

                // 예약이 끝나면 자동으로 메인 메뉴로 돌아갑니다 (while 루프 계속)
            }
            else if (choice == 2) {
                // ===== 2) 예약 확인 =====
                System.out.print("예약을 확인할 사용자 ID를 입력하세요: ");
                String userId = scanner.nextLine();

                // 해당 사용자 ID의 모든 예약 찾기
                List<Reservation> userReservations = reservationRepo.findReservationsByUserId(userId);

                if (userReservations.isEmpty()) {
                    System.out.println("예약 정보가 없습니다.\n");
                } else {
                    System.out.println("===== 예약 확인 =====");
                    for (Reservation r : userReservations) {
                        System.out.println("예약자: " + r.getUser().getId() 
                            + ", 영화: " + r.getMovie().getTitle());
                    }
                    System.out.println();
                }
            }
            else if (choice == 3) {
                // ===== 3) 종료 =====
                System.out.println("프로그램을 종료합니다.");
                break; // while 루프 탈출
            }
            else {
                System.out.println("잘못된 선택입니다. 다시 시도하세요.\n");
            }
        }

        // 자원 정리
        scanner.close();
    }
}
