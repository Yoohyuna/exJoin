

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class JoinOK
 */
@WebServlet("/JoinOK")
public class JoinOK extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private java.sql.Connection connection;
	private java.sql.Statement stmt;
	
	private String name, id, pw, phone1, phone2, phone3, gender;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinOK() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response); //doget으로 오면 actionDo메소드로 간다
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response); //dopost으로 오면 actionDo메소드로 간다 - doget,dopost 어디로 오든 actionDo로 가서 처리. 주로 하는 방식
	}
	
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("EUC-KR");
		
		name= request.getParameter("name");
		id= request.getParameter("id");
		pw= request.getParameter("pw");
		phone1= request.getParameter("phone1");
		phone2= request.getParameter("phone2");
		phone3= request.getParameter("phone3");
		gender= request.getParameter("gender");
		
		String query = "insert into yoo.member1 values('"+name+"', '"+id +"', '"+pw+"', '"+phone1+"', '"+phone2+"', '"+phone3+"', '"+gender+"')";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "1234");
			System.out.println("Connection Success !");
			stmt = connection.createStatement();
			int i = stmt.executeUpdate(query);

			
			if(i==1) {
				System.out.println("insert success");
				response.sendRedirect("join.html");
			}else {
				System.out.println("insert fail");
				response.sendRedirect("join.html");
			}
						
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("insert fail");
			response.sendRedirect("join.html");
		} finally {//자원해제(서버부하)
			try {
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
