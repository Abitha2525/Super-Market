package classesOfAdmin;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

public class EditAProductPriceClass {

	String category;
	String productName;
	double newPrice;
	Date date;
	
	public EditAProductPriceClass(String category, String productName, double newPrice, Date date) {
		this.category = category;
		this.productName = productName;
		this.newPrice = newPrice;
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

	public double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    public JSONObject editAProductPrice(EditAProductPriceClass obj) {
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
				double oldPrice = 0;
				ppst = ConstantVariables.dbConnection.prepareStatement("select productId, price from Products where productName = ? and categoryId = ? and status = ?");
				ppst.setString(1, obj.getProductName());
				ppst.setInt(2, id);
				ppst.setString(3, "Available");
				ResultSet rs2 = ppst.executeQuery();
				if(rs2.next()) {
					productId = rs2.getInt(1);
					oldPrice = rs2.getDouble(2);
				}
				
			    ppst = ConstantVariables.dbConnection.prepareStatement("update Products set price = ? where productId = ?");
				ppst.setDouble(1, obj.getNewPrice());
				ppst.setInt(2, productId);
			    ppst.execute();
			    
			    ppst = ConstantVariables.dbConnection.prepareStatement("update Products set oldPrice = ? where productId = ?");
			    ppst.setDouble(1, oldPrice);
			    ppst.setInt(2, productId);
			    ppst.execute();
			    
			    ppst = ConstantVariables.dbConnection.prepareStatement("update DateDetails set editPriceDate = ? where productId = ?");
			    ppst.setDate(1, obj.getDate());
			    ppst.setInt(2, productId);
			    ppst.execute();
			    			    
			    jsonObject.put("statusCode", 200);
			    jsonObject.put("message", "Product price was edited successfully");
			    
			}
			catch(SQLException ex) {
				ex.printStackTrace();
				jsonObject.put("statusCode", 400);
				jsonObject.put("message", "Some error occurred");
			}
			return jsonObject;
    }
}
