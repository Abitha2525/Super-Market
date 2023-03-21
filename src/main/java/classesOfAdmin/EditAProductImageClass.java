package classesOfAdmin;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

public class EditAProductImageClass {

	String category;
	String productName;
	String url;
	Date date;
	
	public EditAProductImageClass(String category, String productName, String url, Date date){
		this.category = category;
		this.productName = productName;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public JSONObject editAProductImage(EditAProductImageClass obj) {
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
				long oldUrl = 0;
				long maxUrlId = 0;
//				ppst = ConstantVariables.dbConnection.prepareStatement("select productId, urlOfProducts from Products where productName = ? and categoryId = ? and status = ?");
			
				ppst = ConstantVariables.dbConnection.prepareStatement("select productId, urlOfProducts from Products where productName = ? and categoryId = ? and status = ?");
				ppst.setString(1, obj.getProductName());
				ppst.setInt(2, id);
				ppst.setString(3, "Available");
				ResultSet rs2 = ppst.executeQuery();
				if(rs2.next()) {
					productId = rs2.getInt(1);
					oldUrl = rs2.getLong(2);
				}
				
				ppst = ConstantVariables.dbConnection.prepareStatement("insert into urlTable(url) values(?)");
				ppst.setString(1, obj.getUrl());
				ppst.execute();
				
				ppst = ConstantVariables.dbConnection.prepareStatement("select max(urlOfProducts) from urlTable");
				ResultSet rs3 = ppst.executeQuery();
				if(rs3.next()) {
					maxUrlId = rs3.getInt(1);
				}
			
				
			    ppst = ConstantVariables.dbConnection.prepareStatement("update Products set urlOfProducts = ? where productId = ?");
				ppst.setDouble(1, maxUrlId);
				ppst.setInt(2, productId);
			    ppst.execute();
			    
			    ppst = ConstantVariables.dbConnection.prepareStatement("update Products set oldImage = ? where productId = ?");
			    ppst.setLong(1, oldUrl);
			    ppst.setInt(2, productId);
			    ppst.execute();
			    
			    ppst = ConstantVariables.dbConnection.prepareStatement("update DateDetails set editImageDate = ? where productId = ?");
			    ppst.setDate(1, obj.getDate());
			    ppst.setInt(2, productId);
			    ppst.execute();
			    			    
			    jsonObject.put("statusCode", 200);
			    jsonObject.put("message", "Product image was edited successfully");
			    
			}
			catch(SQLException ex) {
				ex.printStackTrace();
				jsonObject.put("statusCode", 400);
				jsonObject.put("message", "Some error occurred");
			}
			return jsonObject;
	}
}
