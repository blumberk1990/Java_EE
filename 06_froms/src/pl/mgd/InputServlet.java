package pl.mgd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InputServlet
 */
@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Wykorzystano metode get:");
		User user = createUserFromRequest(request);
		sendResponse(response, user);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Wykorzystano metode Post:");
		User user = createUserFromRequest(request);
		sendResponse(response, user);
	}

	private User createUserFromRequest(HttpServletRequest request) {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("pass"));
		user.setGender(request.getParameter("gender"));
		user.setHobby(request.getParameterValues("hobby"));
		return user;
	}
	
	private void sendResponse(HttpServletResponse response, User user) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<body>");
		writer.println("<h1>Dane odebrano pomyœlnie.</h1>");
		writer.println("<div>");
		writer.println(user.getUsername() + "<br>");
		writer.println(user.getPassword() + "<br>");
		writer.println(user.getGender() + "<br>");
		writer.println("Hobby: <br>");
		if(user.getHobby() != null) {
			for(String hobby : user.getHobby()) {
				writer.println(" " + hobby + "<br>");
			}
		}
		writer.println("</div>");
		writer.println("</body>");
		writer.println("</html>");
	}
}
