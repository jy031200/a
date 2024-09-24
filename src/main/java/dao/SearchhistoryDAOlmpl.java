package dao;

import dto.Searchhistory;

import java.util.List;

public interface SearchhistoryDAOlmpl {

    public void addSearchHistory(String id, String search);
    public List<Searchhistory> getSearchhistory(String user_id);
    public boolean DelSearchHistory(String search);
    public boolean deleteSearchHistoryBySearchText(String user_id, String search);
}
