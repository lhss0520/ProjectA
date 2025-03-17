import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) { //주요 서비스 및 저장소 객체 생성
		Scanner sc = new Scanner(System.in);
		UserRepository userRepository = new UserRepository(); 
		MovieRepository movieRepository = new MovieRepository(); 
		ReservationRepository reservationRepository = new ReservationRepository();
		
		UserService userServ = new UserService(sc, userRepository);
		MovieService movieServ = new MovieService(sc, movieRepository);
		ReservationService reservationServ =  new ReservationService(reservationRepository);

		while(true) {
			
			System.out.println("1.예약하기 2.예약확인 3.종료");
			System.out.print("선택하세요: ");

			String menu = sc.nextLine();
			switch (menu) {
				case "1":
					User user = userServ.getOrCreateUser(); //UserService를 통해 사용자 정보입력 및 조회

					Movie movie = movieServ.selectMovie(); //MovieService를 통해 영화목록 출력 및 선택

					//reservationServ.createReservation(user, movie);
					//새로운 예약 객체 생성 및 저장
					Reservation reservation = reservationServ.createReservation(user, movie);
					reservationServ.showReservationInfo(reservation); //예약정보 출력
					
					break;
				case "2":
					System.out.print("예약확인할 ID 입력: ");
					String id = sc.nextLine();
					//입력한 사용자Id로 예약정보 조회
					List<Reservation> reservations = reservationRepository.findReservationsByUserId(id);
					
					if(reservations.isEmpty()){
						System.out.println("예약 내역이 없습니다.");
					}else{
						for(Reservation r : reservations){
							System.out.println("ID: "+ r.getUser().getId() +"영화: " +r.getMovie().getTitle());
						}
					}

					break;
				case "3":
					System.out.println("종료합니다.");
					return;

			}
	       
		}
		
	}

}
