package dao;

import dto.User;

import java.util.List;

public interface UserDAOlmpl {
    public void addUserData(List<User> userList);
    public boolean getUserloginData(String id, String password);
    public User getUserData(String ID);

    public boolean DelUserData(String ID);
}
