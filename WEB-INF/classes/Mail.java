import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import javax.servlet.ServletException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail extends HttpServlet 
{
    
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
      {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
	
        //String name = (String)req.getParameter("name");
        String email = (String)req.getParameter("email");
       // String pass = (String)req.getParameter("passw");
		
		final String from="bhageeradh96@gmail.com";
		final String username="bhageeradh96@gmail.com";
		final String pwd="bhagee1596";
		String host="smtp.gmail.com";
		Properties p= new Properties();
		p.put("mail.smtp.auth", "true");
	      p.put("mail.smtp.starttls.enable", "true");
	      p.put("mail.smtp.host",host);
	     p.put("mail.smtp.port", "587");
	     p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session son=Session.getInstance(p,new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	             return new PasswordAuthentication(username, pwd);
	          }
	       });
		try
		{
			Message msg = new MimeMessage(son);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
			msg.setSubject("Forgot Password");
			msg.setText("UserName and Password");
			Transport.send(msg);
		}
		catch(MessagingException ex)
		{
			 out.print("error "+ex.getMessage());
		}
		RequestDispatcher rd=req.getRequestDispatcher("login.html");
		out.println("Username and Password sent to your registered Email address");
		rd.include(req,res);
		
      /*  try{
        Class.forName("com.mysql.jdbc.Driver");}catch(ClassNotFoundException c){c.printStackTrace();}        
		try{Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
		PreparedStatement ps=conn.prepareStatement("insert into userin values(?,?,?)");

        ps.setString(1,name);
        ps.setString(2,email);
        ps.setString(3,pass);
		
        int i=ps.executeUpdate();
        if(i>0){
					req.setAttribute("email",email);
					RequestDispatcher rd=req.getRequestDispatcher("index.html");
					rd.forward(req,res);
					}
					ps.close();
		conn.close();
		
		/*HttpSession ses = req.getSession(false);
        String u=(String)ses.getAttribute("username");
		res.setHeader("Cache-Control","no-cache");
		res.setHeader("Cache-Control","no-store"); 
		res.setDateHeader("Expires", 0); 
		res.setHeader("Pragma","no-cache"); 

		if (null == u) 
		{
			RequestDispatcher rd = req.getRequestDispatcher("index.html");
			rd.forward(req,res);
		}*/
        
      /*  }
        catch(Exception e)
        {
            out.print("error 2"+e.getMessage());
        }*/
      }
}
