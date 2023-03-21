package classesOfAdmin;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

public class ProcessingMethods {

	
	
	
	
	
	public JSONObject getAllProducts(String items, String table) {
		JSONArray jsonArray = new JSONArray();
		JSONObject js2 = new JSONObject();
		int id = 0;
		String url = "";
	   if(!items.equals("")) {
		try {
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select categoryId from Categories where category = ?");
			ppst.setString(1, table);
			ResultSet rs = ppst.executeQuery();
			if(rs.next()) {
				id = rs.getInt(1);
			}
			if(id != 0) {
		    ppst = ConstantVariables.dbConnection.prepareStatement("select productName, productCount, urlOfProducts, price from Products where categoryId = ? and productName like ? and status = ?");
		    ppst.setInt(1, id);
		    ppst.setString(2, "%"+items+"%");
		    ppst.setString(3, "Available");
		    ResultSet rs1 = ppst.executeQuery();
		    
		    while(rs1.next()) {
		    	
		    	ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
				ppst.setInt(1, rs1.getInt(3));
				ResultSet rs3 = ppst.executeQuery();
				
				if(rs3.next()) {
					url = rs3.getString(1);
				}
				
				JSONObject js = new JSONObject();
				Map<String, Integer> map = new LinkedHashMap<>();
				map.put(rs1.getString(1), rs1.getInt(2));
				js.put("item", map);
				js.put("url", url);
				js.put("price", rs1.getDouble(4));
				jsonArray.put(js);
			}
			}
			else {
				ppst = ConstantVariables.dbConnection.prepareStatement("select productName, productCount, urlOfProducts, price from Products where productName like ? and status = ?");
			    ppst.setString(1, "%"+items+"%");
			    ppst.setString(2, "Available");
			    ResultSet rs1 = ppst.executeQuery();
			    while(rs1.next()) {
			    	
			    	ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
					ppst.setInt(1, rs1.getInt(3));
					ResultSet rs3 = ppst.executeQuery();
					
					if(rs3.next()) {
						url = rs3.getString(1);
					}
					
					JSONObject js = new JSONObject();
					Map<String, Integer> map = new LinkedHashMap<>();
					map.put(rs1.getString(1), rs1.getInt(2));
					js.put("item", map);
					jsonArray.put(js);
					js.put("url", url);
					js.put("price", rs1.getDouble(4));
				}
			}
		
		js2.put("statusCode", 200);
		js2.put("arr", jsonArray);		
		
		}
		catch(Exception ex) {
			ex.printStackTrace();
			js2.put("statusCode", 400);
			js2.put("message", "Some error occurred");
		}
	   }
		else {
			js2.put("statusCode", 400);
			js2.put("message", "Please fill the product name");
		}
		return js2;
	}
	
	//...............................................................................................
	
