package processOfAdmin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import classesOfAdmin.ProcessingMethods;

/**
 * Servlet implementation class ChangesBetweenTwoDates
 */
@WebServlet("/admin/ChangesBetweenTwoDates")
public class ChangesBetweenTwoDates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangesBetweenTwoDates() {
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
		
		
		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");
		String category = request.getParameter("category");
		System.out.println(category);
		java.sql.Date startingDate = null;
		java.sql.Date endingDate = null;
		
		JSONObject jsonObject = new JSONObject();
		
		if(!date1.equals("") || !date2.equals("")) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = date.parse(date1);
			endDate = date.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		startingDate = new java.sql.Date(startDate.getTime());
		endingDate = new java.sql.Date(endDate.getTime());
		System.out.println(startingDate + " ............ " + endingDate);
		if(endingDate.getTime() < startingDate.getTime()) {
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Last date is before the start date...Enter valid date");
		}
		else {
		ProcessingMethods obj = new ProcessingMethods();
		JSONArray jsonArray = obj.viewChangesBetweenTwoDates(startingDate, endingDate, category);
	    jsonObject.put("list", jsonArray);
	    jsonObject.put("statusCode", 200);
		}
		}
		else {
		    jsonObject.put("statusCode", 400);
		    jsonObject.put("message", "Please fill two dates");
		}
		response.getWriter().append(jsonObject.toString());
	}

}
