package ClassesOfUser;

import java.util.LinkedList;

public class Users {

	String userName;
	String mobileNumber;
	int pinNumber;
	public static LinkedList<Users> users = new LinkedList<>();
	static LinkedList<BuyingProducts> products = new LinkedList<>();
	
	public Users(String userName, String mobileNumber, int pinNumber){
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.pinNumber = pinNumber;
	}

	String getUserName() {
		return userName;
	}

	void setUserName(String userName) {
		this.userName = userName;
	}

	String getMobileNumber() {
		return mobileNumber;
	}

	void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	int getPinNumber() {
		return pinNumber;
	}

	void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	LinkedList<BuyingProducts> getProducts() {
		return products;
	}

	void setProducts(BuyingProducts product) {
		this.products.add(product);
	}
	
	
	
	
}
