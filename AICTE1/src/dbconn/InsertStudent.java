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
public class InsertStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String connectionURL = "jdbc:postgresql://127.0.0.1:5432/AICTE";
		Connection connection = null;
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String stu_name = req.getParameter("stu_name");
		long Inst_Id = Long.valueOf(req.getParameter("Inst_Id"));
		long UIDAI = Long.valueOf(req.getParameter("UIDAI"));
		String DOB = req.getParameter("DOB");
		String Gender = req.getParameter("Gender");
		String Mail_id = req.getParameter("Mail_id");
		long contact_no = Long.valueOf(req.getParameter("contact_no"));
		long year_passed_out = Long.valueOf(req.getParameter("year_passed_out"));
		String enrolled = req.getParameter("enrolled");
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(connectionURL, "postgres", "42");
			PreparedStatement pst = connection.prepareStatement("INSERT into student VALUES(?,?,?,?,?,?,?,?,?)");
			pst.setString(1, stu_name);
			pst.setLong(2, Inst_Id);
			pst.setLong(3, UIDAI);
			pst.setString(9, DOB);
			pst.setString(4, Gender);
			pst.setString(5, Mail_id);
			pst.setLong(6, contact_no);
			pst.setLong(7, year_passed_out);
			pst.setString(8, enrolled);
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