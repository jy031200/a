package com.example.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity // 데이터베이스 테이블과 연결된 JPA 엔티티
@Table(name="salespost")
@Setter // 클래스의 필드 값을 수정하는 역할
@Getter // 클래스의 필드 값을 읽어오는 역할
public class SalesPost {

    @GeneratedValue(strategy = GenerationType.IDENTITY)// 자동 증가 (auto-increment)
    private int id;

    @Id // 기본 키(PK)
    private String userId;

    private String title;
    private String content;
    private int price;
    private String categori;
    private String img;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    // 기본 생성자
    public SalesPost() {}

    //생성자
    public SalesPost(String userId, String title, String content, int price, String categori, String img, LocalDateTime created_at) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.price = price;
        this.categori = categori;
        this.img = img;
        this.created_at = created_at;
    }
}