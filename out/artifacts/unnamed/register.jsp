<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
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

        #register {
            color: black;
            font-size: 25px;
            font-weight: bold;
            text-align: center;
        }

        .register-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        .register-container label {
            display: block;
            margin-top: 10px;
        }
        .register-container input[type="text"],
        .register-container input[type="password"] {
            width: 100%;
            height: 20pt;
            padding-right: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .register-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }
        .register-container input[type="submit"]:hover {
            background-color: #45a049;
        }
        .id-container {
            margin-top: 5px;
            display: flex;
        }
        #id {
            width: 50%;
            padding-right: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        #at-sign{
            margin-right: 10px;
            margin-left: 10px;
        }
        #options {
            width: 40%;
            height: 30px;
            font-size: 13px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="register-container">
    <p id="register">회원가입</p>
    <form action="register" method="post">
        <label for="name">이름</label>
        <input type="text" id="name" name="name" required>
        <label for="id">ID (이메일 형식)</label>
        <div class="id-container">
            <input type="email" id="id" name="id" required>
<%--            <p id="at-sign">@</p>
            <select id="options" name="options">
                <option value="naver">naver.com</option>
                <option value="google">gmail.com</option>
            </select>--%>
        </div>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
        <label for="number">전화번호</label>
        <input type="text" id="number" name="number" required>
        <input type="submit" value="회원가입 하기">
    </form>
</div>
</body>
</html>
