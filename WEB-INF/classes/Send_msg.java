import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.*;
import java.util.Date;
import javax.sql.*;
import java.io.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import com.sun.rowset.JdbcRowSetImpl;
import javax.sql.rowset.JdbcRowSet;


@MultipartConfig(maxFileSize = 16177215)	// upload file's size up to 16MB

public class Send_msg extends HttpServlet{
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
		String name,text,url;
		
		url="jdbc:mysql://localhost:3306/project";

		InputStream inputStream = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp now = new Timestamp(new Date().getTime());

		
		
		Connection con=null;
		try {
			
			
			HttpSession session = request.getSession(false);
			out.println("Start");
			name = (String)session.getValue("user");
			out.print(name);
			//pass = (String)request.getParameter("password");
			//email = (String)request.getParameter("email");
			text = (String)request.getParameter("box");
			out.print(text);
		     	con = DriverManager.getConnection(url,"root","");
		     	
		     	out.println("AfterConnection");
		     	
				PreparedStatement ps = con.prepareStatement("insert into chats (sender,text) values ('"+name+"','"+text+"')");
				ps.executeUpdate();
				out.println("After Prepared");
				out.println("after update");
					//PreparedStatement ps = new prepareStatement("CREATE TABLE "+name+" ( username VARCHAR(20) NOT NULL UNIQUE , sent LONGTEXT NOT NULL , received LONGTEXT NOT NULL , flag LONGTEXT NOT NULL , total LONGTEXT NOT NULL");
					RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
					rd.forward(request, response);	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
            if (con != null) {
                // closes the database connection
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
		}
	}


}
