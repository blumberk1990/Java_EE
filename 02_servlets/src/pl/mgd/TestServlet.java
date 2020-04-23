package pl.mgd;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @WebServlet - Mapowanie adresu URL/URI. Mo¿liwe rozszrzenie do tablicy kilku adresuów urlPatterns
 * 
 * web.xml - opcja która dzia³a bez adnotacji, konfiguracja i mapowanie odbywa siê w pliku web.xml
 */
@WebServlet("/TestServlet")
//@WebServlet(urlPatterns = {"/TestServlet", "/helloWorld"})
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * GET - jest to podstawowe ¿¹danie w protokole HTTP, za któego obs³ugê w klasie HTTPServlet odpowiada metoda doGet()
	 * 		 ¯¹danie to bêdzie wykorzystywane za ka¿dym razem kiedy bêdziemy odwo³ywali siê do zasobu na serwerze pod wskazanym adresem URL.
	 * 		 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("¯¹danie GET przetworzono pomyœlnie");
	}

}
