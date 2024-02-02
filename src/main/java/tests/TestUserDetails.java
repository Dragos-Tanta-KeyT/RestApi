package tests;

public class TestUserDetails {

	public static void main(String[] args) {

		//UserDetails user =  new UserDetails("Ion", "Bacau", 30);
		
		UserDetails altUser =  new UserDetails();
		altUser.setAge(24);
		altUser.setCity("Iasi");
		altUser.setName("Maria");
		
		System.out.println(altUser.getAge());
		
		
		
	}

}
