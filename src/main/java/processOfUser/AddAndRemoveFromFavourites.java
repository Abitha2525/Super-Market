package processOfUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClassesOfUser.FavouritesDetails;
import fullTimeUse.ConstantVariables;

/**
 * Servlet implementation class AddAndRemoveFromFavourites
 */
@WebServlet("/user/AddAndRemoveFromFavourites")
public class AddAndRemoveFromFavourites extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAndRemoveFromFavourites() {
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
		
		String product = request.getParameter("product");
		String n = request.getParameter("n");
		String mobileNumber = "";
		Cookie[] cookies = request.getCookies();
		try {
		for(int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("SESSIONID")) {
					PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select mobileNumber from sessions where sessionId = ?");
					ppst.setString(1, cookies[i].getValue());
					ResultSet rs = ppst.executeQuery();
					if(rs.next()) {
						mobileNumber = rs.getString(1);
					}
				}
			}
		FavouritesDetails obj = new FavouritesDetails();
		obj.addAndRemoveFromFavourites(product, n, mobileNumber);
		
	}
		catch(Exception ex) {
			ex.printStackTrace();
		}

}
}
