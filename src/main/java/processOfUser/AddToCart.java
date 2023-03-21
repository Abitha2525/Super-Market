package processOfUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ClassesOfUser.BuyingProducts;
import fullTimeUse.ConstantVariables;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/user/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		
		String product = request.getParameter("product");
		String quantity = request.getParameter("quantity");
		JSONObject jsonObject  = new JSONObject();
		Date date = new Date();
		java.sql.Date currentDate = new java.sql.Date(date.getTime());
		
		Cookie[] cookies = request.getCookies();
		String mobileNumber = "";
		try {
		for(int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("SESSIONID")) {
					PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select mobileNumber from sessions where sessionId = ?");
					ppst.setString(1, cookies[i].getValue());
					ResultSet rs = ppst.executeQuery();
					if(rs.next()) {
						mobileNumber = rs.getString(1);
					}
				}
			}
		
		jsonObject = BuyingProducts.addProductsToCart(product, quantity, currentDate, mobileNumber);
	}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		response.getWriter().append(jsonObject.toString());
}
}


//<div id = "adminClick" onclick = "admin()" class = "admin">Admin</div>
