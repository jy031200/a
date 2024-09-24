package com.example.controller;

import dao.SalesPostRepository;
import com.example.dto.SalesPost;
import dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@Slf4j
public class SalesPostController {

    @Autowired
    private SalesPostRepository salesPostRepository;

    @PostMapping("/savePost")
    public String saveSalesPost(@RequestParam("userId") String userId,
                                @RequestParam("title") String title,
                                @RequestParam("content") String content,
                                @RequestParam("categori") String categori,
                                @RequestParam("price") int price)
                                /*@RequestParam("img") String img)*/ {


        log.info("userId: {}", userId);
        log.info("title: {}", title);
        log.info("content: {}", content);
        log.info("categori: {}", categori);
        log.info("price: {}", price);

        SalesPost salesPost = new SalesPost();
        salesPost.setUserId(userId);
        salesPost.setTitle(title);
        salesPost.setContent(content);
        salesPost.setCategori(categori);
        salesPost.setPrice(price);
        /*salesPost.setImg(img);*/

        salesPost.setCreated_at(LocalDateTime.now());

        salesPostRepository.save(salesPost);
        return "redirect:/create_post";
    }

    @GetMapping("/create_post")
    public String showMainPage() {
        return "create_post"; // /WEB-INF/views/main.jsp로 매핑됨
    }

    @GetMapping("/showPost")
    public String showPost(@RequestParam("userId") String userId, Model model) {
        SalesPost salesPost1 = salesPostRepository.findByUserId(userId);
        model.addAttribute("salesPost", salesPost1);
        return "/main_post.jsp"; // JSP 페이지 이름
    }

    @GetMapping("/createPost")
    public String showCreatePostForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user"); // 세션에서 유저 정보 가져오기
        if (user != null) {
            model.addAttribute("user", user); // 모델에 사용자 정보 추가
        }
        return "create_post"; // create_post.jsp로 이동
    }
}

