package controller;

import com.google.gson.Gson;

import dao.SearchhistoryDAO;
import dao.UserDAO;
import dto.Searchhistory;

import org.json.JSONObject;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

@WebServlet("/search_history")
public class SearchController extends HttpServlet {
    private final UserService userService;
    private final UserDAO userDAO;
    private final SearchhistoryDAO searchhistoryDAO;

    public SearchController() {
        userService = new UserService();
        userDAO = new UserDAO();
        searchhistoryDAO = new SearchhistoryDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search_text");
        String userId = (String) request.getSession().getAttribute("userId");  // 세션에서 userId 가져오기
        System.out.println("User ID: " + userId);
        System.out.println("Search Text: " + search);


        userService.addSearchHistory(userId, search); // 올바른 user_id와 search를 사용하여 검색 기록 추가
        response.sendRedirect("/main.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String userId = (String) request.getSession().getAttribute("userId");  // 세션에서 userId 가져오기
        List<Searchhistory> searchhistoryList = searchhistoryDAO.getSearchhistory(userId);

        // JSON 변환
        Gson gson = new Gson();
        String json = gson.toJson(searchhistoryList);

        response.getWriter().write(json);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = (String) request.getSession().getAttribute("userId");
        String search = request.getParameter("search_text");
        System.out.println("Search Text: " + id);

        JSONObject jsonResponse = new JSONObject();

        if (id != null) {
            if(search != null) {
                try {
                    boolean isDeleted = this.searchhistoryDAO.deleteSearchHistoryBySearchText(id, search);
                    jsonResponse.put("message", isDeleted ? "삭제 성공" : "삭제 실패");
                    response.getWriter().write(jsonResponse.toString());
                } catch (IOException | JSONException e) {
                    jsonResponse.put("message", "오류 발생: " + e.getMessage());
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write(jsonResponse.toString());
                }
            }
            else {
                try {
                    boolean isDeleted = this.searchhistoryDAO.DelSearchHistory(id);
                    jsonResponse.put("message", isDeleted ? "삭제 성공" : "삭제 실패");
                    response.getWriter().write(jsonResponse.toString());
                } catch (IOException | JSONException e) {
                    jsonResponse.put("message", "오류 발생: " + e.getMessage());
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write(jsonResponse.toString());
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(jsonResponse.toString());
        }

        response.getWriter().close();
    }
}

