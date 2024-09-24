package dao;

import dto.User;

import java.sql.*;
import java.util.List;

public class UserDAO implements UserDAOlmpl {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/commerce";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "user";

    private Connection conn = null;
    private PreparedStatement pstmt = null;

    @Override
    public void addUserData(List<User> userList) { // 회원 데이터 저장
        String query = "INSERT INTO user(NAME, ID, PASSWORD, NUMBER) VALUES(?, ?, ?, ?)";

        if(userList != null){
            try {
                Class.forName(DB_DRIVER);
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                conn.setAutoCommit(false);
                pstmt = conn.prepareStatement(query);

                for (User user : userList) {
                    pstmt.setString(1, user.getNAME());
                    pstmt.setString(2, user.getID());
                    pstmt.setString(3, user.getPASSWORD()); // 비밀번호 해시 적용 추후 추가 혹은 고려 중
                    pstmt.setString(4, user.getNUMBER());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
                conn.commit();
            } catch (ClassNotFoundException | SQLException e) {
            }
        } else{
            System.out.println("userList is null");
        }
    }

    @Override
    public boolean getUserloginData(String id, String password) { // 회원 데이터(id,password) 가져오기
        boolean isValidUser = false;

        String query = "SELECT COUNT(*) FROM user WHERE ID = ? AND PASSWORD = ?";

        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, id);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                isValidUser = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            }
        }

        return isValidUser;
    }

    @Override
    public User getUserData(String ID) {
        String query = "SELECT * FROM user WHERE ID = ?";

        User user = null;
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, ID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();

                user.setNAME(rs.getString("NAME"));
                user.setID(rs.getString("ID"));
                user.setPASSWORD(rs.getString("PASSWORD"));
                user.setNUMBER(rs.getString("NUMBER"));
            }

        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            }
        }
        return user;
    }


    @Override
    public boolean DelUserData(String ID) {
        boolean isDeleted = false;
        String query = "DELETE FROM user WHERE ID = ?";

        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            pstmt = conn.prepareStatement(query);
            conn.setAutoCommit(false);

            pstmt.setString(1, ID);

            int rowAffected = pstmt.executeUpdate();
            if(rowAffected>0){
                isDeleted = true;
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (ClassNotFoundException|SQLException e) {
        }
        return isDeleted;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8 && password.length() <= 20;
    }
}