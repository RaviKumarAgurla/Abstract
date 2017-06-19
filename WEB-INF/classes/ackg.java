import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.io.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;


@MultipartConfig(maxFileSize = 16177215)	// upload file's size up to 16MB

public class ackg extends HttpServlet{
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();
		
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma","no-cache");
	
		
		String name,pass,email,url;
		
		url="jdbc:mysql://localhost:3306/project";

		InputStream inputStream = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Connection con=null;
		try {
			
			Part filePart = null;
			
			
			out.println("Start");
			name = (String)request.getParameter("username");
			pass = (String)request.getParameter("password");
			email = (String)request.getParameter("email");
			
			filePart = request.getPart("photo");
			out.println("File Part Completed");
			 if (filePart != null) {
					
		         inputStream = filePart.getInputStream();
		         out.println("Get InputStream");
		      }
		     
		     	con = DriverManager.getConnection(url,"root","");
		     	
		     	out.println("AfterConnection");
		     	PreparedStatement st = con.prepareStatement("insert into register (username,password,email,status,image) values ('"+name+"','"+pass+"','"+email+"','0','"+inputStream+"')");
				out.println("After Prepared");
				
				/*if (inputStream != null) {
	                // fetches input stream of the upload file for the blob column
	                st.setBlob(5, inputStream);
	                out.println("after insert");
	                
	            }*/
				
				//ResultSet rs =  st.executeQuery();
				
				st.executeUpdate();
				out.println("after update");
					//PreparedStatement ps = new prepareStatement("CREATE TABLE "+name+" ( username VARCHAR(20) NOT NULL UNIQUE , sent LONGTEXT NOT NULL , received LONGTEXT NOT NULL , flag LONGTEXT NOT NULL , total LONGTEXT NOT NULL");
					RequestDispatcher rd = request.getRequestDispatcher("afterReg.html");
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
