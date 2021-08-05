<%--
  Created by IntelliJ IDEA.
  User: 子期
  Date: 2018/8/5
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>测试过滤器_监听器_Servlet</title>
    </head>

    <body>
        <p>${hello}</p>
        <a href="MyServlet">请求</a>
        <form action="MyServlet" method="post">
          <input type="submit" value="提交">
        </form>
    </body>
</html>
