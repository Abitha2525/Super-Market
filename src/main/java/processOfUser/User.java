package processOfUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import ClassesOfUser.BuyingProducts;
import ClassesOfUser.Users;
import fullTimeUse.ConstantVariables;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		JSONObject responseObject = new JSONObject();
			try {
				String mobileNumber = request.getParameter("userMobileNumber");
				String regex = "[0-9]*$";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(mobileNumber);
				if(m.matches() && !mobileNumber.equals("") && mobileNumber.length() == 10) {
				
				int pinNumber = Integer.valueOf(request.getParameter("userId"));
				String role = "";
				UUID uuid = UUID.randomUUID();
				Cookie cookie = new Cookie("SESSIONID", uuid.toString());
				response.addCookie(cookie);
				System.out.println(mobileNumber);
				PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select role, name, mobileNumber, pinNumber from LoginUsers where mobileNumber = ? and pinNumber = ?");
			    ppst.setString(1, mobileNumber);
			    ppst.setInt(2, pinNumber);
				ResultSet rs = ppst.executeQuery();
			    if(rs.next()) {
			    	role = rs.getString(1);
			    	System.out.println(role+ ".............");
			    }
			    
			    ppst = ConstantVariables.dbConnection.prepareStatement("insert into sessions values(?, ?)");
			    ppst.setString(1, uuid.toString());
			    ppst.setString(2, mobileNumber);
			    ppst.execute();
			    
			    Cookie roleCookie = new Cookie("ROLE", role);
				response.addCookie(roleCookie);
				
				BuyingProducts.list.clear();
				
			    if(role.equals("Admin")) {
			    	responseObject.put("statusCode", 201);
			    	responseObject.put("message", "Welcome Admin");
			    	responseObject.put("mobileNumber", mobileNumber);
			    	responseObject.put("pinNumber", pinNumber);
			    }
			    else if(role.equals("User")) {
			    	responseObject.put("statusCode", 200);
			    	responseObject.put("message", "Welcome " + rs.getString(2));
			    	responseObject.put("mobileNumber", mobileNumber);
			    	responseObject.put("pinNumber", pinNumber);
			    }
			    else {
			    	responseObject.put("statusCode", 400);
			    	responseObject.put("message", "You are not a logged in user");
			    }
			}
			else {
				responseObject.put("statusCode", 400);
				responseObject.put("message", "Mobile Number must have 10 digits");
			}
			}
			catch (NumberFormatException ex) {
				ex.printStackTrace();
				responseObject.put("statusCode", 400);
				responseObject.put("message", "Enter only the pin number");
			}
			catch(MysqlDataTruncation ex) {
				responseObject.put("statusCode", 400);
				responseObject.put("message", "Mobile Number must have 10 digits");
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			System.out.println(responseObject.toString());
		response.getWriter().append(responseObject.toString());
	}

	
}