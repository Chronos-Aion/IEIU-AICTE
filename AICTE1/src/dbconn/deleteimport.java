package dbconn;
import java.io.IOException;   
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class deleteimport extends HttpServlet {
		private static final long serialVersionUID = 1L;
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
		}
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			String connectionURL = "jdbc:postgresql://127.0.0.1:5432/AICTE";
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			long uidai = Long.valueOf(req.getParameter("uidai"));
			try {
				Class.forName("org.postgresql.Driver");
				Connection c = DriverManager.getConnection(connectionURL, "postgres", "42");
				    PreparedStatement prepstmt;
				    prepstmt = c.prepareStatement("insert into dropoutcount (uidai) select uidai from staff where uidai = ?");
				    prepstmt.setLong(1, uidai);
				    prepstmt.executeUpdate();
				    prepstmt.close();
				    c.close();
			}
			 catch (Exception e) {
				out.println(e);
			} 	
	try {
				Class.forName("org.postgresql.Driver");
				Connection c = DriverManager.getConnection(connectionURL, "postgres", "42");
				    PreparedStatement prepstmt;
				    prepstmt = c.prepareStatement("DELETE FROM staff WHERE uidai = ?");
				    prepstmt.setLong(1, uidai);
				    prepstmt.executeUpdate();
				    prepstmt.close();
				    c.close();
		       } catch ( Exception e ) {
		         out.println(e);
		       }
		       System.out.println("Operation done successfully");
		     }
		}	