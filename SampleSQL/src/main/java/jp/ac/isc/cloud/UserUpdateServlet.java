package jp.ac.isc.cloud;

import static java.util.Map.*;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection users = null;
		try {
			request.setCharacterEncoding("utf-8");
			users = DBConnection.openConnection();
			String id = request.getParameter("updateId");
			
			Map<String, String> updateMap = Map.ofEntries(
				entry("name", request.getParameter("updateName")),
				entry("picture", request.getParameter("updatePicture"))
			);
			
			String[] updateCols =
				updateMap
					.entrySet()
					.stream()
					.filter(entry -> entry.getValue().length() != 0)
					.map(entry ->
						String.format("%s = '%s'", entry.getKey(), entry.getValue())
						)
					.toArray(String[]::new);
			
			Statement state = users.createStatement();
			String query = String.format(
				"UPDATE user_table SET %s WHERE id = '%s'",
				String.join(", ", updateCols), id
			);
			state.executeUpdate(query);
			DBConnection.closeConnection(users, state);
			response.sendRedirect("/select");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
