import java.util.ArrayList;
import java.util.List;

/**
 * UserRepository 클래스는 사용자 데이터를 저장하고 관리합니다.
 */
public class UserRepository {
    private List<User> userList;
    
    // 생성자: 사용자 목록을 초기화합니다.
    public UserRepository() {
        userList = new ArrayList<>();
    }
    
    // 새로운 사용자를 추가합니다.
    public void addUser(User user) {
        userList.add(user);
    }
    
    // 저장된 모든 사용자 목록을 반환합니다.
    public List<User> getAllUsers() {
        return userList;
    }
    
    // 주어진 ID로 사용자를 찾습니다.
    public User findUserById(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
