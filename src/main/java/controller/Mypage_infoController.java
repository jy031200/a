package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dto.User;
import service.UserService;

@WebServlet("/mypage_info")
public class Mypage_infoController extends HttpServlet {
    private final UserService userService;
    private final UserDAO userDAO;

    public Mypage_infoController() {
        this.userService = new UserService();
        this.userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = (String) request.getSession().getAttribute("userId");

        if (id != null) {
            User user = userDAO.getUserData(id);
            if(user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/mypage_info.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}