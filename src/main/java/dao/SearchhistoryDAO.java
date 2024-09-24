package dao;

import dto.Searchhistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchhistoryDAO implements SearchhistoryDAOlmpl {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/commerce";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "user";

    private Connection conn = null;
    private PreparedStatement pstmt = null;

    @Override
    public void addSearchHistory(String user_id, String search) {
        String query = "INSERT INTO SearchHistory (user_id, search) VALUES (?, ?)";

        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, user_id);
            pstmt.setString(2, search);

            pstmt.executeUpdate();
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();  // 트랜잭션 롤백
                    System.out.println("Transaction rolled back due to error.");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }

    @Override
    public List<Searchhistory> getSearchhistory(String user_id) {
        String query = "SELECT * FROM searchhistory WHERE user_id=? ORDER BY search_date desc limit 5";
        List<Searchhistory> searchhistoryList = new ArrayList<>();

        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, user_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Searchhistory searchhistory = new Searchhistory();

                searchhistory.setUser_id(rs.getString("user_id"));
                searchhistory.setSearch(rs.getString("search"));
                searchhistory.setSearch_date(Timestamp.valueOf(rs.getString("search_date")));

                searchhistoryList.add(searchhistory);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return searchhistoryList;
    }

    @Override
    public boolean DelSearchHistory(String user_id) { // 전체 삭제
        boolean isdel = false;
        String query = "DELETE FROM searchhistory WHERE user_id = ?";
        String resetAutoIncrementQuery = "ALTER TABLE searchhistory AUTO_INCREMENT = 1";

        Statement stmt = null;

        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(query);
            conn.setAutoCommit(false);

            pstmt.setString(1,user_id);

            int rowAffected = pstmt.executeUpdate();

            stmt = conn.createStatement();
            stmt.executeUpdate(resetAutoIncrementQuery);
            if(rowAffected > 0){
                isdel = true;
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        return isdel;
    }

    @Override
    public boolean deleteSearchHistoryBySearchText(String user_id, String search) { // 전체 삭제
        boolean isdel = false;
        String query = "DELETE FROM searchhistory WHERE user_id = ? AND search = ?";


        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(query);
            conn.setAutoCommit(false);

            pstmt.setString(1,user_id);
            pstmt.setString(2, search);

            int rowAffected = pstmt.executeUpdate();

            if(rowAffected > 0){
                isdel = true;
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        return isdel;
    }
}
