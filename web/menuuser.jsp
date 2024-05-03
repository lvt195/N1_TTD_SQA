<%-- 
    Document   : menu
    Created on : May 17, 2023, 2:42:07 PM
    Author     : hi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/trangchu.css">
    </head>
    <body>
        <%
            String mes=(String)session.getAttribute("message");
        %>
        <div style="position: fixed;background-color: white">
        <div class="menu">
            <div class="tacvu">
                 Hỗ trợ 24/7: Nhóm 01 Đảm bảo chất lượng phần mềm
            </div>
            
            <div class="tacvu">
                <p><%= mes%></p>
                <form action="${pageContext.request.contextPath}/dangxuatcontroller"> <input type="submit" class="button" value="Đăng xuất"> </form>
                <form action="${pageContext.request.contextPath}/doimatkhaucontroller"> <input type="submit" class="button" value="Đổi Mật Khẩu"> </form>
            </div>
        </div>
        <div class="menu1"> </div>
        <div class="menu">
            <div class="tacvu">
                <a href="${pageContext.request.contextPath }/trangchucontroller" style="text-decoration: none;font-weight: normal;color: inherit;">EVNHANOI</a>
            </div>
            <div class="tacvu">
                <form action="${pageContext.request.contextPath}/trangchucontroller"> <input type="submit" class="button" value="Trang Chủ"> </form>
                <form action="${pageContext.request.contextPath}/congcutinhtiendien.html"> <input type="submit" class="button" value="Công cụ tính tiền điện"> </form>
                <form action="${pageContext.request.contextPath}/bill.html"> <input type="submit" class="button" value="Xem hoá đơn"> </form>
                <form action="${pageContext.request.contextPath}/#"> <input type="submit" class="button" value="Đăng kí mua điện"> </form>
            </div>
        </div>
        </div>
    </body>
</html>
