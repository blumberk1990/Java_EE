package pl.mgd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 1. Request scope - Przyk³ad 1, zasiêg tylko dla pojêdyñczego ¿¹dania.
	 * 2. Session scope - Przyk³ad 2, zasiêg dla ca³ej utworzonej sesji dla danego u¿ytkownika.
	 * 3. Application scope - Przyk³ad 3, zasiêg dla w obrêbie ca³ej aplikacji.
	 */
	/*Przyk³ad 1
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		if(request.getAttribute("login") == null) {
			request.setAttribute("login", login);
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head><title>Witaj " + request.getAttribute("login") + "</title></head>");
		writer.println("<body>");
		writer.println("<h1>Witaj " + request.getAttribute("login") + "</h1>");
		writer.println("</body>");
		writer.println("</html>");
	}*/
	
	/*Przyk³ad 2
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		if(request.getSession().getAttribute("login") == null) {
			request.getSession().setAttribute("login", login);
		}
		
		request.getSession().setMaxInactiveInterval(60);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head><title>Witaj " + request.getSession().getAttribute("login") + "</title></head>");
		writer.println("<body>");
		writer.println("<h1>Witaj " + request.getSession().getAttribute("login") + "</h1>");
		writer.println("</body>");
		writer.println("</html>");
	}*/

	/*Przyk³ad 3*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		if(getServletContext().getAttribute("login") == null) {
			getServletContext().setAttribute("login", login);
		}
		
		request.getSession().setMaxInactiveInterval(60);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head><title>Witaj " + getServletContext().getAttribute("login") + "</title></head>");
		writer.println("<body>");
		writer.println("<h1>Witaj " + getServletContext().getAttribute("login") + "</h1>");
		writer.println("</body>");
		writer.println("</html>");
	}

}
