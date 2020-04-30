package pl.mgd.wykopek.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.mgd.wykopek.model.User;
import pl.mgd.wykopek.service.DiscoveryService;

/**
 * Servlet implementation class DiscoveryAddController
 */
@WebServlet("/add")
public class DiscoveryAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiscoveryAddController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getUserPrincipal() != null) {
			request.getRequestDispatcher("new.jsp").forward(request, response);
		} else {
			response.sendError(403);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("inputName");
		String description = request.getParameter("inputDescription");
		String url = request.getParameter("inputUrl");
		User aauthenticatedUser = (User) request.getSession().getAttribute("user");
		System.out.println("request.getUserPrincipal() " + request.getUserPrincipal().getName());
		if(request.getUserPrincipal() != null) {
			DiscoveryService discoveryService = new DiscoveryService();
			discoveryService.addDiscovery(name, description, url, aauthenticatedUser);
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			response.sendError(403);
		}
	}

}
