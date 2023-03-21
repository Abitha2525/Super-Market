package classesOfAdmin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

public class ProcessingMethods1 {

	public JSONArray findThingsBoughtByCustomer(long pin) {
		JSONArray jsonArray = new JSONArray();
		SimpleDateFormat datFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			long pinNumber = pin;
			boolean check = false;
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select saledDate, productId, quantity, urlOfProducts from saledProducts where pinNumber = ?");
			ppst.setLong(1, pinNumber);
			ResultSet rs = ppst.executeQuery();
	
			while(rs.next()) {
				ppst = ConstantVariables.dbConnection.prepareStatement("select productName from Products where productId = ?");
				ppst.setInt(1, rs.getInt(2));
				ResultSet rs1 = ppst.executeQuery();
				
				ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
				ppst.setInt(1, rs.getInt(4));
				ResultSet rs2 = ppst.executeQuery();
				
				if(rs1.next() && rs2.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("date", datFormat.format(rs.getDate(1)));
					jsonObject.put("product", rs1.getString(1));
					jsonObject.put("quantity", rs.getInt(3));
					jsonObject.put("url", rs2.getString(1));
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
