package delete;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteSong")
public class DeleteSong extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteSong(){ super(); }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int song_id = Integer.parseInt(request.getParameter("id"));

		System.out.println("song_id " + song_id);

		Connection c = null;
		try {
			String sql = "DELETE FROM SONG WHERE song_id='" + song_id + "'";
			String url = "jdbc:mysql://localhost/mydatabase";
			System.out.println("SQL" + sql);
			c = DriverManager.getConnection(url, "root", "");
			Statement st = c.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
				if (c != null) {
					c.close();
					System.out.println("Connection Closed: Delete Musician");
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		//response.sendRedirect("Displayproject");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
