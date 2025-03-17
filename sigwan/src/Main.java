import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

/**
 * Main 클래스는 전체 프로그램의 흐름을 제어합니다.
 */
public class Main {
    // Scanner와 Repository들을 static 변수로 선언하여 전체 메서드에서 사용합니다.
    static Scanner scanner = new Scanner(System.in);
    static UserRepository userRepo = new UserRepository();
    static MovieRepository movieRepo = new MovieRepository();
    static ReservationRepository reservationRepo = new ReservationRepository();
    
    public static void main(String[] args) {
        while (true) {
            // 메인 메뉴 출력
            System.out.println("===== 메인 메뉴 =====");
            System.out.println("1. 사용자 정보 입력 및 영화 예약");
            System.out.println("2. 기존 예약 조회 (예약 취소 옵션 포함)");
            System.out.println("3. 종료 및 완전 초기화");
            System.out.print("선택 (번호): ");
            int mainChoice = Integer.parseInt(scanner.nextLine());
            
            if (mainChoice == 1) {
                // 사용자 정보 입력 및 영화 예약 진행
                System.out.print("사용자 ID를 입력하세요: ");
                String userId = scanner.nextLine();
                System.out.print("나이를 입력하세요: ");
                int age = Integer.parseInt(scanner.nextLine());
                
                // 중복 여부 확인
                User user = userRepo.findUserById(userId);
                if (user == null) {
                    user = new User(userId, age);
                    userRepo.addUser(user);
                    System.out.println("새로운 사용자로 등록되었습니다.");
                    movieReservationFlow(user);
                } else {
                    System.out.println("이미 등록된 사용자입니다.");
                    // 기존 사용자일 경우 예약 조회 흐름 진행 (예약 취소 옵션 제공)
                    Reservation existingRes = reservationRepo.findReservationByUserId(userId);
                    if (existingRes != null) {
                        reservationInquiryFlow(existingRes, true);  // 첫 로그인 시 예약 취소 옵션 노출
                    } else {
                        System.out.println("예약 정보가 없습니다.");
                        System.out.print("영화 예약을 진행하시겠습니까? (Y/N): ");
                        String answer = scanner.nextLine();
                        if (answer.equalsIgnoreCase("Y")) {
                            movieReservationFlow(user);
                        }
                    }
                }
            } else if (mainChoice == 2) {
                // 기존 예약 조회: 사용자 ID로 예약 정보 검색
                System.out.print("예약 조회할 사용자 ID를 입력하세요: ");
                String userId = scanner.nextLine();
                Reservation existingRes = reservationRepo.findReservationByUserId(userId);
                if (existingRes != null) {
                    reservationInquiryFlow(existingRes, true);  // 기존 예약자의 경우 예약 취소 옵션 제공
                } else {
                    System.out.println("예약 정보가 없습니다.");
                }
            } else if (mainChoice == 3) {
                // 종료 및 완전 초기화: 모든 Repository를 초기화하고 프로그램 종료
                System.out.println("프로그램을 종료하고 모든 데이터가 초기화됩니다.");
                userRepo = new UserRepository();
                movieRepo = new MovieRepository();
                reservationRepo = new ReservationRepository();
                break;
            } else {
                System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
            System.out.println("메인 메뉴로 돌아갑니다.\n");
        }
        scanner.close();
    }
    
    // 영화 예약 흐름: 날짜 선택 → 영화 선택 → 좌석 예약 → 예약 저장 후 예약 조회
    static void movieReservationFlow(User user) {
        LocalDate selectedDate = selectDate();
        Movie selectedMovie = selectMovie(user, selectedDate);
        if (selectedMovie == null) {
            System.out.println("영화 선택이 취소되었습니다.");
            return;
        }
        int reservedSeats = reserveSeats(100); // 예: 남은 좌석 100석 가정
        Reservation res = new Reservation(user, selectedMovie, selectedDate, reservedSeats);
        reservationRepo.addReservation(res);
        System.out.println("예약이 완료되었습니다.");
        // 예약 완료 후 예약 조회 화면 (예약 취소 옵션 미노출)
        reservationInquiryFlow(res, false);
    }
    
    // 10일간의 날짜 중 예약 날짜 선택
    static LocalDate selectDate() {
        System.out.println("===== 날짜 선택 =====");
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 10; i++) {
            LocalDate date = today.plusDays(i);
            System.out.println((i + 1) + ". " + date);
        }
        System.out.print("예약할 날짜를 선택하세요 (번호): ");
        int choice = Integer.parseInt(scanner.nextLine());
        LocalDate selectedDate = today.plusDays(choice - 1);
        System.out.println("선택한 날짜: " + selectedDate);
        return selectedDate;
    }
    
    // 사용자의 나이와 선택한 날짜에 맞는 영화 선택
    static Movie selectMovie(User user, LocalDate date) {
        System.out.println("===== 영화 선택 =====");
        List<Movie> availableMovies = movieRepo.getMoviesByDateAndAge(date, user.getAge());
        if (availableMovies.isEmpty()) {
            System.out.println("해당 날짜에 상영중인 영화가 없거나, 관람 가능한 영화가 없습니다.");
            return null;
        }
        int index = 1;
        for (Movie movie : availableMovies) {
            System.out.println(index + ". " + movie.getTitle() + " (관람 나이: " + movie.getScreeningAge() + "+)");
            index++;
        }
        System.out.println(index + ". 뒤로가기");
        System.out.print("영화 선택 (번호): ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == index) {
            return null; // '뒤로가기' 선택 시
        } else if (choice > 0 && choice < index) {
            Movie selected = availableMovies.get(choice - 1);
            System.out.println("선택한 영화: " + selected.getTitle());
            return selected;
        }
        return null;
    }
    
    // 좌석 예약: 남은 좌석 수와 예약할 인원 입력
    static int reserveSeats(int availableSeats) {
        System.out.println("===== 좌석 예약 =====");
        System.out.println("남은 좌석 수: " + availableSeats);
        System.out.print("예약할 인원 수를 입력하세요: ");
        int reserved = Integer.parseInt(scanner.nextLine());
        if (reserved > availableSeats) {
            System.out.println("좌석이 부족합니다. 다시 입력하세요.");
            return reserveSeats(availableSeats);
        }
        return reserved;
    }
    
    /**
     * 예약 조회 및 옵션 처리
     * @param res 예약 정보
     * @param showCancellationOption 예약 취소 옵션을 보여줄지 여부 (기존 예약자가 처음 로그인했을 때만 true)
     */
    static void reservationInquiryFlow(Reservation res, boolean showCancellationOption) {
        System.out.println("===== 예약 조회 =====");
        System.out.println("예약자: " + res.getUser().getId());
        System.out.println("영화: " + res.getMovie().getTitle());
        System.out.println("예약 날짜: " + res.getDate());
        System.out.println("예약 인원: " + res.getSeatCount());
        if (showCancellationOption) {
            System.out.println("1. 예약 취소");
        }
        System.out.println("0. 메인 메뉴로 돌아가기");
        System.out.print("선택 (번호): ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1 && showCancellationOption) {
            reservationCancellationFlow(res);
        } else if (choice == 0) {
            // 메인 메뉴로 복귀
        } else {
            System.out.println("잘못된 선택입니다. 메인 메뉴로 돌아갑니다.");
        }
    }
    
    // 예약 취소 처리: 예약 데이터를 삭제
    static void reservationCancellationFlow(Reservation res) {
        reservationRepo.removeReservation(res);
        System.out.println("예약이 취소되었습니다. 좌석이 복구되었습니다.");
    }
}
