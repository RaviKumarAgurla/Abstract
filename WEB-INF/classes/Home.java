import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import javax.sql.*;
import javax.sql.rowset.*;
import com.sun.rowset.*;

public class Home extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//out.println("In Home");
		
		String name, pass,url;
		url="jdbc:mysql://localhost:3306/project";
		
		name = (String) request.getParameter("username");
		
		//out.println("After String");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		out.println("<head>"
						+ "<link rel='stylesheet' href='main.css' >"				
						+ "<title>My Home</title>"
				+ "</head>"
				+ "<body>"
					+ "<div class = 'container' >"
						+ "<div class ='header' >"
								+ "<div class= 'profile'>"
									+ "<a href='#'> <img src='DefaultPic.png' height='70' width='70'> </a>"
								+ "</div>"
								+ "<div class='info'>"
									+ "<a href='#'><h3 style='color:white'>"+name+"</h3></a> <br>"
								+ "</div>"
								+ "<div class='options'>"
									+ "<ul>"
										+ "<a href='http://localhost:8080/project/logout' ><li>Logout</li></a>"
										+ "<a href='#'><li>Settings</li></a>"
										+ "<a href='#'><li>+ New Chat</li></a>"
									+ "</ul>"
								+ "</div>"
						+ "</div>"
						+ "<div class= 'mainbody'>"
							+ "<div class ='chatbox'>"
							+ "</div>"
							+ "<div class='contacts'>");
		
		
		//out.println("After Class.forName");
		
		Connection con =null;
		try{
			
			con = DriverManager.getConnection(url,"root","");
			
			//out.println("Connection Created");
			
			PreparedStatement ps = con.prepareStatement("select username from register");
			
			//out.println("After Prepared");
			
			ResultSet rs = ps.executeQuery();
			
			//out.println("After Execute");
		
			//out.println("In Div Block");
			
		while(rs.next())
		{
			String sname = (String)rs.getString(1);
			if(! sname.equals(name))
			{
				out.println("<button style='display: block;  background-color: inherit;  color: black;  padding-left: 10px;  width: 100%;  border: none;  outline: none;  text-align: left;  cursor: pointer;  transition: 0.3s;  font-size: 17px;  height: 60px;' class='tablinks'> "+sname+"</button>");
			}
		}
		
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		out.println("</div>"
				+ "</div>"
			+ "</div>"
		+ "</body>");
	}

}
