package processOfAdmin;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import classesOfAdmin.ProcessingMethods1;
import fullTimeUse.ConstantVariables;

/**
 * Servlet implementation class ViewThingsWhichBoughtByCustomer
 */
@WebServlet("/admin/ViewThingsWhichBoughtByCustomer")
public class ViewThingsWhichBoughtByCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewThingsWhichBoughtByCustomer() {
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
		
		ProcessingMethods1 obj = new ProcessingMethods1();
		String pin = request.getParameter("pin");
		JSONObject jsonObject = new JSONObject();
		PreparedStatement ppst = null;
		try {
		long pinNumber = Long.parseLong(pin);
		ppst = ConstantVariables.dbConnection.prepareStatement("select name from LoginUsers where pinNumber = ?");
		ppst.setLong(1, pinNumber);
		ResultSet rs = ppst.executeQuery();
		if(rs.next() && pinNumber != 1000) {
		JSONArray jsonArray = obj.findThingsBoughtByCustomer(pinNumber);
		
		jsonObject.put("statusCode", 200);
		jsonObject.put("list", jsonArray);
		}
		else {
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Please enter a valid user's pin number ");
		}
		}
		catch(NumberFormatException ex) {
			ex.printStackTrace();
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Please enter a valid pin number");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Some error occurred");
		}
		response.getWriter().append(jsonObject.toString());
	}

}
