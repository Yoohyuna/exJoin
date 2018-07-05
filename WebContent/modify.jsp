<!-- 수정화면. db에 연결해서 id 맞으면 수정(회원가입 페이지랑 비슷) -->

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%!
	private Connection connection;
	private Statement stmt;
	private ResultSet resultSet;

	private String id, pw, name, phone1, phone2, phone3, gender;
	
	
	public String getPhone1() {
		return this.phone1;
		}
	
	
	%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>


	<%
		session.setAttribute("gender", "man");
	
	Object obj1 = session.getAttribute("gender"); 
	String gender = (String) obj1;
		
	%>



	<%
		//id = (String) session.getAttribute("id");

		String query = "select * from yoo.member where id = '" + id + "'";

		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "1234");
		stmt = connection.createStatement();
		resultSet = stmt.executeQuery(query);

		while (resultSet.next()) {
			name = resultSet.getString("name");
			pw = resultSet.getString("pw");
			phone1 = resultSet.getString("phone1");
			phone2 = resultSet.getString("phone2");
			phone3 = resultSet.getString("phone3");
			gender = resultSet.getString("gender");
		}
	%>

	<form action="ModifyOK" method="post">
		이름 : <input type="text" name="name" size="10" value=<%=name%>><br />
		아이디 :	<%=id%><br />	<!--id는 변경불가. 고유한 값 -->
		비밀번호 : <input type="text" name="pw" size="10"><br />
		전화번호 : <select name="phone1">
			<option value="010">010</option>
			<option value="011">011</option>
			<option value="016">016</option>
			<option value="017">017</option>
			<option value="018">018</option>
		</select> -
		<input type="text" name="phone2" size="5" value=<%=phone2%>>
		- <input type="text" name="phone3" size="5" value=<%=phone3%>> <br />
		 <%
	
			if (gender.equals("man")) {
	
		%>

		성별 : <input type="radio" name="gender" value="man" checked="checked">남&nbsp; <input type="radio" name="gender" value="woman">여 <br />

		<%
			} else {
		%>
		성별 : <input type="radio" name="gender" value="man">남 &nbsp; <input type="radio" name="gender" value="woman" checked="checked">여
		<br />
		<%
			}
		%> 
		<input type="submit" value="정보수정"> <input type="reset"	value="취소">
	</form>

</body>
</html>