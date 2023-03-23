package ClassesOfUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

public class FavouritesDetails {

	public void addAndRemoveFromFavourites(String product, String n, String mobileNumber) {
		try {
		PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select productId from Products where productName = ? and status = ?");
		ppst.setString(1, product);
		ppst.setString(2, "Available");
		ResultSet rs = ppst.executeQuery();
		if(rs.next()) {
	
			ppst = ConstantVariables.dbConnection.prepareStatement("select pinNumber from LoginUsers where mobileNumber = ?");
			ppst.setString(1, mobileNumber);
			ResultSet rs2 = ppst.executeQuery();
			
			if(rs2.next()) {
				if(Integer.parseInt(n) == 1) {
				ppst = ConstantVariables.dbConnection.prepareStatement("insert into favouritesTable values(?, ?)");
				ppst.setInt(1, rs.getInt(1));
				ppst.setInt(2, rs2.getInt(1));
				ppst.execute();
				
			}
				else if(Integer.parseInt(n) == -1) {
					ppst = ConstantVariables.dbConnection.prepareStatement("delete from favouritesTable where productId = ? and pinNumber = ?");
					ppst.setInt(1, rs.getInt(1));
					ppst.setInt(2, rs2.getInt(1));
					ppst.execute();
				}
			}
		}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public JSONArray getProductsFromFavourites(String mobile) {
		JSONArray jsonArray = new JSONArray();
		try {
			long pin = 0;
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select pinNumber from LoginUsers where mobileNumber = ?");
			ppst.setString(1, mobile);
			ResultSet rs = ppst.executeQuery();
			if(rs.next()) {
				pin = rs.getInt(1);
			}
			
			ppst = ConstantVariables.dbConnection.prepareStatement("select productId from favouritesTable where pinNumber = ?");
			ppst.setInt(1, rs.getInt(1));
			ResultSet rs2 = ppst.executeQuery();
			
			while(rs2.next()) {
				ppst = ConstantVariables.dbConnection.prepareStatement("select productName, urlOfProducts, price from Products where productId = ?");
				ppst.setInt(1, rs2.getInt(1));
				ResultSet rs3 = ppst.executeQuery();
				
				while(rs3.next()) {
					String url = "";
					ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
					ppst.setInt(1, rs3.getInt(2));
					ResultSet rs4 = ppst.executeQuery();
					if(rs4.next()) {
						url = rs4.getString(1);
					}
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("product", rs3.getString(1));
					jsonObject.put("url", url);
					jsonObject.put("price", rs3.getDouble(3));
					jsonArray.put(jsonObject);
					
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonArray;
	}
	
	public void removeProductFromFavourites(String product, String mobile) {
		try {
			long pin = 0;
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select pinNumber from LoginUsers where mobileNumber = ?");
			ppst.setString(1, mobile);
			ResultSet rs = ppst.executeQuery();
			if(rs.next()) {
				pin = rs.getInt(1);
			}
			
			ppst = ConstantVariables.dbConnection.prepareStatement("select productId from Products where productName = ? and status = ?");
			ppst.setString(1, product);
			ppst.setString(2, "Available");
			ResultSet rs2 = ppst.executeQuery();
			if(rs2.next()) {
				ppst = ConstantVariables.dbConnection.prepareStatement("delete from favouritesTable where productId = ? and pinNumber = ?");
				ppst.setInt(1, rs2.getInt(1));
				ppst.setLong(2, pin);
				ppst.execute();
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
