package world.model.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import world.model.dao.CountryDAO;
import world.model.entity.Country;

/**
 * Servlet implementation class TableServlet
 */
@WebServlet("/table")
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
//			ドライバーの呼び出し(jarファイルの設定必須)
			Class.forName("com.mysql.cj.jdbc.Driver");
			String driver = "jdbc:mysql://localhost/world?serverTimezone=UTC";
			Connection connection = DriverManager.getConnection(driver, "root", "");
			CountryDAO dao = new CountryDAO(connection);

			String column = request.getParameter("column");
			String direction = request.getParameter("direction");

			List<Country> list = dao.findAll(column, direction);

			request.setAttribute("list", list);
			request.setAttribute("column", column);
			request.setAttribute("direction", direction);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/table.jsp");
			dispatcher.forward(request, response);

			connection.close();

		} catch(Exception e) {
			throw new ServletException(e);
		}
	}

}
