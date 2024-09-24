package service;

import java.time.LocalDateTime;

public interface UserServicelmpl {
    public boolean CheckLogin(String id, String password);

    public boolean DeleteUser(String id);

    public void addSearchHistory(String userId, String search);

    String formattedDttm(LocalDateTime date);

    LocalDateTime parseDateTime(String dateTimeStr);
}
