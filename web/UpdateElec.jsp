
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sửa thông tin đăng ký</title>
    </head>
    <body>
        <% HttpSession sss = request.getSession();
        String userRole = (String) sss.getAttribute("vaitro");
        if (userRole == null || !userRole.equals("admin")) {
            // Nếu không có vai trò hoặc vai trò không phải là "admin", chuyển hướng đến trang khác hoặc hiển thị thông báo lỗi
            response.sendRedirect("unauthorized.jsp");
        } else {%> 
        <form action="UpdateElec" method="POST" autocomplete="off" id="regis">
            <table border="1" style="width: 100%">
                <tr>
                    <th>Họ tên người yêu cầu *</th>
                    <th>Điện thoại liên hệ *</th>
                    <th>Email liên hệ</th>
                </tr>
                <tr>
                    <td><input type="text" name="fullName" value="${item.fullName}" id="fullName" /></td>
                    <td><input type="text" name="phone" value="${item.phone}" id="phone" maxlength="10" /></td>
                    <td><input type="text" name="gmail" value="${item.gmail}" /></td>
                </tr>

                <tr>
                    <th>Nơi thường trú</th>
                </tr>
                <tr>
                    <td><input type="text" name="placeOfResidence" value="${item.placeOfResidence}" id="placeOfResidence" /></td>
                </tr>
                <tr>
                    <th>Địa chỉ dùng điện</th>
                </tr>
                <tr>
                    <td><input type="text" name="elecAddress" value="${item.elecAddress}" id="elecAddress" /></td>
                </tr>

                <tr>
                    <th>Tỉnh, thành phố</th>
                    <th>Quận, huyện</th>
                    <th>Phường xã</th>
                </tr>

                <tr>
                    <td>
                        <select id="city" name="city">
                            <option value="city" >Hà Nội</option>
                        </select>
                    </td>
                    <td>
                        <select name="district" id="district" onchange="loadWards" >
                            <option>${item.district}</option>
                            <c:forEach items="${listDis}" var="item">
                                <option>${item.nameOfDistrict}</option>
                            </c:forEach>
                        </select> 
                    </td>

                    <td>
                        <select name="wards" id="wards" >
                            <option>${item.wards}</option>
                        </select>
                    </td>
                </tr>

                <tr>    
                    <th>CMND/CCCD/Hộ chiếu *</th>
                    <th>Ngày cấp chứng minh thư *</th>
                    <th>Nơi cấp *</th>
                    <th>Số pha *</th>
                </tr>

                <tr>
                    <td>
                        <input type="text" name="idCard" value="${item.idCard}" />
                    </td>
                    <td>
                        <input type="date" name="dateOfId" value="" />
                    </td>
                    <td>
                        <input type="text" name="placeOfId" value="${item.placeOfId}" />
                    </td>
                    <td>
                        <select name="phaseNumber" >
                            <option>${item.phaseNumber}</option>
                            <c:if test = "${item.phaseNumber =='3'}">
                                <option>1</option>
                            </c:if>
                            <c:if test = "${item.phaseNumber =='1'}">
                                <option>3</option>
                            </c:if>
                        </select> 
                    </td> 
                </tr>
                <tr>
                    <td>Giấy tờ tuỳ thân</td>
                </tr>
                <tr>
                    <td>Giấy tờ xác định chủ thể</td>
                </tr>
                <tr>
                    <td><h4>Hồ Sơ khách hàng cung cấp:</h4></td>
                </tr>
                <tr>
                    <td>
                        Trạng thái: 
                        <select name="status">
                            <option>${item.status}</option>
                            <c:if test = "${item.status =='Chờ duyệt'}">
                                <option>Đã duyệt</option>
                            </c:if>
                            <c:if test = "${item.status =='Đã duyệt'}">
                                <option>Chờ duyệt</option>
                            </c:if>
                        </select>
                    </td>
                    <td><input name="id" value="${item.id}" style="visibility: hidden;"/></td>
                </tr>
            </table>
            <input type="submit" value="Cập nhật" id="submit"/>
        </form>
        <% } %>
    </body>
    <script>
        document.getElementById("district").addEventListener("change", loadWards);
        function loadWards() {
            var districtSelect = document.getElementById("district");
            var selectedDistrict = districtSelect.value;
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var WardsSelect = document.getElementById("wards");
                    WardsSelect.innerHTML = xhr.responseText;
                }
            };

            xhr.open("POST", "WardController?district=" + selectedDistrict, true);
            xhr.send();
        }
    </script>

</html>
