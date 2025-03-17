import java.util.Scanner;

/**
 * UserService 클래스
 * 사용자 ID 입력 및 사용자 생성/조회 로직을 담당합니다.
 */
public class UserService {
    private Scanner scanner;
    private UserRepository userRepository;

    public UserService(Scanner scanner, UserRepository userRepository) {
        this.scanner = scanner;
        this.userRepository = userRepository;
    }

    /**
     * 사용자 ID를 입력받고,
     * 만약 기존에 없는 사용자라면 새로 등록 후 반환합니다.
     */
    public User getOrCreateUser() {
        System.out.print("사용자 ID를 입력하세요: ");
        String userId = scanner.nextLine();

        User user = userRepository.findUserById(userId);
        if (user == null) {
            user = new User(userId);
            userRepository.addUser(user);
            System.out.println("새로운 사용자로 등록되었습니다.\n");
        } else {
            System.out.println("이미 등록된 사용자입니다.\n");
        }

        return user;
    }
}
