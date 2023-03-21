package ClassesOfUser;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.tomcat.util.bcel.Const;
import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

public class BuyingProducts {

	String productName;
	int quantity;
	double price;
	String url;
	Date date;
	int userPinNumber;
	int totalQuantity;
	int productId;
	int urlId;

	public static LinkedList<BuyingProducts> list = new LinkedList<>();
	BuyingProducts(){}
	BuyingProducts(String productName, int quantity, double price, String url, Date date, int userPinNumber, int totalQuantity, int productId, int urlId){
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.url = url;
		this.date = date;
		this.userPinNumber = userPinNumber;
		this.totalQuantity = totalQuantity;
		this.urlId = urlId;
		this.productId = productId;
	}

	public int getUrlId() {
		return urlId;
	}
	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUserPinNumber() {
		return userPinNumber;
	}
	public void setUserMobileNumber(int userPinNumber) {
		this.userPinNumber = userPinNumber;
	}
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public void addToList(BuyingProducts obj) {
		list.add(obj);
	}
	public LinkedList<BuyingProducts> getList(){
		return list;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public static JSONObject addProductsToCart(String product, String quantity1, Date date, String mobile) {
		JSONObject jsonObject = new JSONObject();
		try {
			System.out.println(quantity1);
			int quantity = Integer.valueOf(quantity1);
			double price = 0;
			int totalQuantity = 0;
			int productId = 0;
			int pinNumber = 0;
			int urlId = 0;
			String url = "";
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select price, productCount, productId, urlOfProducts from Products where productName = ? and status = ?");
			ppst.setString(1, product);
			ppst.setString(2, "Available");
			ResultSet rs = ppst.executeQuery();
			
			if(rs.next()) {
				price = rs.getDouble(1);
				totalQuantity = rs.getInt(2);
				productId = rs.getInt(3);
				urlId = rs.getInt(4);
			}
			
			ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
			ppst.setInt(1, urlId);
			ResultSet rs3 = ppst.executeQuery();
			
			if(rs3.next()) {
				url = rs3.getString(1);
			}
			
			ppst = ConstantVariables.dbConnection.prepareStatement("select pinNumber from LoginUsers where mobileNumber = ?");
			ppst.setString(1, mobile);
			ResultSet rs2 = ppst.executeQuery();
			if(rs2.next()) {
				pinNumber = rs2.getInt(1);
			if(quantity > 0) {
			
			if(totalQuantity > 0) {
			
				if(quantity <= totalQuantity) {
			
			boolean check = false;
			BuyingProducts buy = new BuyingProducts(product, quantity, price, url, date, pinNumber, totalQuantity, productId, urlId);
			for(int i = 0; i < buy.getList().size(); i++) {
				if(buy.getList().get(i).getProductName().equals(product)) {
					check = true;
//					int productQuantityForPurchase = buy.getList().get(i).getQuantity();
					buy.getList().get(i).setQuantity(quantity);
					System.out.println("quantity : " + buy.getQuantity());
					break;
				}
			}
			if(!check) {
			buy.addToList(buy);
			}
			System.out.println(list.get(0).getQuantity() + " " + list.get(0).getPrice());
			jsonObject.put("statusCode", 200);
			jsonObject.put("message", "Bought successfully");
				}
				else {
					jsonObject.put("statusCode", 450);
					jsonObject.put("message", "That product quantity was bigger than our quantity");
				}
			
			}
			
			else {
				jsonObject.put("statusCode", 400);
				jsonObject.put("message", "That product is doesn't available in our superMarket");
			}
			}
			
			else {
				jsonObject.put("statusCode", 475);
				jsonObject.put("message", "Please enter a valid quantity");
			}
			}
			else {
				jsonObject.put("statusCode", 475);
				jsonObject.put("message", "You are not a logged in user");
			}
			
		}
		catch(NumberFormatException ex) {
			ex.printStackTrace();
			jsonObject.put("statusCOde", 500);
			jsonObject.put("message", "Please enter the quantity of that product");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return jsonObject;
	}
	
	public static double billCalculation(Date date) {
		double totalAmount = 0;
		for(int i = 0; i < list.size(); i++) {
			totalAmount += list.get(i).getPrice() * list.get(i).getQuantity();
	}
		return totalAmount;
	}
	
	public static void removeProductsFromList(String product) {
		for(BuyingProducts b : list) {
			if(b.getProductName().equals(product)) {
				list.remove(b);
				System.out.println(b.getProductName());
				break;
			}
		}
	}
	
	public static void changeProductDetailsAfterPurchase(Date date, double totalAmount) {
		try {
		PreparedStatement ppst = null;
		for(int i = 0; i < list.size(); i++) {
			totalAmount += list.get(i).getPrice() * list.get(i).getQuantity();

			ppst = ConstantVariables.dbConnection.prepareStatement("update Products set productCount = ? where productName = ?");
			ppst.setInt(1, list.get(i).getTotalQuantity()-list.get(i).getQuantity());
			ppst.setString(2, list.get(i).getProductName());
			ppst.execute();
			
			ppst = ConstantVariables.dbConnection.prepareStatement("insert into saledProducts (productId, pinNumber, quantity, saledDate, urlOfProducts) values(?, ?, ?, ?, ?)");
			ppst.setInt(1, list.get(i).getProductId());
			ppst.setInt(2, list.get(i).getUserPinNumber());
			ppst.setInt(3, list.get(i).getQuantity());
			ppst.setDate(4, list.get(i).getDate());
			ppst.setInt(5, list.get(i).getUrlId());
			ppst.execute();
			
		}
		ppst = ConstantVariables.dbConnection.prepareStatement("insert into dailyIncome values(?, ?)");
		ppst.setDate(1, date);
		ppst.setDouble(2, totalAmount);
		ppst.execute();
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
		}
	
	}
	
	public static int findPinNumberOfUser() {
		int pin = 0;
		try {
			String mobile = "";
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select mobileNumber from sessions");
			ResultSet rs = ppst.executeQuery();
			if(rs.next()) {
				mobile = rs.getString(1);
			}
			
			ppst = ConstantVariables.dbConnection.prepareStatement("select pinNumber from LoginUsers where mobileNumber = ?");
			ppst.setString(1, mobile);
			ResultSet rs2 = ppst.executeQuery();
			
			if(rs2.next()) {
				pin = rs2.getInt(1);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return pin;
	}
}