	public JSONObject getAllProductsFromSpecificCategory(String table) {
		JSONArray jsonArray = new JSONArray();
		JSONObject js2 = new JSONObject();
		int id = 0;
		String url = "";
	
		try {
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select categoryId from Categories where category = ?");
			ppst.setString(1, table);
			ResultSet rs = ppst.executeQuery();
			if(rs.next()) {
				id = rs.getInt(1);
			}
			System.out.println(id);
			if(id != 0) {
				
//				ppst = ConstantVariables.dbConnection.prepareStatement("select * from Products joins urlTable on Products.urlOfProducts = urlTable.urlOfProducts");
//				ResultSet r = ppst.executeQuery();
//				
				ppst = ConstantVariables.dbConnection.prepareStatement("select productName, productCount, urlOfProducts, price from Products where categoryId = ? and status = ?");
				ppst.setInt(1, id);
				ppst.setString(2, "Available");
				ResultSet rs1 = ppst.executeQuery();
				  while(rs1.next()) {
					  
					  ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
						ppst.setInt(1, rs1.getInt(3));
						ResultSet rs3 = ppst.executeQuery();
						
						if(rs3.next()) {
							url = rs3.getString(1);
						}
						
						
					  JSONObject js = new JSONObject();
						Map<String, Integer> map = new LinkedHashMap<>();
						map.put(rs1.getString(1), rs1.getInt(2));
						js.put("item", map);
						js.put("url", url);
						js.put("price", rs1.getDouble(4));
						jsonArray.put(js);
				  }
			}
			else {
				ppst = ConstantVariables.dbConnection.prepareStatement("select productName, productCount, urlOfProducts, price from Products where status = ?");
				ppst.setString(1, "Available");
				ResultSet rs2 = ppst.executeQuery();
				  while(rs2.next()) {
					  
					  ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
						ppst.setInt(1, rs2.getInt(3));
						ResultSet rs3 = ppst.executeQuery();
						
						if(rs3.next()) {
							url = rs3.getString(1);
						}
						
					  JSONObject js = new JSONObject();
						Map<String, Integer> map = new LinkedHashMap<>();
						map.put(rs2.getString(1), rs2.getInt(2));
						js.put("item", map);
						js.put("url", url);
						js.put("price", rs2.getDouble(4));
						jsonArray.put(js);
				  }
			}

		
		js2.put("statusCode", 200);
		js2.put("arr", jsonArray);		
		
		}
		catch(Exception ex) {
			ex.printStackTrace();
			js2.put("statusCode", 400);
			js2.put("message", "Some error occurred");
		}
		return js2;
	}
	
	//......................................................................................
	
