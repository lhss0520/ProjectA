import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

/**
 * Main 클래스는 전체 프로그램의 흐름을 제어합니다.
 */
public class Main {
    // Scanner와 Repository들은 static 변수로 선언하여 전체 메서드에서 사용합니다.
    static Scanner scanner = new Scanner(System.in);
    static UserRepository userRepo = new UserRepository();
    static MovieRepository movieRepo = new MovieRepository();
    static ReservationRepository reservationRepo = new ReservationRepository();
    
    public static void main(String[] args) {
        // 1. 사용자 정보 입력
        System.out.print("사용자 ID를 입력하세요: ");
        String userId = scanner.nextLine();
        System.out.print("나이를 입력하세요: ");
        int age = Integer.parseInt(scanner.nextLine());
        
        // 중복 여부 확인s
        User user = userRepo.findUserById(userId);
        if (user == null) {
            // 신규 사용자이면 저장하고 영화 예약 진행
            user = new User(userId, age);
            userRepo.addUser(user);
            System.out.println("새로운 사용자로 등록되었습니다.");
            movieReservationFlow(user);
        } else {
            // 기존 사용자이면 예약 조회 흐름 진행
            System.out.println("이미 등록된 사용자입니다.");
            Reservation existingRes = reservationRepo.findReservationByUserId(userId);
            if (existingRes != null) {
                reservationInquiryFlow(existingRes);
            } else {
                System.out.println("예약 정보가 없습니다.");
                // 기존 사용자라도 예약을 진행할 수 있도록 할 수도 있습니다.
                System.out.println("영화 예약을 진행하시겠습니까? (Y/N)");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    movieReservationFlow(user);
                }
            }
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
        int reservedSeats = reserveSeats(100); // 남은 좌석 100석 가정
        Reservation res = new Reservation(user, selectedMovie, selectedDate, reservedSeats);
        reservationRepo.addReservation(res);
        System.out.println("예약이 완료되었습니다.");
        reservationInquiryFlow(res);
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
            return null; // 뒤로가기 선택
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
    
    // 예약 조회 및 예약 취소 옵션 제공
    static void reservationInquiryFlow(Reservation res) {
        System.out.println("===== 예약 조회 =====");
        System.out.println("예약자: " + res.getUser().getId());
        System.out.println("영화: " + res.getMovie().getTitle());
        System.out.println("예약 날짜: " + res.getDate());
        System.out.println("예약 인원: " + res.getSeatCount());
        System.out.println("1. 예약 취소");
        System.out.println("0. 종료");
        System.out.print("선택 (번호): ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            reservationCancellationFlow(res);
        }
    }
    
    // 예약 취소 처리: 예약 데이터를 삭제하고 좌석 수 복구(여기서는 단순 메시지 출력)
    static void reservationCancellationFlow(Reservation res) {
        reservationRepo.removeReservation(res);
        System.out.println("예약이 취소되었습니다. 좌석이 복구되었습니다.");
    }
}
