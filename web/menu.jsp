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
        
        <div style="position: fixed;background-color: white; width: 100%">
        <div class="menu">
            <div class="tacvu">
                 Hỗ trợ 24/7: Nhóm 01 Đảm bảo chất lượng phần mềm
            </div>
            
            <div class="tacvu">
             <button class="button" onclick = "document.location = 'http://localhost:8080/BaiCoNgoc/dangnhap.jsp'" > Đăng Nhập </button>
             <button class="button" onclick = "document.location = 'http://localhost:8080/BaiCoNgoc/dangky.jsp'" > Đăng ký </button>
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
               
                <form action="${pageContext.request.contextPath}/#"> <input type="submit" class="button" value="Đăng kí mua điện"> </form>
            </div>
        </div>
        </div>
    </body>
</html>