	public JSONArray viewTodayChanges(String category, Date date) {
		JSONArray jsonArray = new JSONArray();
		try {
		JSONObject jsonObject = null;
		PreparedStatement ppst = null;
		if(category.equals("AddedProducts")) {
			boolean check = false;
			ppst = ConstantVariables.dbConnection.prepareStatement("select productId from DateDetails where insertDate = ?");
			ppst.setDate(1, date);
			ResultSet rs4 = ppst.executeQuery();
			while(rs4.next()) {
			ppst = ConstantVariables.dbConnection.prepareStatement("select productName, categoryId, productCount, price, urlOfProducts, status from Products where productId = ?");
			ppst.setInt(1, rs4.getInt(1));
			ResultSet rs = ppst.executeQuery();
			int id = 0;
			String categoryName = "";
			String url = "";
				if(rs.next()) {
					check = true;
					id = rs.getInt(2);
					ppst = ConstantVariables.dbConnection.prepareStatement("select category from Categories where categoryId = ?");
					ppst.setInt(1, id);
					ResultSet rs1 = ppst.executeQuery();
					if(rs1.next()) {
						categoryName = rs1.getString(1);
					}
					
					ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
					ppst.setInt(1, rs.getInt(5));
					ResultSet rs3 = ppst.executeQuery();
					
					if(rs3.next()) {
						url = rs3.getString(1);
					}
			    jsonObject = new JSONObject();
				jsonObject.put("category", categoryName);
				jsonObject.put("productName", rs.getString(1));
				jsonObject.put("quantity", rs.getInt(3));
				jsonObject.put("price", rs.getDouble(4));
				jsonObject.put("url", url);
				jsonObject.put("status", rs.getString(6));
				jsonArray.put(jsonObject);
				
				}
			}
			if(!check) {
				jsonObject = new JSONObject();
				jsonObject.put("category", "-");
				jsonObject.put("productName", "-");
				jsonObject.put("quantity", "-");
				jsonObject.put("price", "-");
				jsonObject.put("url", "-");
				jsonObject.put("status", "-");
				jsonArray.put(jsonObject);
				
			}
		}
		else if(category.equals("DeletedProducts")) {
			boolean check = false;
			ppst = ConstantVariables.dbConnection.prepareStatement("select productId from DateDetails where deleteDate = ?");
			ppst.setDate(1, date);
			ResultSet rs4 = ppst.executeQuery();
			while(rs4.next()) {
			ppst = ConstantVariables.dbConnection.prepareStatement("select productName, categoryId, urlOfProducts, price from Products where productId = ? and status = ?");
			ppst.setInt(1, rs4.getInt(1));
			ppst.setString(2, "Deleted");
			ResultSet rs = ppst.executeQuery();
			int id = 0;
			String categoryName = "";
			String url = "";
				if(rs.next()) {
					check = true;
					id = rs.getInt(2);
					ppst = ConstantVariables.dbConnection.prepareStatement("select category from Categories where categoryId = ?");
					ppst.setInt(1, id);
					ResultSet rs1 = ppst.executeQuery();
					if(rs1.next()) {
						categoryName = rs1.getString(1);
					}
					
					ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
					ppst.setInt(1, rs.getInt(3));
					ResultSet rs3 = ppst.executeQuery();
					
					if(rs3.next()) {
						url = rs3.getString(1);
					}
			    jsonObject = new JSONObject();
				jsonObject.put("category", categoryName);
				jsonObject.put("productName", rs.getString(1));
				jsonObject.put("url", url);
				jsonObject.put("price", rs.getDouble(4));
				jsonArray.put(jsonObject);
				}
			}
					
			if(!check) {
				jsonObject = new JSONObject();
				jsonObject.put("category", "-");
				jsonObject.put("productName", "-");
				jsonObject.put("url", "-");
				jsonObject.put("price", "-");
				jsonArray.put(jsonObject);
				
			}
		}
		else if(category.equals("EditedProducts")) {
			boolean check = false;
			ppst = ConstantVariables.dbConnection.prepareStatement("select productId from DateDetails where editDate = ?");
			ppst.setDate(1, date);
			ResultSet rs4 = ppst.executeQuery();
			while(rs4.next()) {
			ppst = ConstantVariables.dbConnection.prepareStatement("select productName, categoryId, OldName, urlOfProducts, price, status from Products where productId = ?");
			ppst.setInt(1, rs4.getInt(1));
			ResultSet rs = ppst.executeQuery();
			int id = 0;
			String categoryName = "";
			String url = "";
				if(rs.next()) {
					check = true;
					id = rs.getInt(2);
					ppst = ConstantVariables.dbConnection.prepareStatement("select category from Categories where categoryId = ?");
					ppst.setInt(1, id);
					ResultSet rs1 = ppst.executeQuery();
					if(rs1.next()) {
						categoryName = rs1.getString(1);
					}
					
					ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
					ppst.setInt(1, rs.getInt(4));
					ResultSet rs3 = ppst.executeQuery();
					
					if(rs3.next()) {
						url = rs3.getString(1);
					}
			    jsonObject = new JSONObject();
				jsonObject.put("category", categoryName);
				jsonObject.put("productNewName", rs.getString(1));
				jsonObject.put("productOldName", rs.getString(3));
				jsonObject.put("url", url);
				jsonObject.put("price", rs.getDouble(5));
				jsonObject.put("status", rs.getString(6));
				jsonArray.put(jsonObject);
				}
			}
			
			if(!check) {
				jsonObject = new JSONObject();
				jsonObject.put("category", "-");
				jsonObject.put("productNewName", "-");
				jsonObject.put("productOldName", "-");
				jsonObject.put("url", "-");
				jsonObject.put("price", "-");
				jsonObject.put("status", "-");
				jsonArray.put(jsonObject);
				
			}
			
		}
		else if(category.equals("EditedProductsPrices")) {
			System.out.println("success");
			boolean check = false;
			ppst = ConstantVariables.dbConnection.prepareStatement("select productId from DateDetails where editPriceDate = ?");
			ppst.setDate(1, date);
			ResultSet rs4 = ppst.executeQuery();
			while(rs4.next()) {
			ppst = ConstantVariables.dbConnection.prepareStatement("select productName, categoryId, oldPrice, urlOfProducts, price, status from Products where productId = ?");
			ppst.setInt(1, rs4.getInt(1));
			ResultSet rs = ppst.executeQuery();
			int id = 0;
			String categoryName = "";
			String url = "";
				if(rs.next()) {
					check = true;
					id = rs.getInt(2);
					ppst = ConstantVariables.dbConnection.prepareStatement("select category from Categories where categoryId = ?");
					ppst.setInt(1, id);
					ResultSet rs1 = ppst.executeQuery();
					if(rs1.next()) {
						categoryName = rs1.getString(1);
					}
					
					ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
					ppst.setInt(1, rs.getInt(4));
					ResultSet rs3 = ppst.executeQuery();
					
					if(rs3.next()) {
						url = rs3.getString(1);
					}
			    jsonObject = new JSONObject();
				jsonObject.put("category", categoryName);
				jsonObject.put("productName", rs.getString(1));
				jsonObject.put("productOldPrice", rs.getString(3));
				jsonObject.put("url", url);
				jsonObject.put("price", rs.getDouble(5));
				jsonObject.put("status", rs.getString(6));
				jsonArray.put(jsonObject);
				}
			}
			
			if(!check) {
				jsonObject = new JSONObject();
				jsonObject.put("category", "-");
				jsonObject.put("productName", "-");
				jsonObject.put("productOldPrice", "-");
				jsonObject.put("url", "-");
				jsonObject.put("price", "-");
				jsonObject.put("status", "-");
				jsonArray.put(jsonObject);
				
			}
			
		}
		else if(category.equals("EditedProductsImages")) {
			System.out.println("success");
			boolean check = false;
			ppst = ConstantVariables.dbConnection.prepareStatement("select productId from DateDetails where editImageDate = ?");
			ppst.setDate(1, date);
			ResultSet rs4 = ppst.executeQuery();
			while(rs4.next()) {
			ppst = ConstantVariables.dbConnection.prepareStatement("select productName, categoryId, oldImage, urlOfProducts, price, status from Products where productId = ?");
			ppst.setInt(1, rs4.getInt(1));
			ResultSet rs = ppst.executeQuery();
			int id = 0;
			String categoryName = "";
			String url = "";
			String oldImage = "";
				if(rs.next()) {
					check = true;
					id = rs.getInt(2);
					ppst = ConstantVariables.dbConnection.prepareStatement("select category from Categories where categoryId = ?");
					ppst.setInt(1, id);
					ResultSet rs1 = ppst.executeQuery();
					if(rs1.next()) {
						categoryName = rs1.getString(1);
					}
					
					ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
					ppst.setInt(1, rs.getInt(4));
					ResultSet rs3 = ppst.executeQuery();
					
					if(rs3.next()) {
						url = rs3.getString(1);
					}
					
					ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
					ppst.setInt(1, rs.getInt(3));
					ResultSet rs5 = ppst.executeQuery();
					if(rs5.next()) {
						oldImage = rs5.getString(1);
					}
			    jsonObject = new JSONObject();
				jsonObject.put("category", categoryName);
				jsonObject.put("productName", rs.getString(1));
				jsonObject.put("productOldImage", oldImage);
				jsonObject.put("url", url);
				jsonObject.put("price", rs.getDouble(5));
				jsonObject.put("status", rs.getString(6));
				jsonArray.put(jsonObject);
				}
			}
			
			if(!check) {
				jsonObject = new JSONObject();
				jsonObject.put("category", "-");
				jsonObject.put("productName", "-");
				jsonObject.put("productOldImage", "-");
				jsonObject.put("url", "-");
				jsonObject.put("price", "-");
				jsonObject.put("status", "-");
				jsonArray.put(jsonObject);
				
			}
			
		}
		else if(category.equals("QuantityIncreasedProducts")) {
			boolean check = false;
			ppst = ConstantVariables.dbConnection.prepareStatement("select productId from DateDetails where quantityIncreaseDate = ?");
			ppst.setDate(1, date);
			ResultSet rs4 = ppst.executeQuery();
			while(rs4.next()) {
			ppst = ConstantVariables.dbConnection.prepareStatement("select productName, categoryId, initialQuantity, productCount, urlOfProducts, price, status from Products where productId = ?");
			ppst.setInt(1, rs4.getInt(1));
			ResultSet rs = ppst.executeQuery();
			int id = 0;
			String categoryName = "";
			String url = "";
				if(rs.next()) {
					check = true;
					id = rs.getInt(2);
					ppst = ConstantVariables.dbConnection.prepareStatement("select category from Categories where categoryId = ?");
					ppst.setInt(1, id);
					ResultSet rs1 = ppst.executeQuery();
					if(rs1.next()) {
						categoryName = rs1.getString(1);
					}
					
					ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
					ppst.setInt(1, rs.getInt(5));
					ResultSet rs3 = ppst.executeQuery();
					
					if(rs3.next()) {
						url = rs3.getString(1);
					}
			    jsonObject = new JSONObject();    
				jsonObject.put("category", categoryName);
				jsonObject.put("productName", rs.getString(1));
				jsonObject.put("startingQuantity", rs.getInt(3));
				jsonObject.put("quantity", rs.getInt(4));
				jsonObject.put("url", url);
				jsonObject.put("price", rs.getDouble(6));
				jsonObject.put("status", rs.getString(7));
				jsonArray.put(jsonObject);
				}
			}
				
			if(!check) {
				jsonObject = new JSONObject();
				jsonObject.put("category", "-");
				jsonObject.put("productName", "-");
				jsonObject.put("startingQuantity", "-");
				jsonObject.put("quantity", "-");
				jsonObject.put("url", "-");
				jsonObject.put("price", "-");
				jsonObject.put("status", "-");
				jsonArray.put(jsonObject);
				
			}

		}
	}
   catch(Exception ex) {
	   ex.printStackTrace();
   }
		return jsonArray;
	}
	
