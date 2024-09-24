<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="dto.User, controller.SalesPostController"%>
<%@ page import="com.sun.tools.jconsole.JConsoleContext" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"> <%--판매글 작성--%>
    <title>판매글 작성</title>
    <script src="/resource/js/index.js" defer></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        .header-container{
            margin-top: -10px;
            margin-bottom: -10px;
        }
        .header-container h3 {
            text-align: center;
            margin-bottom: 20px;
        }
        .title-container {
            display: flex;
            flex-direction: column;
            margin-left: 5px;
            margin-right: 5px;
        }
        .title-container label {
            margin-bottom: 5px;
            font-weight: bold;
        }
        .title-container input[type="text"] {
            height: 30px;
            padding-left: 10px;
            border-radius: 5px;
        }
        .price-container input[type="number"]{
            height: 30px;
            padding-left: 10px;
            border-radius: 5px;
            -moz-appearance: textfield;
        }
        .price-container input[type="number"]::-webkit-outer-spin-button,
        .price-container input[type="number"]::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        .categori-container {
            display: flex;
            margin-left: 5px;
            align-items: center;
        }
        .categori-container select {
            margin-left: 40px;
            width: 180px;
            height: 30px;
            border-radius: 5px;
            text-align: center;
            margin-top: 10px;
        }
        .content-container {
            display: flex;
            flex-direction: column;
            margin-left: 5px;
            margin-right: 5px;
        }
        .content-container label,
        .price-container label {
            margin-top: 10px;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .price-container {
            display: flex;
            flex-direction: column;
            margin-left: 5px;
            margin-right: 5px;
            -moz-appearance: textfield;
        }
        .categori-container p {
            margin-top: 15px;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .content-container #content {
            height: 150px;
            padding-left: 10px;
            padding-top: 10px;
            border: 2px solid black;
            font-size: 13px;
            font-family: Arial;
            border-radius: 5px;
            resize: none;
        }
        .btn_CreatePost {
            width: 85%;
            height: 35px;
            background-color: cornflowerblue;
            color: white;
            border-radius: 5px;
            margin-top: 5px;
            margin-bottom: -10px;
            margin-left: 22px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <form action="/savePost" method="post">
            <div class="header-container">
                <h3>판매글 작성</h3>
            </div>
            <hr>
            <input type="hidden" id="userId" name="userId" value= "jy031200@naver.com">
            <div class="title-container">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" placeholder="제목" maxlength="20" oninput="CheckLengthTitle()" required>
            </div>
            <div class="categori-container">
                <p for="catagori">카테고리</p>
                <select id="caragori" name="categori">
                    <option value="digital">디지털 기기</option>
                    <option value="lifestyle">생활가전</option>
                    <option value="clothes">옷</option>
                </select>
            </div>
            <div class="content-container">
                <label for="content">제품 설명</label>
                <textarea id="content" name="content" placeholder="제품에 대한 자세한 설명을 적어주세요." maxlength="100" oninput="CheckLengthContent()" required></textarea>
            </div>
            <div class="price-container">
                <label for="price">가격</label>
                <input type="number" id="price" name="price" placeholder="&#8361; 가격을 입력해주세요." min="0" required>
            </div>
            <hr>
            <button type="submit" class="btn_CreatePost">작성 완료</button>
        </form>
    </div>
</body>
</html>
