

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ModifyOK
 */ 
@WebServlet("/ModifyOK")
public class ModifyOK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection connection;
	private Statement stmt;
	private HttpSession httpSession;
	
	private String id, pw, name, phone1, phone2, phone3, gender;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyOK() {
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

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	request.setCharacterEncoding("EUC-KR");
	httpSession = request.getSession();
	
	name= request.getParameter("name");
	id= request.getParameter("id");
	pw= request.getParameter("pw");
	phone1= request.getParameter("phone1");
	phone2= request.getParameter("phone2");
	phone3= request.getParameter("phone3");
	gender= request.getParameter("gender");
	
	if(pwConfirm()) {
		System.out.println("OK");
		
		String query = "update yoo.member1 set name= '"+name+"', phone1='"+phone1+"', phone2='"+phone2+"',phone3='"+phone3+"', gender='"+gender+"'";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "1234");
			stmt = connection.createStatement();
			int i = stmt.executeUpdate(query);

			if(i==1) {
				System.out.println("update success");
				httpSession.setAttribute("name", name);
				response.sendRedirect("modifyResult.jsp");
			}else {
				System.out.println("update fail");
				response.sendRedirect("modify.jsp");
			}
						
		} catch (Exception e) {
			System.out.println(e);
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
		
	}else {
		System.out.println("NG");
	}
	
	}
	
	private boolean pwConfirm() {
		boolean rs = false;
		
		String sessionPW = (String)httpSession.getAttribute("pw");
		
		if(sessionPW.equals(pw)) {
			rs=true;
		}else {
			rs = false;
		}
		
		return rs;
	}
}
