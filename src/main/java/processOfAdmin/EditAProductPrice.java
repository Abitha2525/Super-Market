package processOfAdmin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import classesOfAdmin.EditAProductClass;
import classesOfAdmin.EditAProductPriceClass;

/**
 * Servlet implementation class EditAProductPrice
 */
@WebServlet("/admin/EditAProductPrice")
public class EditAProductPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAProductPrice() {
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
		
		String category = request.getParameter("category");
		String oldName = request.getParameter("oldName");
		double price = Double.valueOf(request.getParameter("price"));
		
		Date date = new Date();
		java.sql.Date currentDate = new java.sql.Date(date.getTime());
		
		EditAProductPriceClass obj = new EditAProductPriceClass(category, oldName, price, currentDate);
		
		JSONObject jsonObject =  obj.editAProductPrice(obj);
		response.getWriter().append(jsonObject.toString());
	}

}
