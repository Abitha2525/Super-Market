package classesOfAdmin;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

public class IncreaseAProductCountClass {

	String category;
	String productName;
	int quantity;
	Date date;
	
	public IncreaseAProductCountClass(String category, String productName, int quantity, Date date){
		this.category = category;
		this.productName = productName;
		this.quantity = quantity;
		this.date = date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public JSONObject increaseProductCount(IncreaseAProductCountClass obj) {
			JSONObject jsonObject = new JSONObject();
			PreparedStatement ppst = null;
			int id = 0;
			try {
				ppst = ConstantVariables.dbConnection.prepareStatement("select categoryId from Categories where category = ?");
				ppst.setString(1, obj.getCategory());
				ResultSet rs = ppst.executeQuery();
				
				if(rs.next()) {
					id = rs.getInt(1);
				}
				
				int productId = 0;
				ppst = ConstantVariables.dbConnection.prepareStatement("select productId from Products where productName = ? and categoryId = ? and status = ?");
				ppst.setString(1, obj.getProductName());
				ppst.setInt(2, id);
				ppst.setString(3, "Available");
				ResultSet rs2 = ppst.executeQuery();
				if(rs2.next()) {
					productId = rs2.getInt(1);
				}
			    ppst = ConstantVariables.dbConnection.prepareStatement("select productCount from Products where productId = ?");
				ppst.setInt(1, productId);
				ResultSet rs1 = ppst.executeQuery();
				int value = 0;
				if(rs1.next()) {
					value = rs1.getInt(1);
				}
				
				ppst = ConstantVariables.dbConnection.prepareStatement("update Products set initialQuantity = ? where productId = ?");
				ppst.setInt(1, value);
				ppst.setInt(2, productId);
				ppst.execute();
				
				ppst = ConstantVariables.dbConnection.prepareStatement("update Products set productCount = ? where productId = ?");
				ppst.setInt(1, value + obj.getQuantity());
				ppst.setInt(2, productId);
				ppst.execute();
				
				ppst = ConstantVariables.dbConnection.prepareStatement("update DateDetails set quantityIncreaseDate = ? where productId = ?");
				ppst.setDate(1, obj.getDate());
				ppst.setInt(2, productId);
				ppst.execute();
				
			    jsonObject.put("statusCode", 200);
			    jsonObject.put("message", "Product quantity was increased successfully");
			    
			}
			catch(Exception ex) {
				ex.printStackTrace();
				jsonObject.put("statusCode", 400);
				jsonObject.put("message", "Some error occurred");
			}
			return jsonObject;
		}
	
}
