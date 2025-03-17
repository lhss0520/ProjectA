/**
 * ReservationService 클래스
 * 예약 객체를 생성하고, 예약 정보를 조회하는 기능을 담당합니다.
 */
public class ReservationService { 
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * 사용자와 영화 정보를 받아서 새로운 예약을 생성한 뒤 저장합니다.
     */
    public Reservation createReservation(User user, Movie movie) {
        Reservation reservation = new Reservation(user, movie);
        reservationRepository.addReservation(reservation);
        System.out.println("예약이 완료되었습니다.\n");
        return reservation;
    }

    /**
     * 생성된 예약 정보를 간단히 출력해줍니다.
     */
    public void showReservationInfo(Reservation reservation) {
        System.out.println("===== 예약 확인 =====");
        System.out.println("예약자: " + reservation.getUser().getId());
        System.out.println("영화: " + reservation.getMovie().getTitle());
        System.out.println();
    }
}