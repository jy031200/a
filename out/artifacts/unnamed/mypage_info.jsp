<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="dto.User"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마이페이지-내 정보</title>
    <script src="/resource/js/index.js" defer></script>
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
        .myinfo-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        .myinfo-container a {
            width: 25px;
            padding: 0px;
            border: none;
            border-radius: 5px;
            background-color: white;
            color: cornflowerblue;
            cursor: pointer;
            font-size: 25px;
            margin-bottom: 5px;
            text-decoration: none;
        }
        .myinfo-container h2 {
            font-size: 20px;
            margin-top: -5px;
            font-weight: bolder;
            text-align: left;
            margin-bottom: -3px;
        }
        .myinfo-container #userinfo {
            font-size: 14px;
        }
        .myinfo-container hr{
            margin-top: -8px;
            padding-top: 0;
        }
        .myname {
            display: flex;
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
            margin-left: 30px;
            margin-right: 10px;
            margin-top: 0;
        }
        .myname #name {
            font-weight: bold;
            font-size: 18px;
        }
        .myname #logout {
            width: 60px;
            padding: 0px;
            border: none;
            border-radius: 5px;
            background-color: white;
            color: cornflowerblue;
            cursor: pointer;

            font-size: 13px;
            margin-left: 50%;
        }
        .user,
        .id,
        .pw {
            margin-top: -18px;
            margin-bottom: -20px;
            display: flex;
            text-align: left;
            font-size: 13px;
        }
        .num {
            font-size: 13px;
            margin-top: -18px;
            margin-bottom: -10px;
            display: flex;
            text-align: left;
        }
        .user #name2{
            margin-left: 52px;
            font-weight: bold;
        }
        .id #id{
            color: black;
            font-weight: bold;
            margin-left: 39px;
        }
        .pw #password{
            color: black;
            font-weight: bold;
            margin-left: 26px;
            padding-top: 5px    ;
        }
        .num #number{
            color: black;
            font-weight: bold;
            margin-left: 39px;
            margin-bottom: 25px;
        }
        .myinfo-container #out {
            width: 60px;
            padding: 0px;
            border: none;
            border-radius: 5px;
            background-color: white;
            color: red;
            cursor: pointer;

            font-size: 10px;
            margin-left: 80%;
        }
    </style>
</head>
<body>
<% User user = (User) request.getAttribute("user");
    System.out.println("User object in mypage_info: " + user);
    if(user != null){

%>
<div class="myinfo-container">
    <form action="mypage" method="get">
        <%--<a href="${pageContext.request.contextPath}/mypage.jsp">&lt;</a>--%>
        <input type="submit" id="btn_info" value="&lt;">
    </form>
    <h2>내 정보 관리</h2>
    <div class="myname">
        <p id="name"> ${user.NAME}</p>
        <button type="button" id="logout" onclick="home()"> 로그아웃</button>
    </div>
    <hr>
    <p id="userinfo">회원 정보</p>
    <div class="user">
        <p id="name_label">이름</p>
        <p id="name2"> ${user.NAME}</p>
    </div>
    <div class="id">
        <p id="id_label">아이디 </p>
        <p id="id">${user.ID}</p>
    </div>
    <div class="pw">
        <p id="pw_label">비밀번호</p>
        <p id="password">************</p>
    </div>
    <div class="num">
        <p id="num_label">연락처 </p>
        <p id="number">${user.NUMBER}</p>
    </div>
    <hr>
    <button type="button" id="out" onclick="deldata('<%= user.getID() %>')"> 회원 탈퇴</button>
</div>
<%
    } else {
%>
<p>사용자 정보 없음.</p>
<%
    }
%>

</body>
</html>
