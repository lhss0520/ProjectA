package Kosa.movie;

public class User {
	private int age;
	private String userID;
	
	public User() {}

	public User(int age, String userID) {
		super();
		this.age = age;
		this.userID = userID;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	

}
