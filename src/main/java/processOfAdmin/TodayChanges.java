package processOfAdmin;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class TodayChanges
 */
@WebServlet("/admin/TodayChanges")
public class TodayChanges extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodayChanges() {
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
		
		JSONObject jsonObject = new JSONObject();
		String category = request.getParameter("category");
		Date date = new Date();
		java.sql.Date currentDate = new java.sql.Date(date.getTime());
		
		ProcessingMethods obj = new ProcessingMethods();
		JSONArray jsonArray = obj.viewTodayChanges(category, currentDate);
		
		jsonObject.put("statusCode", 200);
		jsonObject.put("list", jsonArray);
		
		response.getWriter().append(jsonObject.toString());
	}

}
