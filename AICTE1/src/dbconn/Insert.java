package dbconn;
   
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Insert extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String connectionURL = "jdbc:postgresql://127.0.0.1:5432/AICTE";
		Connection connection = null;
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String Staff_Name = req.getParameter("Staff_Name");
		long uidai = Long.valueOf(req.getParameter("uidai"));
		String Gender = req.getParameter("Gender");
		String Mail_id = req.getParameter("Mail_id");
		long acc_no = Long.valueOf(req.getParameter("acc_no"));
		long contact_no = Long.valueOf(req.getParameter("contact_no"));
		long joined_year = Long.valueOf(req.getParameter("joined_year"));
		String Type_of_staff = req.getParameter("Type_of_staff");
		String Institution_Name = req.getParameter("Institution_Name");
		String Qualification = req.getParameter("Qualification");
		String Salary_Credited = req.getParameter("Salary_Credited");
		String dob = req.getParameter("dob");
		
		try {
		
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(connectionURL, "postgres", "42");
			PreparedStatement pst = connection.prepareStatement("INSERT into staff VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, Staff_Name);
			pst.setLong(8,  uidai);
			pst.setString(2, Gender);
			pst.setString(3, Mail_id);
			pst.setLong(9,  acc_no);
			pst.setLong(10,  contact_no);
			pst.setLong(11,  joined_year);
			pst.setString(4, Type_of_staff);
			pst.setString(5	, Institution_Name);
			pst.setString(6,Qualification);
			pst.setString(7,Salary_Credited);
			pst.setString(12, dob);
		
			int numRowsChanged = pst.executeUpdate();
			if (numRowsChanged != 0) {
				out.println("<br>Record has been inserted");
			} else {
				out.println("failed to insert the data");
			}
			pst.close();
		} catch (ClassNotFoundException e) {
			out.println("Couldn't load database driver: " + e.getMessage());
		} catch (SQLException e) {
			out.println("SQLException caught: " + e.getMessage());
		} catch (Exception e) {
			out.println(e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException ignored) {
				out.println(ignored);
			}
		}

	}

	
}


