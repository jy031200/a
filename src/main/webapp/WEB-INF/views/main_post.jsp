<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.example.dto.SalesPost,controller.UserController"%>
<!DOCTYPE html>
<html>
<% SalesPost salesPost = (SalesPost) request.getAttribute("salesPost");
    if(salesPost != null){
%>
<head>
    <meta charset="UTF-8"> <%--판매글 상세보기--%>
    <title>${salesPost.title}</title>
    <script>
    </script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .main-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

    </style>
</head>
<body>
    <div class="main-container">
<%--        <p class="img" id="img" name="img">${SalesPost.img}</p>--%>
        <div class="title-container">
            <p class="title" id="title" name="title">${salesPost.title}</p>
            <p class="categori" id="categori" name="categori">${salesPost.categori}</p>
        </div>
        <hr>
        <div class="content-container">
            <p class="content" id="content" name="content">${salesPost.content}</p>
        </div>
        <hr>
        <p class="date" id="date" name="date">${salesPost.create_at}</p>
        <hr>
        <p class="price" id="price" name="price">${salesPost.price}</p>
        <button class="btn_CreatePost" id="btn_CreatePost" onclick="GoCart()">
            <i class="text_CreatePost">구매 하기</i> <%--장바구니 화면 이동--%>
        </button>
    </div>
    <%
    } else {
    %>
    <p>사용자 정보가 없습니다.</p>
    <%
        }
    %>
</body>
</html>