	//....................................................................................
	
	public JSONArray viewChangesBetweenTwoDates(Date startDate, Date endDate, String category) {
		JSONArray jsonArray = new JSONArray();
		
		PreparedStatement ppst = null;
		JSONObject jsonObject = null;
		SimpleDateFormat datFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if(category.equals("AddedProducts")) {
				boolean check = false;
				ppst = ConstantVariables.dbConnection.prepareStatement("select productId, insertDate from DateDetails where insertDate between ? and ?");
				ppst.setDate(1, startDate);
				ppst.setDate(2, endDate);
				ResultSet rs4 = ppst.executeQuery();
				while(rs4.next()) {
				ppst = ConstantVariables.dbConnection.prepareStatement("select productName, categoryId, productCount, price, urlOfProducts, status from Products where productId = ?");
				ppst.setInt(1, rs4.getInt(1));
				ResultSet rs = ppst.executeQuery();
				
				int id = 0;
				String categoryName = "";
				String url = "";
					if(rs.next()) {
						check = true;
						id = rs.getInt(2);
						ppst = ConstantVariables.dbConnection.prepareStatement("select category from Categories where categoryId = ?");
						ppst.setInt(1, id);
						ResultSet rs1 = ppst.executeQuery();
						if(rs1.next()) {
							categoryName = rs1.getString(1);
						}
						
						ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
						ppst.setInt(1, rs.getInt(5));
						ResultSet rs3 = ppst.executeQuery();
						
						if(rs3.next()) {
							url = rs3.getString(1);
						}
				    jsonObject = new JSONObject();
					jsonObject.put("category", categoryName);
					jsonObject.put("productName", rs.getString(1));
					jsonObject.put("quantity", rs.getInt(3));
					jsonObject.put("price", rs.getDouble(4));
					jsonObject.put("url", url);
					jsonObject.put("date", datFormat.format(rs4.getDate(2)));
					jsonObject.put("status", rs.getString(6));
					jsonArray.put(jsonObject);					
					}
				}
				if(!check) {
					jsonObject = new JSONObject();
					jsonObject.put("category", "-");
					jsonObject.put("productName", "-");
					jsonObject.put("quantity", "-");
					jsonObject.put("price", "-");
					jsonObject.put("url", "-");
					jsonObject.put("date", "-");
					jsonObject.put("status", "-");
					jsonArray.put(jsonObject);
					
			}
		}
			else if(category.equals("DeletedProducts")) {
				boolean check = false;
				
				ppst = ConstantVariables.dbConnection.prepareStatement("select productId, deleteDate from DateDetails where deleteDate between ? and ?");
				ppst.setDate(1, startDate);
				ppst.setDate(2, endDate);
				ResultSet rs4 = ppst.executeQuery();
				
				while(rs4.next()) {
				ppst = ConstantVariables.dbConnection.prepareStatement("select productName, categoryId, urlOfProducts, price from Products where productId = ? and status = ?");
				ppst.setInt(1, rs4.getInt(1));
				ppst.setString(2, "Deleted");
				ResultSet rs = ppst.executeQuery();
				int id = 0;
				String categoryName = "";
				String url = "";
					if(rs.next()) {
						check = true;
						id = rs.getInt(2);
						ppst = ConstantVariables.dbConnection.prepareStatement("select category from Categories where categoryId = ?");
						ppst.setInt(1, id);
						ResultSet rs1 = ppst.executeQuery();
						if(rs1.next()) {
							categoryName = rs1.getString(1);
						}
						
						ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
						ppst.setInt(1, rs.getInt(3));
						ResultSet rs3 = ppst.executeQuery();
						
						if(rs3.next()) {
							url = rs3.getString(1);
						}
				    jsonObject = new JSONObject();
					jsonObject.put("category", categoryName);
					jsonObject.put("productName", rs.getString(1));
					jsonObject.put("url", url);
					jsonObject.put("price", rs.getDouble(4));
					jsonObject.put("date", datFormat.format(rs4.getDate(2)));
					jsonArray.put(jsonObject);
					}
						
				}
				if(!check) {
					jsonObject = new JSONObject();
					jsonObject.put("category", "-");
					jsonObject.put("productName", "-");
					jsonObject.put("url", "-");
					jsonObject.put("price", "-");
					jsonObject.put("date", "-");
					jsonArray.put(jsonObject);
					
				}
			}
			else if(category.equals("EditedProducts")) {
				boolean check = false;
				ppst = ConstantVariables.dbConnection.prepareStatement("select productId, editDate from DateDetails where editDate between ? and ?");
				ppst.setDate(1, startDate);
				ppst.setDate(2, endDate);
				ResultSet rs4 = ppst.executeQuery();
				while(rs4.next()) {
				ppst = ConstantVariables.dbConnection.prepareStatement("select productName, categoryId, OldName, urlOfProducts, price, status from Products where productId = ?");
				ppst.setInt(1, rs4.getInt(1));
				ResultSet rs = ppst.executeQuery();
				int id = 0;
				String categoryName = "";
				String url = "";
					if(rs.next()) {
						check = true;
						id = rs.getInt(2);
						ppst = ConstantVariables.dbConnection.prepareStatement("select category from Categories where categoryId = ?");
						ppst.setInt(1, id);
						ResultSet rs1 = ppst.executeQuery();
						if(rs1.next()) {
							categoryName = rs1.getString(1);
						}
						
						ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
						ppst.setInt(1, rs.getInt(4));
						ResultSet rs3 = ppst.executeQuery();
						
						if(rs3.next()) {
							url = rs3.getString(1);
						}
				    jsonObject = new JSONObject();
					jsonObject.put("category", categoryName);
					jsonObject.put("productNewName", rs.getString(1));
					jsonObject.put("productOldName", rs.getString(3));
					jsonObject.put("url", url);
					jsonObject.put("price", rs.getDouble(5));
					jsonObject.put("date", datFormat.format(rs4.getDate(2)));
					jsonObject.put("status", rs.getString(6));
					jsonArray.put(jsonObject);
					}
				}
				
				if(!check) {
					jsonObject = new JSONObject();
					jsonObject.put("category", "-");
					jsonObject.put("productNewName", "-");
					jsonObject.put("productOldName", "-");
					jsonObject.put("url", "-");
					jsonObject.put("price", "-");
					jsonObject.put("date", "-");
					jsonObject.put("status", "-");
					jsonArray.put(jsonObject);
					
				}
			}
			else if(category.equals("EditedProductsPrices")) {
				boolean check = false;
				ppst = ConstantVariables.dbConnection.prepareStatement("select productId, editPriceDate from DateDetails where editPriceDate between ? and ?");
				ppst.setDate(1, startDate);
				ppst.setDate(2, endDate);
				ResultSet rs4 = ppst.executeQuery();
				while(rs4.next()) {
				ppst = ConstantVariables.dbConnection.prepareStatement("select productName, categoryId, oldPrice, urlOfProducts, price, status from Products where productId = ?");
				ppst.setInt(1, rs4.getInt(1));
				ResultSet rs = ppst.executeQuery();
				int id = 0;
				String categoryName = "";
				String url = "";
					if(rs.next()) {
						check = true;
						id = rs.getInt(2);
						ppst = ConstantVariables.dbConnection.prepareStatement("select category from Categories where categoryId = ?");
						ppst.setInt(1, id);
						ResultSet rs1 = ppst.executeQuery();
						if(rs1.next()) {
							categoryName = rs1.getString(1);
						}
						
						ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
						ppst.setInt(1, rs.getInt(4));
						ResultSet rs3 = ppst.executeQuery();
						
						if(rs3.next()) {
							url = rs3.getString(1);
						}
				    jsonObject = new JSONObject();
					jsonObject.put("category", categoryName);
					jsonObject.put("productName", rs.getString(1));
					jsonObject.put("productOldPrice", rs.getDouble(3));
					jsonObject.put("url", url);
					jsonObject.put("price", rs.getDouble(5));
					jsonObject.put("date", datFormat.format(rs4.getDate(2)));
					jsonObject.put("status", rs.getString(6));
					jsonArray.put(jsonObject);
					}
				}
				
				if(!check) {
					jsonObject = new JSONObject();
					jsonObject.put("category", "-");
					jsonObject.put("productNewName", "-");
					jsonObject.put("productOldPrice", "-");
					jsonObject.put("url", "-");
					jsonObject.put("price", "-");
					jsonObject.put("date", "-");
					jsonObject.put("status", "-");
					jsonArray.put(jsonObject);
					
				}
			}
			else if(category.equals("EditedProductsImages")) {
				boolean check = false;
				ppst = ConstantVariables.dbConnection.prepareStatement("select productId, editImageDate from DateDetails where editImageDate between ? and ?");
				ppst.setDate(1, startDate);
				ppst.setDate(2, endDate);
				ResultSet rs4 = ppst.executeQuery();
				while(rs4.next()) {
				ppst = ConstantVariables.dbConnection.prepareStatement("select productName, categoryId, OldName, urlOfProducts, price, status from Products where productId = ?");
				ppst.setInt(1, rs4.getInt(1));
				ResultSet rs = ppst.executeQuery();
				int id = 0;
				String categoryName = "";
				String url = "";
					if(rs.next()) {
						check = true;
						id = rs.getInt(2);
						ppst = ConstantVariables.dbConnection.prepareStatement("select category from Categories where categoryId = ?");
						ppst.setInt(1, id);
						ResultSet rs1 = ppst.executeQuery();
						if(rs1.next()) {
							categoryName = rs1.getString(1);
						}
						
						ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
						ppst.setInt(1, rs.getInt(4));
						ResultSet rs3 = ppst.executeQuery();
						
						if(rs3.next()) {
							url = rs3.getString(1);
						}
				    jsonObject = new JSONObject();
					jsonObject.put("category", categoryName);
					jsonObject.put("productNewName", rs.getString(1));
					jsonObject.put("productOldName", rs.getString(3));
					jsonObject.put("url", url);
					jsonObject.put("price", rs.getDouble(5));
					jsonObject.put("date", datFormat.format(rs4.getDate(2)));
					jsonObject.put("status", rs.getString(6));
					jsonArray.put(jsonObject);
					}
				}
				
				if(!check) {
					jsonObject = new JSONObject();
					jsonObject.put("category", "-");
					jsonObject.put("productNewName", "-");
					jsonObject.put("productOldName", "-");
					jsonObject.put("url", "-");
					jsonObject.put("price", "-");
					jsonObject.put("date", "-");
					jsonObject.put("status", "-");
					jsonArray.put(jsonObject);
					
				}
			}
			else if(category.equals("QuantityIncreasedProducts")) {
				boolean check = false;
				ppst = ConstantVariables.dbConnection.prepareStatement("select productId, quantityIncreaseDate from DateDetails where quantityIncreaseDate between ? and ?");
				ppst.setDate(1, startDate);
				ppst.setDate(2, endDate);
				ResultSet rs4 = ppst.executeQuery();
				while(rs4.next()) {
				ppst = ConstantVariables.dbConnection.prepareStatement("select productName, categoryId, initialQuantity, productCount, urlOfProducts, price, status from Products where productId = ?");
				ppst.setInt(1, rs4.getInt(1));
				ResultSet rs = ppst.executeQuery();
				int id = 0;
				String categoryName = "";
				String url = "";
					if(rs.next()) {
						check = true;
						id = rs.getInt(2);
						ppst = ConstantVariables.dbConnection.prepareStatement("select category from Categories where categoryId = ?");
						ppst.setInt(1, id);
						ResultSet rs1 = ppst.executeQuery();
						if(rs1.next()) {
							categoryName = rs1.getString(1);
						}
						
						ppst = ConstantVariables.dbConnection.prepareStatement("select url from urlTable where urlOfProducts = ?");
						ppst.setInt(1, rs.getInt(5));
						ResultSet rs3 = ppst.executeQuery();
						
						if(rs3.next()) {
							url = rs3.getString(1);
						}
				    jsonObject = new JSONObject();    
					jsonObject.put("category", categoryName);
					jsonObject.put("productName", rs.getString(1));
					jsonObject.put("startingQuantity", rs.getInt(3));
					jsonObject.put("quantity", rs.getInt(4));
					jsonObject.put("url", url);
					jsonObject.put("price", rs.getDouble(6));
					jsonObject.put("date", datFormat.format(rs4.getDate(2)));
					jsonObject.put("status", rs.getString(7));
					jsonArray.put(jsonObject);
					}
				}
				if(!check) {
					jsonObject = new JSONObject();
					jsonObject.put("category", "-");
					jsonObject.put("productName", "-");
					jsonObject.put("startingQuantity", "-");
					jsonObject.put("quantity", "-");
					jsonObject.put("url", "-");
					jsonObject.put("price", "-");
					jsonObject.put("date", "-");
					jsonObject.put("status", "-");
					jsonArray.put(jsonObject);
					
				}

			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonArray;

	}
	
	
	public JSONArray viewCustomers() {
		JSONArray jsonArray = new JSONArray();
		try {
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select name, mobileNumber, pinNumber from LoginUsers where role = ?");
			ppst.setString(1, "User");
			ResultSet rs = ppst.executeQuery();
			
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("customer", rs.getString(1));
				jsonObject.put("mobile", rs.getString(2));
				jsonObject.put("pin", rs.getInt(3));
				jsonArray.put(jsonObject);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonArray;
	}
	
	public JSONArray viewDailyIncome() {
		JSONArray jsonArray = new JSONArray();
		try {
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select currentDate, sum(amount) from dailyIncome group by currentDate order by currentDate");
			ResultSet rs = ppst.executeQuery();
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				SimpleDateFormat datFormat = new SimpleDateFormat("dd/MM/yyyy");
				jsonObject.put("date", datFormat.format(rs.getDate(1)));
				jsonObject.put("amount", rs.getDouble(2));
				jsonArray.put(jsonObject);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonArray;
	}
}

