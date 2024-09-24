package controller;

import dao.UserDAO;
import dto.User;

import org.json.JSONObject;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.json.JSONException;

@WebServlet("/mypage")
public class MypageController extends HttpServlet {
    private final UserService userService;
    private final UserDAO userDAO;

    public MypageController() {
        this.userService = new UserService();
        this.userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.sendRedirect("/mypage");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = (String) request.getSession().getAttribute("userId");

        if (id != null) {
            User user = userDAO.getUserData(id);
            if(user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/mypage.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();
        String id = request.getParameter("id");

        if (id != null) {
            try {
                boolean isDeleted = this.userService.DeleteUser(id);
                jsonResponse.put("message", isDeleted ? "회원탈퇴 성공" : "회원 탈퇴 실패");
                response.getWriter().write(jsonResponse.toString());
            } catch (IOException | JSONException e) {
                jsonResponse.put("message", "오류 발생: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(jsonResponse.toString());
            }
        } else {
            jsonResponse.put("message", "잘못된 ID 형식입니다.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(jsonResponse.toString());
        }
    }
}
