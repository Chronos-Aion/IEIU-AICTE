package dbconn;
import java.io.IOException;     
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class search extends HttpServlet {  
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
		    sql= "SELECT uidai , COUNT(*) FROM trends GROUP BY field";
		    ResultSet rs = pst.executeQuery(sql);
		    out.println("<P ALIGN='center'><TABLE BORDER=1>");
		    ResultSetMetaData rsmd = rs.getMetaData();
		    int columnCount = rsmd.getColumnCount();
		    // table header
		    out.println("<TR>");
		    for (int i = 0; i < columnCount; i++) {
		      out.println("<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>");
		      }
		    out.println("</TR>");
		    // the data
		    while (rs.next()) {
		     out.println("<TR>");
		     for (int i = 0; i < columnCount; i++) {
		       out.println("<TD>" + rs.getString(i + 1) + "</TD>");
		       }
		     out.println("</TR>");
		     }
		    out.println("</TABLE></P>");
	         rs.close();
	         pst.close();
	         c.close();
	       } catch ( Exception e ) {
	        out.println(e);
	       }
	       System.out.println("Operation done successfully");
	     }
	}