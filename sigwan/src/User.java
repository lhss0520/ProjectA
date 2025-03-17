/**
 * User 클래스는 사용자 ID와 나이를 저장합니다.
 */
public class User {
    private String id;
    private int age;
    
    // 생성자: 객체 생성 시 사용자 ID와 나이를 초기화합니다.
    public User(String id, int age) {
        this.id = id;
        this.age = age;
    }
    
    // 사용자 ID를 반환합니다.
    public String getId() {
        return id;
    }
    
    // 사용자의 나이를 반환합니다.
    public int getAge() {
        return age;
    }
}
