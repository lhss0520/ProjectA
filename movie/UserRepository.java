import java.util.ArrayList;
import java.util.List;

/**
 * UserRepository 클래스
 * User 객체를 메모리에 저장하고 조회하는 기능을 담당합니다.
 */
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}