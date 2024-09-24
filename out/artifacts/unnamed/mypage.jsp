<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="dto.User,controller.UserController, dao.UserDAO"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <script src="/resource/js/index.js"></script>
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
        .mypage-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        .mypage-container p {
            font-weight: bold;
            font-size: 18px;
            margin: 0 0 10px;
        }
        .myinfo-container {
            margin-top: 10px;
        }
        .myinfo-container input[type="submit"] {
            font-size: 14px;
            color: black;
            background-color: white;
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 2px solid white;
            box-shadow: none;
            cursor: pointer;
        }
        .myinfo-container input[type="submit"]:hover {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
    System.out.println("User object in mypage: " + user);
    if (user != null) {
%>
<div class="mypage-container">
    <p id="name">${user.NAME}</p>
    <hr>
    <div class="myinfo-container">
        <form action="mypage_info" method="get">
            <input type="submit" id="btn_info" value="내 정보">
        </form>
        <input type="submit" id="btn_post" value="내 판매글">
        <input type="submit" id="btn_cart" value="내 장바구니">
    </div>
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