import java.util.ArrayList;
import java.util.List;

/**
 * ReservationRepository 클래스는 예약 데이터를 저장하고 관리합니다.
 */
public class ReservationRepository {
    private List<Reservation> reservations;
    
    // 생성자: 예약 목록을 초기화합니다.
    public ReservationRepository() {
        reservations = new ArrayList<>();
    }
    
    // 새로운 예약을 추가합니다.
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
    
    // 주어진 사용자 ID로 예약 정보를 찾습니다.
    public Reservation findReservationByUserId(String userId) {
        for (Reservation r : reservations) {
            if (r.getUser().getId().equals(userId)) {
                return r;
            }
        }
        return null;
    }
    
    // 예약을 삭제합니다.
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }
    
    // 모든 예약 정보를 반환합니다.
    public List<Reservation> getAllReservations() {
        return reservations;
    }
}
