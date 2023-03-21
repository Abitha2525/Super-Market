package ClassesOfUser;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

public class FinalOrder {
	static LinkedList<BuyingProducts> list = new BuyingProducts().getList();

	public static JSONArray addProductsToList() {
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < list.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("product", list.get(i).getProductName());
			jsonObject.put("quantity", list.get(i).getQuantity());
			jsonObject.put("price", list.get(i).getPrice());
			jsonObject.put("url", list.get(i).getUrl());
			jsonArray.put(jsonObject);
		}
		
		System.out.println(jsonArray+"\n\n");
		return jsonArray;
	}
}
