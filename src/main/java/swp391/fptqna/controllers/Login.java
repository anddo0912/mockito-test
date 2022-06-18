package swp391.fptqna.controllers;

import swp391.fptqna.dao.UserDAO;
import swp391.fptqna.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    private final String LOGIN_VIEW = "index.jsp";
    private final String HOME_VIEW = "home.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            String txtemail = request.getParameter("txtemail");
            String txtpassword = request.getParameter("txtpassword");

            if (txtemail.trim().equals("") || txtpassword.trim().equals("")){
                request.setAttribute("ERROR", "Do not leave form empty.");
                request.getRequestDispatcher(LOGIN_VIEW).forward(request, response);
            } else {
                UserDAO dao = new UserDAO() {

                    @Override
                    public boolean createUser(String email, String fullName, String password) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public boolean checkLogin(String email, String password) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                };
                boolean user = dao.checkLogin(txtemail, txtpassword);
                if (user) {
                    request.setAttribute("ERROR", "Invalid email or password.");
                    request.getRequestDispatcher(LOGIN_VIEW).forward(request, response);
                } else{
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                    response.sendRedirect(HOME_VIEW);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}
