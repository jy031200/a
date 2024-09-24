
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Online Commerce</title>
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
        .title-container {
            text-align: center;
            padding-bottom: 5%;
        }
        .title-container h1{
            text-align: center;
            color: black;
            font-size: 38px;
            font-weight: bold;
        }
        .title-container h2{
            text-align: center;
            color: black;
            font-size: 28px;
        }
        .title-container button{
            color: white;
            font-size: 22px;
            background-color: cornflowerblue;
            text-align: center;
            width: 65%;
            height: 35pt;
            border-radius: 15px;
            margin: 0 auto;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<div class="title-container">
    <h1>온라인 커머스</h1>
    <h2>누구나 물건을 사고 팔 수 있는 곳</h2>
    <h2>안심하고 물건을 거래하고 싶다면 시작해보세요!</h2>
    <button type="button" id="btn_start" onclick="redirectToLogin()" ><span>시작하기</span></button>
</div>
</body>
</html>