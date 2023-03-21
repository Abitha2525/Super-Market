package fullTimeUse;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseConnection
 */
//@WebServlet("/DatabaseConnection")
public class DatabaseConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseConnection() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
    	String dbName = getServletConfig().getInitParameter("dbname");
    	String url = "jdbc:mysql://localhost:3306/"+dbName;
    	String dbUserName = getServletConfig().getInitParameter("dbusername");
    	String dbPassword = getServletConfig().getInitParameter("dbpassword");
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			ConstantVariables.dbConnection = DriverManager.getConnection(url, dbUserName, dbPassword);
			System.out.println("abitha");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	catch(SQLException e1) {
         e1.printStackTrace();
    	}
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
