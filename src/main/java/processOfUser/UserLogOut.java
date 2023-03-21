package processOfUser;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClassesOfUser.BuyingProducts;
import fullTimeUse.ConstantVariables;

/**
 * Servlet implementation class UserLogOut
 */
@WebServlet("/UserLogOut")
public class UserLogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "1800");
		response.setHeader("Access-Control-Allow-Headers", "content-type");
		response.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");
		
		Cookie[] cookies = request.getCookies();
		try {
		for(int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("SESSIONID")) {
					PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("delete from sessions where sessionId = ?");
					ppst.setString(1, cookies[i].getValue());
					ppst.execute();
					cookies[i].setValue("");
				}
			}
		
		for(int j = 0; j < cookies.length; j++) {
			if(cookies[j].getName().equals("ROLE")) {
				cookies[j].setValue("");
			}
		}
		
		BuyingProducts.list.clear();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		response.getWriter().append("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
