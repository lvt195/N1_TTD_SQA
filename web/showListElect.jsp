<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="DAL.elecRegitrationDAO" %>
<%@ page import="Model.elecRegistration" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/menu.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/trangchu.css">
        <title>Quản lí đăng ký mua điện</title>
    </head>
    <body>
        <!--Kiểm tra xem người dùng đã đăng nhập hay chưa-->
        <% HttpSession sss = request.getSession();
            String userRole = (String) sss.getAttribute("vaitro");
            if (userRole == null || !userRole.equals("admin")) {
                // Nếu không có vai trò hoặc vai trò không phải là "admin", chuyển hướng đến trang khác hoặc hiển thị thông báo lỗi
                response.sendRedirect("unauthorized.jsp");
            } else {%>
            <jsp:include page="menuadmin.jsp" />
            <div class="container-fluid" style="padding: 120px 40px 0px 40px" >
                <h1 class="text-center" style="padding-bottom: 20px ">Quản lý đăng ký mua điện tại Hà Nội</h1>
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Họ tên</th>
                        <th scope="col">Số điện thoại</th>
                        <th scope="col" >Địa chỉ dùng điện</th>
                        <th scope="col">Quận, huyện</th>
                        <th scope="col">Phường, xã</th>
                        <th scope="col">Căn cước</th>
                        <th scope="col">Số pha</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listElecRegis}" var="elec">
                        <tr>
                            <td>${elec.id}</td>
                            <td>${elec.fullName}</td>
                            <td>${elec.phone}</td>
                            <td>${elec.elecAddress}</td>
                            <td>${elec.district}</td>
                            <td>${elec.wards}</td>
                            <td>${elec.idCard}</td>
                            <td>${elec.phaseNumber}</td>
                            <td>${elec.status}</td>
                            <td>
                                <a href="UpdateElec?id=${elec.id}" class="btn btn-primary" style="cursor: pointer">Update</a>
                                <a href="javascript:void(0);" onclick="showmessage(${elec.id})" class="btn btn-danger" style="cursor: pointer">Xoá</a>
                                <a href="detailElec?id=${elec.id}" class="btn btn-danger" style="cursor: pointer">Chi tiết</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>         
        </div>

       <form action="update">

        </form>
        <% }%>
    </body>
    <script>
        function showmessage(id){
            var option=confirm('Ban có thực sự muốn xoá thông tin khách hàng này không?');
            console.log(option);
            if (option===true){
                window.location.href = 'deleteRegis?id=' + id;
            } 
        }
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>

</html>