package classesOfAdmin;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

public class DeleteAProductClass {

	String category;
	String productName;
	Date date;
	
	public DeleteAProductClass(String category, String productName, Date date){
		this.category = category;
		this.productName = productName;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	 
	 
   
	 
	 public JSONObject deleteAProduct(DeleteAProductClass obj) {
         int id = 0;
		 JSONObject jsonObject = new JSONObject();
		try {
			PreparedStatement pp = ConstantVariables.dbConnection.prepareStatement("select categoryId from Categories where category = ?");
			pp.setString(1, obj.getCategory());
			ResultSet rs = pp.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt(1);
			}
			
			int productId = 0;
			pp = ConstantVariables.dbConnection.prepareStatement("select productId from Products where productName = ? and categoryId = ? and status = ?");
			pp.setString(1, obj.getProductName());
			pp.setInt(2, id);
			pp.setString(3, "Available");
			ResultSet rs2 = pp.executeQuery();
			if(rs2.next()) {
				productId = rs2.getInt(1);
			}
			
			pp = ConstantVariables.dbConnection.prepareStatement("update Products set status = ? where productId = ?");
			pp.setString(1, "Deleted");;
			pp.setInt(2, productId);
			pp.execute();
			
			pp = ConstantVariables.dbConnection.prepareStatement("update DateDetails set deleteDate = ? where productId = ?");
			pp.setDate(1, obj.getDate());
			pp.setInt(2, productId);
			pp.execute();
	        
			
			jsonObject.put("statusCode", 200);
			jsonObject.put("message", "Product was deleted successfully");
			}
		
		catch(SQLException ex) {
			ex.printStackTrace();
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Some error occurred");
		}
		return jsonObject;
	}
}
