package com.example.dao;
import com.example.dto.SalesPost;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@Repository
public interface SalesPostRepository extends JpaRepository<SalesPost, String> {

    SalesPost findByUserId(String userId);
}
