package classesOfAdmin;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

public class EditAProductClass {

	String category;
	String productName;
	String productNewName;
	Date date;
	
	public EditAProductClass(String category, String productName, String productNewName, Date date){
		this.category = category;
		this.productName = productName;
		this.productNewName = productNewName;
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
	public String getProductNewName() {
		return productNewName;
	}
	public void setProductNewName(String productNewName) {
		this.productNewName = productNewName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public JSONObject editAProduct(EditAProductClass obj) {
	        int id = 0;
	        JSONObject jsonObject = new JSONObject();
			try {
				PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select categoryId from Categories where category = ? ");
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
				
			    ppst = ConstantVariables.dbConnection.prepareStatement("update Products set productName = ? where productId = ?");
				ppst.setString(1, obj.getProductNewName());
				ppst.setInt(2, productId);
			    ppst.execute();
			    
			    ppst = ConstantVariables.dbConnection.prepareStatement("update Products set OldName = ? where productId = ?");
			    ppst.setString(1, obj.getProductName());
			    ppst.setInt(2, productId);
			    ppst.execute();
			    
			    ppst = ConstantVariables.dbConnection.prepareStatement("update DateDetails set editDate = ? where productId = ?");
			    ppst.setDate(1, obj.getDate());
			    ppst.setInt(2, productId);
			    ppst.execute();
			    			    
			    jsonObject.put("statusCode", 200);
			    jsonObject.put("message", "Product name was edited successfully");
			    
			}
			catch(SQLException ex) {
				ex.printStackTrace();
				jsonObject.put("statusCode", 400);
				jsonObject.put("message", "Some error occurred");
			}
			return jsonObject;
		}
}
