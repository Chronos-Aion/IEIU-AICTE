package dbconn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class convertcsv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String connectionURL = "jdbc:postgresql://127.0.0.1:5432/AICTE";
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(connectionURL, "postgres", "42");
			Statement pst = c.createStatement();
		    String sql;	
		    sql= "COPY (select district , twentysixteen from dropoutdist) TO 'D:/drop6.csv' DELIMITER ',' CSV HEADER";
		    int numColumnsChanged = pst.executeUpdate(sql);
			if (numColumnsChanged != 0) {
				out.println("<br>Record has been updated");
			} else {
				out.println("csv created");
			}
			pst.close();
	         c.close();
	       } catch ( Exception e ) {
	         out.println(e);
	       }
	       System.out.println("Operation done successfully");
	     }
}