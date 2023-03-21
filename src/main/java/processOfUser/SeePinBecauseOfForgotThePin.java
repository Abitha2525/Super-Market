package processOfUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

/**
 * Servlet implementation class SeePinBecauseOfForgotThePin
 */
@WebServlet("/SeePinBecauseOfForgotThePin")
public class SeePinBecauseOfForgotThePin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeePinBecauseOfForgotThePin() {
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
		
		
		String mobile = request.getParameter("mobile");
		JSONObject jsonObject = new JSONObject();
		
		String regex = "[0-9]*$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mobile);
		long pin = 0;
		
		if(m.matches() && mobile.length() == 10) {
			try {
				PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select pinNumber from LoginUsers where mobileNumber = ?");
				ppst.setString(1, mobile);
				ResultSet rs = ppst.executeQuery();
				if(rs.next()) {
					pin = rs.getLong(1);
				}
				if(pin != 0) {
					jsonObject.put("statusCode", 200);
					jsonObject.put("pin", pin);
				}
				else {
					jsonObject.put("statusCode", 400);
					jsonObject.put("message", "You are not a logged in user");
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
				jsonObject.put("statusCode", 400);
				jsonObject.put("message", "Some error occurred");
			}
		}
		else {
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Mobile number must have 10 digits");
		}
		response.getWriter().append(jsonObject.toString());
	}

}
