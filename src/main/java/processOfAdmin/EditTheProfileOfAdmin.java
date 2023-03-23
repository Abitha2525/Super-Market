package processOfAdmin;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.mysql.cj.exceptions.DataTruncationException;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import fullTimeUse.ConstantVariables;

/**
 * Servlet implementation class EditTheProfileOfAdmin
 */
@WebServlet("/admin/EditTheProfileOfAdmin")
public class EditTheProfileOfAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTheProfileOfAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		RequestDispatcher rs = request.getRequestDispatcher("index2.html");
//		rs.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "1800");
		response.setHeader("Access-Control-Allow-Headers", "content-type");
		response.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");
		
		JSONObject jsonObject = new JSONObject();
		
		try {
		String adminNumber = request.getParameter("adminMobileNumberInProfile");
		int adminPin = Integer.valueOf(request.getParameter("adminPinNumberInProfile"));
		String oldNumber = request.getParameter("oldNumber");
		int oldPin = Integer.valueOf(request.getParameter("oldPin"));
		
		String regex = "[0-9]*$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(adminNumber);
		if(m.matches() && !adminNumber.equals("") && adminNumber.length() == 10) {
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("update LoginUsers set mobileNumber = ? where mobileNumber = ?");
			ppst.setString(1, adminNumber);
			ppst.setString(2, oldNumber);
			ppst.executeUpdate();
			
			ppst = ConstantVariables.dbConnection.prepareStatement("update LoginUsers set pinNumber = ? where mobileNumber = ?");
			ppst.setInt(1, adminPin);
			ppst.setString(2, oldNumber);
			ppst.executeUpdate();
			jsonObject.put("statusCode", 200);
			jsonObject.put("message", "Changes saved");
		}
		else {
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Mobile number must have 10 digits...");
		}
		}
		catch(MysqlDataTruncation ex) {
			ex.printStackTrace();
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Mobile number must have 10 digits");
		}
		catch(NumberFormatException ex) {
			ex.printStackTrace();
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Only enter digits please....");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Mobile number/ pin number is already taken");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Some error occurred");
		}
		response.getWriter().append(jsonObject.toString());
	}

}
