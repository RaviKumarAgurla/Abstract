import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import javax.sql.*;

public class Validate extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma","no-cache");
		
		
		String name, pass,url;
		url="jdbc:mysql://localhost:3306/project";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
		name = (String)request.getParameter("username");
		pass =(String) request.getParameter("password");
		
		Connection con = DriverManager.getConnection(url,"root","");
		PreparedStatement st = con.prepareStatement("select username,password from register");
		ResultSet rs =  st.executeQuery();
		
		//out.println("<p>afer query</p>");
		
		while(rs.next())
		{
			//out.println("<p>In rs block</p>");
			String uname =(String) rs.getString("username");
			String upass =(String) rs.getString("password");
			
			/*out.println("name: "+name+ "  pass "+pass);
			out.println("<br>");
			out.println("uname: "+uname+ "   upass "+upass);
			out.println("<br>");
			out.println(rs.getString("username")+ "   "+rs.getString("password"));
			out.println("<br>");*/
			
			
			if(name.equals(uname) && pass.equals(upass))
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("user",name);
				//response.sendRedirect("home");
				
				out.println("<p>vaild ids</p>");
				
				RequestDispatcher rd =request.getRequestDispatcher("main.jsp");
				rd.forward(request, response);
				//out.print("<input type='button' value='continue' onClick='http://localhost:8080/project/home'");  
				
			}
			else{
				
			//	out.print("<script>"
			//			+ "window.alert('Username or Password is Incorrect')"
			//			+ "</script>");  
				out.print("Sorry, Password Incorrect!");  

				RequestDispatcher rd =request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
			
		}
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}
