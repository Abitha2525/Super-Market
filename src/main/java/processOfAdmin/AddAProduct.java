package processOfAdmin;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import classesOfAdmin.AddAProductClass;
import fullTimeUse.ConstantVariables;



/**
 * Servlet implementation class AddAProduct
 */
@WebServlet("/admin/AddAProduct")
public class AddAProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAProduct() {
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
		
		try {
		String category = request.getParameter("category");
		String item = request.getParameter("product");
		String productCount = request.getParameter("count");
		String price = request.getParameter("price");
		String url = request.getParameter("url");
		Date date=new Date();
		java.sql.Date currentDate = new java.sql.Date(date.getTime());
		
		AddAProductClass obj = new AddAProductClass(category, item, Integer.valueOf(productCount), Double.valueOf(price), currentDate, url);
		JSONObject jsonObject = obj.addAProduct(obj);
		
			response.getWriter().append(jsonObject.toString());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	  }
	}

