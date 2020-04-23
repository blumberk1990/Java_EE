package pl.mgd;

import java.util.Map;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ParamServlet
 */
@WebServlet("/ParamServlet")
public class ParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> parameterMap = request.getParameterMap();
		
        /**
         * Przyk³ad 1. Wyœwietlenie w konsoli
         * */
       /* 
		for(String key : parameterMap.keySet()) {
			System.out.println(key);
			for(String value : parameterMap.get(key)) {
				System.out.println(value);
			}
		}*/
        
        /**
         * Przyk³ad 2. Wyœwietlenie za pomoc¹ prostego kodu HTML
         * */
        
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter writer = response.getWriter();
        
        writer.println("<html>");
        for (String key : parameterMap.keySet()) {
        	writer.println("<div>");
        	writer.println(key);
        	writer.println("</div>");
        	for (String value : parameterMap.get(key) ) {
        		writer.println("<div>");
            	writer.println("&nbsp&nbsp-" + value);
            	writer.println("</div>");
        	}
        }
        writer.println("</html>");
	}

}
