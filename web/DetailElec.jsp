<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết thông tin đăng ký mua điện</title>

        <!-- Thêm các tệp CSS của Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <% HttpSession sss = request.getSession();
            String userRole = (String) sss.getAttribute("vaitro");
            if (userRole == null || !userRole.equals("admin")) {
                // Nếu không có vai trò hoặc vai trò không phải là "admin", chuyển hướng đến trang khác hoặc hiển thị thông báo lỗi
                response.sendRedirect("unauthorized.jsp");
            } else {%>
        <jsp:include page="menuadmin.jsp" />

        <div class="container" style="padding-top: 120px">
            <h1 class="text-center">Chi tiết thông tin đăng ký mua điện</h1>
            <table class="table table-bordered table-striped" style="margin-top: 10px">
                <thead>
                    <tr>
                        <th>Họ tên người yêu cầu</th>
                        <th>Điện thoại liên hệ</th>
                        <th>Trạng thái</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><p>${item.fullName}</p></td>
                        <td><p>${item.phone}</p></td>
                        <td><p>${item.status}</p></td>
                    </tr>
                    <tr>
                        <th>Email liên hệ</th>
                        <th>Nơi thường trú</th>
                        <th>Địa chỉ dùng điện</th>
                    </tr>
                    <tr>
                        <td><p>${item.gmail}</p></td>
                        <td><p>${item.placeOfResidence}</p></td>
                        <td><p>${item.elecAddress}</p></td>
                    </tr>
                    <tr>
                        <th>Tỉnh, thành phố</th>
                        <th>Quận, huyện</th>
                        <th>Phường xã</th>
                    </tr>
                    <tr>
                        <td><p>${item.city}</p></td>
                        <td><p>${item.district}</p></td>
                        <td><p>${item.wards}</p></td>
                    </tr>
                    
                    <tr>
                        <th>CMND/CCCD/Hộ chiếu</th>
                        <th>Ngày cấp chứng minh thư</th>
                        <th>Nơi cấp</th>
                    </tr>
                    <tr>
                        <td><p>${item.idCard}</p></td>
                        <td><p>${item.dateOfId}</p></td>
                        <td><p>${item.placeOfId}</p></td>
                    </tr>
                    <tr>
                        <th>Số pha</th>
                        <th>Giấy tờ tuỳ thân</th>
                        <th>Giấy tờ xác định chủ thể</th>
                    </tr>
                    <tr>
                        <td><p>${item.phaseNumber}</p></td>
                        <td><p>${item.personalDoc}</p></td>
                        <td><p>${item.dits}</p></td>
                    </tr>
                    
                </tbody>
            </table>
        </div>
        <% }%>

        <!-- Thêm tệp JavaScript của Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>