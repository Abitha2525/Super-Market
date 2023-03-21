package processOfUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import ClassesOfUser.BuyingProducts;
import fullTimeUse.ConstantVariables;
import fullTimeUse.DatabaseConnection;

/**
 * Servlet implementation class UserSignUp
 */
@WebServlet("/UserSignUp")
public class UserSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignUp() {
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
		
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		JSONObject jsonObject = new JSONObject();
		int max = 0;
		String regex = "[0-9]*$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mobile);
		if(m.matches() && !name.equals("") && mobile.length() == 10) {
			try {
				PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select max(pinNumber) from LoginUsers");
				ResultSet rs = ppst.executeQuery();
				if(rs.next()) {
					max = rs.getInt(1);
				}
				ppst = ConstantVariables.dbConnection.prepareStatement("insert into LoginUsers values(?, ?, ?, ?)");
				ppst.setString(1, name);
				ppst.setString(2, mobile);
				ppst.setInt(3, max+1);
				ppst.setString(4, "User");
				ppst.execute();
			
				UUID uuid = UUID.randomUUID();
				Cookie cookie = new Cookie("SESSIONID", uuid.toString());
				response.addCookie(cookie);
			    
			    ppst = ConstantVariables.dbConnection.prepareStatement("insert into sessions values(?, ?)");
			    ppst.setString(1, uuid.toString());
			    ppst.setString(2, mobile);
			    ppst.execute();
			    
			    Cookie roleCookie = new Cookie("ROLE", "User");
				response.addCookie(roleCookie);
			   
				BuyingProducts.list.clear();
				
			    
				jsonObject.put("statusCode", 200);
				jsonObject.put("message", "Welcome " + name + " !!!");
			}
			catch(SQLException ex) {
				ex.printStackTrace();
				jsonObject.put("statusCode", 400);
				jsonObject.put("message", "UserName/ MobileNumber already taken");
			}
			catch(Exception ex) {
				ex.printStackTrace();
				jsonObject.put("statusCode", 400);
				jsonObject.put("message", "UserName/ MobileNumber already taken");
			}
		}
		else {
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Mobile number must have 10 digits");
		}
		response.getWriter().append(jsonObject.toString());
	}

}
