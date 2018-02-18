package dbconn;
import java.io.IOException; 
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class facultyratio extends HttpServlet {
            public static long tx;
            public static long ty;
            public static long ratio;
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
				    sql= "SELECT COUNT(*) FROM staff FULL OUTER JOIN bank ON staff.uidai = bank.uidai where sstatus IS NULL";
				    ResultSet rs = pst.executeQuery(sql);
                    while(rs.next()){
                    	long ty = rs.getLong("count");
                    	out.println("<html><head><title>Faculty Ratio</title></head>");
						out.println("<body bgcolor=blue >");
						out.println("<center><bold><font size=26><color:red> ");
						out.println("STUDENT FACULTY STRENGTH<BR>");
			            out.println("<li>FACULTY STRENGTH IS " +ty+ "");
			            out.println("</li></color></font></bold><br></center></body></html>");
                    }
                     rs.close();
			         pst.close();
			         c.close();
			       } catch ( Exception e ) {
			         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			         System.out.println(e);
			       }
				try {
					Class.forName("org.postgresql.Driver");
					Connection c = DriverManager.getConnection(connectionURL, "postgres", "42");
					Statement pst = c.createStatement();
				    String sql1;	
				    sql1= "SELECT COUNT(*) FROM student";
				    ResultSet rs1 = pst.executeQuery(sql1);
                    while(rs1.next()){
                    	long tx = rs1.getLong("count");       	
                    	out.println("<html><head><title>Faculty Ratio</title></head>");
						out.println("<body bgcolor=blue >");
						out.println("<center><bold><font size=26><color:red><li> ");
			            out.println("STUDENT STRENGTH IS " +tx+ "");
			            out.println("</li></color></font></bold><br></center></body></html>");
                    }
                     rs1.close();
			         pst.close();
			         c.close();
			       } catch ( Exception e ) {
			         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			         System.out.println(e);
			       }
				try {
					Class.forName("org.postgresql.Driver");
					Connection c = DriverManager.getConnection(connectionURL, "postgres", "42");
					Statement pst = c.createStatement();
				    String sql1;	
				    sql1= "SELECT (SELECT COUNT(*) FROM student) / (SELECT COUNT(*) FROM staff FULL OUTER JOIN bank ON staff.uidai = bank.uidai where sstatus IS NULL)";
				    ResultSet rs1 = pst.executeQuery(sql1);
                    while(rs1.next()){
                    	long tx = rs1.getLong("?column?");       	
                    	out.println("<html><head><title>Faculty Ratio</title></head>");
						out.println("<body bgcolor=blue >");
						out.println("<center><bold><font size=26><color:red>STUDENT FACULTY RATIO<BR><li> ");
			            out.println("THERE IS 1 FACULTY FOR EVERY " +tx+ " STUDENT(S)");
			            out.println("</li></color></font></bold><br></center></body></html>");
                    }
                     rs1.close();
			         pst.close();
			         c.close();
			       } catch ( Exception e ) {
			         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			         System.out.println(e);
			       }
			       System.out.println("Operation done successfully");
			}
}