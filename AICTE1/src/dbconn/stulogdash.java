package dbconn;
import java.io.IOException; 
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class stulogdash extends HttpServlet {
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
			    prepstmt = c.prepareStatement("INSERT INTO stulog (uidai, sname, gender, instid) SELECT uidai, sname, gender, instid FROM student WHERE uidai = ?");
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
			Statement pst = c.createStatement();
		    String sql1;	
		    sql1= "SELECT (uidai,sname,gender,instid) FROM student";
            ResultSet rs1 = pst.executeQuery(sql1);
			while(rs1.next()){
            	String tx = rs1.getString("row");  
            	out.println("<html><head><title>Student DashBoard</title></head>");
				out.println("<body bgcolor=blue >");
				out.println("<center><bold><font size=26><color:red><li> ");
	            out.println("(UIDAI,Student Name,Gender,Institute Id) = " +tx+ "");
	            out.println("</li></color></font></bold><br></center></body></html>");
            }
             rs1.close();
	         pst.close();
	         c.close();
	       } catch ( Exception e ) {
	         System.out.println(e);
	       }
	}
}