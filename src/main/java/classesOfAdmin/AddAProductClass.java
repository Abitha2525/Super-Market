package classesOfAdmin;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.Date;
import java.util.LinkedList;

import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

public class AddAProductClass {

	String category;
	String productName;
	int quantity;
	double price;
	Date date;
	String url;
	
	public AddAProductClass(String category, String productName, int quantity, double price, Date date, String url){
		this.category = category;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.date = date;
		this.url = url;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@SuppressWarnings("resource")
	public JSONObject addAProduct(AddAProductClass obj) {
		JSONObject jsonObject = new JSONObject();
		int id = 0;
		int urlId = 0;
		int productId = 0;
			try {
		   PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select categoryId from Categories where category = ?");
		   ppst.setString(1, obj.getCategory());
		   ResultSet rs = ppst.executeQuery();
		   if(rs.next()) {
			   id = rs.getInt(1);
		   }
		   
		   ppst = ConstantVariables.dbConnection.prepareStatement("insert into urlTable(url) values(?)");
		   ppst.setString(1, obj.getUrl());
		   ppst.execute();
		   
		   ppst = ConstantVariables.dbConnection.prepareStatement("select urlOfProducts from urlTable,  where url = ?");
		   ppst.setString(1, obj.getUrl());
		   ResultSet rs2 = ppst.executeQuery();
			
		   if(rs2.next()) {
			  urlId = rs2.getInt(1); 
		   }
		   ppst = ConstantVariables.dbConnection.prepareStatement("select max(productId) from Products");
		   ResultSet rs3 = ppst.executeQuery();
		   if(rs3.next()) {
			   productId = rs3.getInt(1);
		   }
		   
		    ppst = ConstantVariables.dbConnection.prepareStatement("insert into Products(productId, productName, productCount, price, categoryId, urlOfProducts, status) values (?, ?, ?, ?, ?, ?, ?)");
			ppst.setInt(1, productId + 1);
			ppst.setString(2, obj.getProductName());
			ppst.setInt(3, obj.getQuantity());
			ppst.setDouble(4, obj.getPrice());
			ppst.setInt(5, id);
			ppst.setInt(6, urlId);
			ppst.setString(7, "Available");
			ppst.execute();
			
			ppst = ConstantVariables.dbConnection.prepareStatement("insert into DateDetails(insertDate, productId) values(?, ?)");
			ppst.setDate(1, obj.getDate());
			ppst.setInt(2, productId + 1);
			ppst.execute();
		    
			
			jsonObject.put("statusCode", 200);
			jsonObject.put("message", "Product was Added Successfully");
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			jsonObject.put("statusCode", 500);
			jsonObject.put("message", "Some error occurred");
			
		}
			return jsonObject;
	}
	
	
	
}
