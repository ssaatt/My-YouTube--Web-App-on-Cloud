<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.amazonaws.*"%>
<%@ page import="com.amazonaws.auth.*"%>
<%@ page import="com.amazonaws.services.ec2.*"%>
<%@ page import="com.amazonaws.services.ec2.model.*"%>
<%@ page import="com.amazonaws.services.s3.*"%>
<%@ page import="com.amazonaws.services.s3.model.*"%>
<%@ page import="com.amazonaws.services.simpledb.*"%>
<%@ page import="com.amazonaws.services.simpledb.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hw2.db.RDS"%>


<%! // Share the client objects across threads to
    // avoid creating new clients for each web request
    private AmazonEC2         ec2;
    private AmazonS3           s3;
    private RDS     rds;%>


<%
    if (request.getMethod().equals("HEAD")) return;
%>

<%
    if (ec2 == null) {
        AWSCredentialsProvider credentialsProvider = new ClasspathPropertiesFileCredentialsProvider();
        ec2    = new AmazonEC2Client(credentialsProvider);
        s3     = new AmazonS3Client(credentialsProvider);
        rds = new RDS();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Hello AWS Web World!</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
    <script type="text/javascript"
	src="https://s3.amazonaws.com/js4153/jwplayer.js">
	</script>
<script type="text/javascript">jwplayer.key="IFhVDTGUAS2rMT6B2r9dmwEzCZv17bBbXti9RA==";</script>
</head>
<body>
    <div id="content" class="container">
        
        <br />
         <h1>
         <font size="6"><b>Hello! Welcome to AWS Web World!</b>
                </h1>
                <br />
   
                </h1>
                <br />
        
		<h2><font color="black" size="4">1.You Can Upload Your Own Video From Here (.mp4 or .flv):</h2> <br />

		<form action="FileUpLoad" method="post"
			enctype="multipart/form-data">
			<input type="file" name="file" size="50" /> <input type="submit" value="Upload">

		</form> <br />
         <h3><span style="background-color:#74fdf9; color:black" size="3">${requestScope.message}</span></h3>
         
         <h2><font color="black" size="4">2.Videos Could Be Played Here (.mp4 or .flv):</h2> <br />
		<div id="myplayer">Loading the player ...</div>

		<script type="text/javascript">		
		
		jwplayer('myplayer').setup({
		 
		                file: "https://s3.amazonaws.com/js4153/Movie1.mp4",	
		                image: "images/PLAY.png",
		                height:480,		
		                width:720,		
		                allowfullscreen: true,		
		                title: "The  selected video's name will be shown here.",		
		               modes: [{type: "flash", src:"https://s3.amazonaws.com/js4153/jwplayer.flash.swf"},	 
		                 {type: "html5", config:{file:"http://di5n7r3hj599s.cloudfront.net/Movie4.mp4",title: "Welcome",provider:"video"}} ], 	
		              
		                provider: "rtmp",		
		                streamer: "rtmp://s1juqxc0isivxx.cloudfront.net/cfx/st",
		                listbar : {
		                	position: 'bottom',
		                	size:80
		                },
		            	autostart: true
		                           }); 

		</script><br /><br />
		
		<h2><font color="black" size="5">3.List of Videos are here:</h2> <br />
         
       <table >
			<%
				String p ="https://s3.amazonaws.com/";
						
						String bucket_name = "js4153";
						LinkedList<String> videos = rds.getVideo();
						if( videos != null) {
							for(int i1 =0; i1 < videos.size(); i1++)
							{
								String url =p + bucket_name+"/"+ videos.get(i1).replace(" ","+");
			%>

			<tr>

				<% if(videos.get(i1).contains(".mp4") || videos.get(i1).contains(".flv")){%>
				<td>
					<p>
						<b><font color="black" size="4" ><%=videos.get(i1)  %></font></b></p>
				</td>
				
				<td>
				<button onclick="jwplayer('myplayer').load({file: '<%=url %>', image:'images/play.png', title:'<%= videos.get(i1) %>'});jwplayer('myplayer').play();" alt="Play" />PLAY
				</button>
				</td>
				
				<td><form action="DeleteFile" method="post"
						enctype="multipart/">
						<input type="hidden" name="videoName"
							value=<%= videos.get(i1).replaceAll(" ", "_") %>>
							<input
							type="submit" name="Delete" value="DELETE">
					</form></td>
					

				<td><font color="black" size="3" ><p>
						&nbsp;&nbsp;
						Current Rating is : <%=rds.getObjectRating(videos.get(i1))%>
						&nbsp;
					</p></td>

				<td><form action="Rate" method="post"
						enctype="multipart/">
						<input type="hidden" name="rateVideoName"
							value=<%= videos.get(i1).replaceAll(" ", "_") %>> </td>
						<tr><td><font color="black" size="3" >Rate:	<input type = "radio"
							name="rating" value ="1" onchange="this.form.submit()"/>1
							<input type = "radio"
							name="rating" value ="2" onchange="this.form.submit()"/>2
							<input type = "radio"
							name="rating" value ="3" onchange="this.form.submit()"/>3
							<input type = "radio"
							name="rating" value ="4" onchange="this.form.submit()"/>4
							<input type = "radio"
							name="rating" value ="5" onchange="this.form.submit()"/>5
							
						</td>
						
					</form>




				

				
					 <%} 					   
					   
					   }%>
			</tr>
			<% } %>
		</table>


	</div>
</body>
</html>
