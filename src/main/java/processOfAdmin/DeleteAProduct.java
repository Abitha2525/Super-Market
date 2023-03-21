package processOfAdmin;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import classesOfAdmin.DeleteAProductClass;
import classesOfAdmin.ProcessingMethods;
import fullTimeUse.ConstantVariables;

/**
 * Servlet implementation class DeleteAProduct
 */
@WebServlet("/admin/DeleteAProduct")
public class DeleteAProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAProduct() {
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
		
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		response.setHeader("Access-Control-Max-Age", "1800");
//		response.setHeader("Access-Control-Allow-Headers", "content-type");
//		response.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");
		
		String category = request.getParameter("category");
		String product = request.getParameter("product");
		
		Date date=new Date();
		java.sql.Date currentDate = new java.sql.Date(date.getTime());
		
		DeleteAProductClass obj = new DeleteAProductClass(category, product, currentDate);
		JSONObject jsonObject = obj.deleteAProduct(obj);
		response.getWriter().append(jsonObject.toString());
	}

}
