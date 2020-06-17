<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: zym
  Date: 2020/3/10
  Time: 8:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homework System</title>
    <style>
        .button {
            display: inline-block;
            padding: 15px 25px;
            font-size: 24px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            outline: none;
            color: #fff;
            background-color: #4CAF50;
            border: none;
            border-radius: 15px;
            box-shadow: 0 9px #999;
        }

        .button:hover {background-color: #3e8e41}

        .button:active {
            background-color: #3e8e41;
            box-shadow: 0 5px #666;
            transform: translateY(4px);
        }
    </style>
</head>

<body align="center">
<h1 >
    简易作业管理系统
</h1>

<div class="foor">

    <div class="button"><span onclick="window.location='sallHomework'">查询作业</span></div>

</div>

<br/><br/>

<div class="foor">

    <div class="button"><span onclick="window.location='submitallHomework'">提交作业</span></div>

</div>

</body>

</html>
