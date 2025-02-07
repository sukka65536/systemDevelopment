package jp.ac.isc.cloud;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection users = null;
		try {
			users = DBConnection.openConnection();
			String id = request.getParameter("deleteId");
			Statement state = users.createStatement();
			String query = String.format(
					"DELETE FROM user_table WHERE id = '%s'",
					id
			);
			state.executeUpdate(query);
			DBConnection.closeConnection(users, state);
			response.sendRedirect("/select");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
