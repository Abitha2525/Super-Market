package ClassesOfUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

public class ShowProducts {

	public static JSONArray showProducts(String category, String product) {
		JSONArray jsonArray = new JSONArray();
		int categoryId = 0;
		try {
			if(!category.equals("All")) {
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select categoryId from Categories where category = ?");
			ppst.setString(1, category);
			ResultSet rs = ppst.executeQuery();
			
			if(rs.next()) {
				categoryId = rs.getInt(1);
			}
		    ppst = ConstantVariables.dbConnection.prepareStatement("select productName, productCount, price, urlOfProducts from Products where productName like ? and status = ? and categoryId = ?" );
		    ppst.setString(1, "%" + product + "%");
		    ppst.setString(2, "Available");
		    ppst.setInt(3, categoryId);
		    ResultSet rs1 = ppst.executeQuery();
		    
		    
		    while(rs1.next()) {
		    	
		    	int urlId = rs1.getInt(4);
		    	String url = "";
		    	ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
		    	ppst.setInt(1, urlId);
		    	ResultSet rs2 = ppst.executeQuery();
		    	
		    	if(rs2.next()) {
		    		url = rs2.getString(1);
		    	}
		    	
		    	JSONObject jsonObject = new JSONObject();
		    	jsonObject.put("product", rs1.getString(1));
		    	jsonObject.put("quantity", rs1.getInt(2));
		    	jsonObject.put("price", rs1.getDouble(3));
		    	jsonObject.put("url", url);
		    	jsonArray.put(jsonObject);
		    }
			}
		    else {
			   PreparedStatement  ppst = ConstantVariables.dbConnection.prepareStatement("select productName, productCount, price, urlOfProducts from Products where productName like ? and status = ?" );
			    ppst.setString(1, "%" + product + "%");
			    ppst.setString(2, "Available");
			    ResultSet rs1 = ppst.executeQuery();
			    
			    
			    while(rs1.next()) {
			    	
			    	int urlId = rs1.getInt(4);
			    	String url = "";
			    	ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
			    	ppst.setInt(1, urlId);
			    	ResultSet rs2 = ppst.executeQuery();
			    	
			    	if(rs2.next()) {
			    		url = rs2.getString(1);
			    	}
			    	
			    	JSONObject jsonObject = new JSONObject();
			    	jsonObject.put("product", rs1.getString(1));
			    	jsonObject.put("quantity", rs1.getInt(2));
			    	jsonObject.put("price", rs1.getDouble(3));
			    	jsonObject.put("url", url);
			    	jsonArray.put(jsonObject);
		    }
		    	
		    	
		    }
		}
		    
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonArray;
	}
}
