package pl.mgd;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @WebServlet - Mapowanie adresu URL/URI. Mo�liwe rozszrzenie do tablicy kilku adresu�w urlPatterns
 * 
 * web.xml - opcja kt�ra dzia�a bez adnotacji, konfiguracja i mapowanie odbywa si� w pliku web.xml
 */
@WebServlet("/TestServlet")
//@WebServlet(urlPatterns = {"/TestServlet", "/helloWorld"})
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * GET - jest to podstawowe ��danie w protokole HTTP, za kt�ego obs�ug� w klasie HTTPServlet odpowiada metoda doGet()
	 * 		 ��danie to b�dzie wykorzystywane za ka�dym razem kiedy b�dziemy odwo�ywali si� do zasobu na serwerze pod wskazanym adresem URL.
	 * 		 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("��danie GET przetworzono pomy�lnie");
	}

}
