package service;

import dao.SearchhistoryDAO;
import dao.UserDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserService implements UserServicelmpl {
    private UserDAO userDAO;
    private SearchhistoryDAO searchhistoryDAO;
    public UserService() {
        this.userDAO = new UserDAO();
        this.searchhistoryDAO = new SearchhistoryDAO();
    }

    @Override
    public boolean CheckLogin(String id, String password){ return userDAO.getUserloginData(id,password); }
    @Override
    public boolean DeleteUser(String id){return userDAO.DelUserData(id);}
    @Override
    public void addSearchHistory(String userId, String search) {
        searchhistoryDAO.addSearchHistory(userId, search);  // user_id와 search를 사용하여 검색 기록 추가
    }
    @Override
    public String formattedDttm(LocalDateTime date) {
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        return date.format(FORMATTER);
    }

    @Override
    public LocalDateTime parseDateTime(String dateTimeStr) {
        return dateTimeStr == null || dateTimeStr.isEmpty()
                ? null : LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
