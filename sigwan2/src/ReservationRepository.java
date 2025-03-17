import java.util.ArrayList;
import java.util.List;

/**
 * ReservationRepository 클래스
 * Reservation 객체를 메모리에 저장하고 조회하는 기능을 담당합니다.
 */
public class ReservationRepository {
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    /**
     * 전체 예약 목록을 반환합니다.
     */
    public List<Reservation> getAllReservations() {
        return reservations;
    }

    /**
     * 특정 사용자 ID를 가진 예약들을 모두 찾아서 리스트로 반환합니다.
     */
    public List<Reservation> findReservationsByUserId(String userId) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getUser().getId().equals(userId)) {
                result.add(r);
            }
        }
        return result;
    }
}
