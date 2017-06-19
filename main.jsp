<html>

	<%@ page import ="java.sql.*"%>
<%@ page import ="java.io.*"%>
		<%@ page import ="javax.sql.*"%>
<%@ page import ="javax.http.*"%>
<%@ page import ="javax.http.servlet.*"%>
	<%@ page import ="com.sun.rowset.JdbcRowSetImpl"%>
		<%@ page import ="javax.sql.rowset.JdbcRowSet"%>
	<head>	
			
			<link rel="stylesheet" href="main.css" >
			<title>main</title>
			
	</head>
	
	<body>
		
		<%  String name,url;
		   String cname="";
		url="jdbc:mysql://localhost:3306/project";
		
		
		   		name = (String)session.getValue("user");
		   
		
		
		//out.println("After String");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} %>

			
				<% 
		%>
					
					
		<div class = "container" >
		
			<div class ="header" >
				
				<div class= "profile">
					<a href="#"> <img src="DefaultPic.png" height="70" width="70"> </a>
				</div>
				
				<div class="info">
					<a href="#"><h3 style="color:white">
						<%=name %>
						</h3></a> <br>
				</div>
				
				<div class="options">
					<ul>
						<a href="http:\\localhost:8080\project\logout"><li>Logout</li></a>
						<a class="tablinks" href="javascript:void(0)" onclick="openCity(event,'settings',this.id)"><li>Settings</li></a>
						<a class="tablinks" id="defaultOpen" href="javascript:void(0)" onclick="openCity(event,'default',this.id)"><li>Home</li></a>
					</ul>
				</div>
				
			</div>
			
			<div class= "mainbody">
			
				

					<div id="default" class="tabcontent">
  						
						<div class="headr">
							
							<div class= "profilepic">
								<a href="#"> <img src="DefaultPic.png" height="70" width="70"> </a>
							</div>
						
							<div class="cinfo">
								<a href="#"><h3 style="color:white" id="cinfoname"></h3></a>
							</div>
								
						</div>
							
						<div class="area" id="area" onload="chang()">
								
							<%
		   Connection con =null;
		try{
			
			con = DriverManager.getConnection(url,"root","");
			
			//out.println("Connection Created");
			
			PreparedStatement ps = con.prepareStatement("select username from register");
			
			//out.println("After Prepared");
			
			ResultSet rs = ps.executeQuery();
				   
			//out.println("After Execute");
		
			//out.println("In Div Block");
							   
			JdbcRowSet rowset = new JdbcRowSetImpl();
			rowset.setUrl(url);
			rowset.setUsername("root");
			rowset.setPassword("");
		
			rowset.setCommand("Select * from chats");
			rowset.execute();
							 
					out.println("");
					while(rowset.next())
					{
							out.println(rowset.getString(1)+" :"+" "+rowset.getString(2));
							out.println("");
				   	}
							 %>
						</div>
						
						<div class="send" > 
							<hr>	
							<form action="http:\\localhost:8080\project\send" method="post">
							<input type="text" size="80%" id="textbox" autofocus name="box" >
							<input type="submit" value="send" id="send">
							</form>
						</div>
						
						
						
					</div>

					<div id ="settings" class="tabcontent">
						
						<button  style="display: block;  color: black;  width: 100%;  outline: none;  text-align: center;  cursor: pointer;  transition: 0.3s;  font-size: 17px;  height: 60px; "  href="javascript:void(0)" >Change Password</button>
						<button  style="display: block;  color: black;  width: 100%;  outline: none;  text-align: center;  cursor: pointer;  transition: 0.3s;  font-size: 17px;  height: 60px; "  href="javascript:void(0)" >Privacy</button>
						<button  style="display: block;  color: black;  width: 100%;  outline: none;  text-align: center;  cursor: pointer;  transition: 0.3s;  font-size: 17px;  height: 60px; "  href="javascript:void(0)" >About</button>
						
					</div>	
				

				
		
				
				
				<div class="tab">
				
			
		<%
			
		   while(rs.next())
		{
			String sname = (String)rs.getString(1);
		   
			if(! sname.equals(name))
			{
		   %>
				<button class="tablinks" id="<%=sname%>" style="display: block;  color: black;  width: 100%;  outline: none;  text-align: center;  cursor: pointer;  transition: 0.3s;  font-size: 17px;  height: 60px; "  href="javascript:void(0)" onclick="openCity(event,'default',this.id)"> <%=sname %></button>
			<%
			   }
		}
		
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} %> 
				
				</div>
				
			</div>
		
		</div>
	
		<script>
				
			
			var act;
			function openCity(evt, cityName , bid) {
    		// Declare all variables
    			var i, tabcontent, tablinks;

    		// Get all elements with class="tabcontent" and hide them
    			tabcontent = document.getElementsByClassName("tabcontent");
    		
				for (i = 0; i < tabcontent.length; i++) {
        			tabcontent[i].style.display = "none";
    			}

    			// Get all elements with class="tablinks" and remove the class "active"
    			tablinks = document.getElementsByClassName("tablinks");
    		
				for (i = 0; i < tablinks.length; i++) {
        			tablinks[i].className = tablinks[i].className.replace(" active", "");
    			}

    		// Show the current tab, and add an "active" class to the link that opened the tab
    			document.getElementById(cityName).style.display = "block";
    			evt.currentTarget.className += " active";
			}
			
			function sendmsg(){
				
				
				var str = document.getElementById("textbox").value;
				str = "<%=name%> : "+str;
				document.getElementById("area").textContent+="\n"+str+"\n";
				document.getElementById("textbox").focus();
							
				
				var element = document.getElementById("area");
				element.scrollTop = element.scrollHeight;
				
			}
			
			function chang(){
			
				var element = document.getElementById("area");
				element.scrollTop = element.scrollHeight;
			}	
			document.getElementById("defaultOpen").click();
	
		</script>
	
	</body>

</html>