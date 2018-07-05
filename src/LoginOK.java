//db에 연결-member1테이블로 감 - login.html에서 넘어온 정보가 있는지 ( 아이디가 있으면 비밀번호 확인)-다음페이지로 이동


import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginOK
 */
@WebServlet("/LoginOK")
public class LoginOK extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	private Statement stmt;
	private ResultSet resultSet;
	
	private String name, id, pw, phone1, phone2, phone3, gender;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginOK() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		id= request.getParameter("id");
		pw = request.getParameter("pw");
		
		String query = "select * from yoo.member1 where id = '"+id+"' and pw='"+pw+"'";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "1234");
			stmt = connection.createStatement();
			resultSet = stmt.executeQuery(query);

			while(resultSet.next()) { //데이터가 있다면 데이터에 있는 값 구해옴
				name = resultSet.getString("name");
				id = resultSet.getString("id");
				pw = resultSet.getString("pw");
				phone1 = resultSet.getString("phone1");
				phone2 = resultSet.getString("phone2");
				phone3 = resultSet.getString("phone3");
				gender = resultSet.getString("gender");
				
			}
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("name", name);
			httpSession.setAttribute("id", id);
			httpSession.setAttribute("pw", pw);
			
			response.sendRedirect("loginResult.jsp");
			
						
						
		} catch (Exception e) {
			System.out.println(e);
		} finally {//자원해제(서버부하)
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(stmt != null) {
					stmt.close();					
				}
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}
}
