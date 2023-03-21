package processOfUser;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import ClassesOfUser.BuyingProducts;
import ClassesOfUser.FinalOrder;

/**
 * Servlet implementation class BillPayment
 */
@WebServlet("/user/BillPayment")
public class BillPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillPayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date = new Date();
		java.sql.Date currentDate = new java.sql.Date(date.getTime());
		
		double totalAmount = BuyingProducts.billCalculation(currentDate);
		BuyingProducts.changeProductDetailsAfterPurchase(currentDate, totalAmount);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "1800");
		response.setHeader("Access-Control-Allow-Headers", "content-type");
		response.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");
		
		
		Date date = new Date();
		java.sql.Date currentDate = new java.sql.Date(date.getTime());
		
		double totalAmount = BuyingProducts.billCalculation(currentDate);
		int pin = BuyingProducts.findPinNumberOfUser();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("amount", totalAmount);
		jsonObject.put("pin", pin);
		response.getWriter().append(jsonObject.toString());
	}

}
