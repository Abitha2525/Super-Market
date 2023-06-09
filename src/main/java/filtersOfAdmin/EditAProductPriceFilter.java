package filtersOfAdmin;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

/**
 * Servlet Filter implementation class EditAProductPriceFilter
 */
@WebFilter(filterName = "EditAProductPriceFilter", urlPatterns = {"/admin/EditAProductPrice"})
public class EditAProductPriceFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public EditAProductPriceFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 String productOldName = request.getParameter("oldName");
	       String category = request.getParameter("category");
	       JSONObject jsonObject = new JSONObject();
	       
	       try {    	   
	    	   double price = Double.valueOf(request.getParameter("price"));
	    	   if(!productOldName.equals("") || price > 0) {
	           PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select productName from Products where productName = ?");
	    	   ppst.setString(1, productOldName);
	           ResultSet rs = ppst.executeQuery();
	    	   if(rs.next()) {
	    		   chain.doFilter(request, response);
	    	   }
	    	   else {
	    		   jsonObject.put("statusCode", 400);
	    		   jsonObject.put("message", "We don't have that product");
	    		   response.getWriter().append(jsonObject.toString());
	    	   }
	    	   
	    	   }
	    	   else {
	    		   jsonObject.put("statusCode", 101);
	    		   jsonObject.put("message", "Please fill the required fields with valid values");
	    		   response.getWriter().append(jsonObject.toString());
	    	   }
	       }
	       catch(NumberFormatException ex) {
	    	   ex.printStackTrace();
	    	   jsonObject.put("statusCode", 102);
	    	   jsonObject.put("message", "Please enter the price with a valid value");
	    	   response.getWriter().append(jsonObject.toString());
	       }
	       catch(Exception ex) {
	    	   ex.printStackTrace();
	    	   jsonObject.put("statusCode", 400);
			   jsonObject.put("message", "Some error occurred");
			   response.getWriter().append(jsonObject.toString());
	       }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
