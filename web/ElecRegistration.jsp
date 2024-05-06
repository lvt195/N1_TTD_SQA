<%-- 
    Document   : ElecRegistration
    Created on : Mar 24, 2024, 5:21:12 PM
    Author     : tuyen
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="DAL.districtDAO" %>
<%@ page import="Model.district" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký mua điện</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    </head>
    <body>
        <% HttpSession sss = request.getSession();
            String userRole = (String) sss.getAttribute("vaitro");
            if (userRole != null) {
                if (userRole.equals("admin")) {
        %>
        <jsp:include page="menuadmin.jsp" />
        <%
        } else {
        %>
        <jsp:include page="menuuser.jsp" />
        <%
            }
        } else {
        %>
        <jsp:include page="menu.jsp" />
        <%
            }
        %>
        <div class="container" style="padding-top: 120px">
            <h1 style="text-align: center">Đăng ký mua điện</h1>
            <form action="ElecRegistration" method="POST" autocomplete="off" id="regis" enctype="multipart/form-data">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Họ tên người yêu cầu *</th>
                            <th>Điện thoại liên hệ *</th>
                            <th>Email liên hệ</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" name="fullName" value="" id="fullName" class="form-control" /></td>
                            <td><input type="text" name="phone" value="" id="phone" maxlength="10" class="form-control" /></td>
                            <td><input type="text" name="gmail" value="" class="form-control" /></td>
                        </tr>

                        <tr>
                            <th>Nơi thường trú *</th>
                        </tr>
                        <tr>
                            <td><input type="text" name="placeOfResidence" value="" id="placeOfResidence" class="form-control" /></td>
                        </tr>
                        <tr>
                            <th>Địa chỉ dùng điện *</th>
                        </tr>
                        <tr>
                            <td><input type="text" name="elecAddress" value="" id="elecAddress" class="form-control" /></td>
                        </tr>

                        <tr>
                            <th>Tỉnh, thành phố *</th>
                            <th>Quận, huyện *</th>
                            <th>Phường xã *</th>
                        </tr>

                        <tr>
                            <td>
                                <select id="city" name="city" class="form-control">
                                    <option value="city">Hà Nội</option>
                                </select>
                            </td>
                            <td>
                                <select name="district" id="district" onchange="loadWards" class="form-control">
                                    <option>--Chọn Quận/Huyện--</option>
                                    <c:forEach items="${listDis}" var="item">
                                        <option>${item.nameOfDistrict}</option>
                                    </c:forEach>
                                </select> 
                            </td>

                            <td>
                                <select name="wards" id="wards" class="form-control">
                                    <option>---Chọn phường xã---</option>
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
                                <input type="text" name="idCard" value="" class="form-control" id="IdCard"/>
                            </td>
                            <td>
                                <input type="date" name="dateOfId" value="" class="form-control" id="dateOfIdCard"/>
                            </td>
                            <td>
                                <input type="text" name="placeOfId" value="" class="form-control" id="placeOfIdCard"/>
                            </td>
                            <td>
                                <select name="phaseNumber" class="form-control">
                                    <option>1</option>
                                    <option>3</option>
                                </select> 
                            </td> 
                        </tr>
                        <tr>
                            <td><h4>Hồ Sơ khách hàng cung cấp:</h4></td>
                        </tr>
                        <tr>
                            <td>Giấy tờ tuỳ thân</td>
                        </tr>
                        <tr>
                            <td>Giấy tờ xác định chủ thể</td>
                        </tr>

                        <tr>
                            <td>Hình ảnh xác thực</td>
                            <td>
                                Nhập mã xác thực trong ảnh
                            </td>
                    </tbody>
                </table>
                <input type="submit" value="Đăng ký" id="submit" class="btn btn-primary"/>
            </form>
        </div>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </body>
    <script>
                                    window.addEventListener('pageshow', function (event) {
                                        var historyTraversal = event.persisted || (typeof window.performance != 'undefined' && window.performance.navigation.type === 2);
                                        if (historyTraversal) {
                                            // Xóa dữ liệu mẫu
                                            document.getElementById("regis").reset();
                                        }
                                    });
                                    //ham validate ho ten
                                    function validateHoTen(hoTen) {
                                        var pattern = /^([a-zA-ZáàảãạăắằẳẵặâấầẩẫậÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬđĐéèẻẽẹêếềểễệÉÈẺẼẸÊẾỀỂỄỆíìỉĩịÍÌỈĨỊóòỏõọôốồổỗộơớờởỡợÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢúùủũụưứừửữựÚÙỦŨỤƯỨỪỬỮỰýỳỷỹỵÝỲỶỸỴ\s]){1,10}(?:\s([a-zA-ZáàảãạăắằẳẵặâấầẩẫậÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬđĐéèẻẽẹêếềểễệÉÈẺẼẸÊẾỀỂỄỆíìỉĩịÍÌỈĨỊóòỏõọôốồổỗộơớờởỡợÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢúùủũụưứừửữựÚÙỦŨỤƯỨỪỬỮỰýỳỷỹỵÝỲỶỸỴ\s]){1,10})*$/;
                                        var check = pattern.test(hoTen);
                                        if (!check) {
                                            const fullNameElement = document.getElementById("fullName");
                                            // Nếu chưa tồn tại, thêm thẻ p thông báo nhập sai tên
                                            const errorMessage = document.createElement("p");
                                            errorMessage.id = "errorfullName";
                                            errorMessage.textContent = "Tên không hợp lệ!";
                                            errorMessage.style.color = "red";
                                            errorMessage.style.margin = "0";

                                            // Thêm thẻ p vào element cha của element chứa tên
                                            const parentElement = fullNameElement.parentNode;
                                            parentElement.appendChild(errorMessage);
                                        }
                                        return check;
                                    }

                                    //thuc thi validate ho ten
                                    const fullName = document.getElementById("fullName");
                                    fullName.addEventListener("blur", () => {

                                        const errorMessageElement = document.getElementById("errorfullName");

                                        // Xóa message lỗi trước đó
                                        if (errorMessageElement) {
                                            errorMessageElement.parentNode.removeChild(errorMessageElement);
                                        }
                                        const isValid = validateHoTen(fullName.value);
                                        // Bật/tắt nút submit tùy theo tên hợp lệ hay ko
                                        const submitButton = document.getElementById("submit");
                                        submitButton.disabled = !isValid;
                                    });

                                    function validatePhoneNumber(phoneNumber) {
                                        // Biểu thức Regex kiểm tra số điện thoại VN
                                        const regex = /(((\+|)84)|0)(3|5|7|8|9)+([0-9]{8})\b/g;

                                        // Kiểm tra số điện thoại có hợp lệ hay không
                                        if (!regex.test(phoneNumber)) {
                                            // Lấy element chứa số điện thoại
                                            const phoneElement = document.getElementById("phone");


                                            // Thêm thẻ p thông báo nhập sai số điện thoại
                                            const errorMessage = document.createElement("p");
                                            errorMessage.id = "error-message";
                                            errorMessage.textContent = "Số điện thoại không hợp lệ!";
                                            errorMessage.style.color = "red";
                                            errorMessage.style.margin = "0";

                                            // Thêm thẻ p vào element cha của element chứa số điện thoại
                                            const parentElement = phoneElement.parentNode;
                                            parentElement.appendChild(errorMessage);

                                            return false;
                                        }

                                        return true;
                                    }

                                    const inputElement = document.getElementById("phone");

                                    inputElement.addEventListener("blur", () => {
                                        const errorMessageElement = document.getElementById("error-message");

                                        // Xóa message lỗi trước đó
                                        if (errorMessageElement) {
                                            errorMessageElement.parentNode.removeChild(errorMessageElement);
                                        }
                                        const isValid = validatePhoneNumber(inputElement.value);

                                        // Bật/tắt nút submit tùy theo số điện thoại hợp lệ hay không
                                        const submitButton = document.getElementById("submit");
                                        submitButton.disabled = !isValid;
                                    });

                                    function validateplace(place) {

                                        if (place.trim() === '') {
                                            // Nếu chưa tồn tại, thêm thẻ p thông báo nhập sai tên
                                            console.log("place roongx");
                                            const errorMessage = document.createElement("p");
                                            errorMessage.id = "errorPlace";
                                            errorMessage.textContent = "Điền nơi thường trú hợp lệ!";
                                            errorMessage.style.color = "red";
                                            errorMessage.style.margin = "0";

                                            const placeElement = document.getElementById("placeOfResidence");
                                            // Thêm thẻ p vào element cha của element chứa tên
                                            const parentElement = placeElement.parentNode;
                                            parentElement.appendChild(errorMessage);
                                            return false;
                                        }
                                        return true;
                                    }

                                    const place = document.getElementById("placeOfResidence");
                                    place.addEventListener("blur", () => {

                                        const errorMessageElement = document.getElementById("errorPlace");

                                        // Xóa message lỗi trước đó
                                        if (errorMessageElement) {
                                            errorMessageElement.parentNode.removeChild(errorMessageElement);
                                        }
                                        const isValid = validateplace(place.value);
                                        // Bật/tắt nút submit tùy theo tên hợp lệ hay ko
                                        const submitButton = document.getElementById("submit");
                                        submitButton.disabled = !isValid;
                                    });

                                    function validateaddress(address) {

                                        if (address.trim() === '') {
                                            // Nếu chưa tồn tại, thêm thẻ p thông báo nhập sai tên
                                            const errorMessage = document.createElement("p");
                                            errorMessage.id = "errorAddress";
                                            errorMessage.textContent = "Điền địa chỉ dùng điện hợp lệ!";
                                            errorMessage.style.color = "red";
                                            errorMessage.style.margin = "0";

                                            const placeElement = document.getElementById("elecAddress");
                                            // Thêm thẻ p vào element cha của element chứa tên
                                            const parentElement = placeElement.parentNode;
                                            parentElement.appendChild(errorMessage);
                                            return false;
                                        }
                                        return true;
                                    }

                                    const address = document.getElementById("elecAddress");
                                    address.addEventListener("blur", () => {

                                        const errorMessageElement = document.getElementById("errorAddress");

                                        // Xóa message lỗi trước đó
                                        if (errorMessageElement) {
                                            errorMessageElement.parentNode.removeChild(errorMessageElement);
                                        }
                                        const isValid = validateaddress(address.value);
                                        // Bật/tắt nút submit tùy theo tên hợp lệ hay ko
                                        const submitButton = document.getElementById("submit");
                                        submitButton.disabled = !isValid;
                                    });

                                    function validatedistrict(district) {
                                        if (district === '--Chọn Quận/Huyện--') {
                                            // Nếu chưa tồn tại, thêm thẻ p thông báo nhập sai tên
                                            const errorMessage = document.createElement("p");
                                            errorMessage.id = "errorDistrict";
                                            errorMessage.textContent = "Vui lòng chọn Quận, Huyện!";
                                            errorMessage.style.color = "red";
                                            errorMessage.style.margin = "0";

                                            const district = document.getElementById("district");
                                            // Thêm thẻ p vào element cha của element chứa tên
                                            const parentElement = district.parentNode;
                                            parentElement.appendChild(errorMessage);
                                            return false;
                                        }
                                        return true;
                                    }




                                    const district = document.getElementById("district");
                                    district.addEventListener("blur", () => {
                                        const errorMessageElement = document.getElementById("errorDistrict");

                                        // Xóa message lỗi trước đó
                                        if (errorMessageElement) {
                                            errorMessageElement.parentNode.removeChild(errorMessageElement);
                                        }
                                        const isValid = validatedistrict(district.value);
                                        // Bật/tắt nút submit tùy theo tên hợp lệ hay ko
                                        const submitButton = document.getElementById("submit");
                                        submitButton.disabled = !isValid;
                                    });

//                                    const btn = document.getElementById("submit");
//                                    btn.addEventListener("click", () => {
//                                        const errorMessageElement = document.getElementById("errorDistrict");
//                                        // Xóa message lỗi trước đó
//                                        if (errorMessageElement) {
//                                            errorMessageElement.parentNode.removeChild(errorMessageElement1);
//                                        }
//                                        const isValid = validatedistrict(district.value);
//                                        // Bật/tắt nút submit tùy theo tên hợp lệ hay ko
//                                        const submitButton = document.getElementById("submit");
//                                        submitButton.disabled = !isValid;
//                                    });

                                    function validateWards(ward) {
                                        if (ward === '---Chọn phường xã---') {
                                            // Nếu chưa tồn tại, thêm thẻ p thông báo nhập sai tên
                                            const errorMessage = document.createElement("p");
                                            errorMessage.id = "errorWard";
                                            errorMessage.textContent = "Vui lòng chọn Phường, Xã!";
                                            errorMessage.style.color = "red";
                                            errorMessage.style.margin = "0";

                                            const wards = document.getElementById("wards");
                                            // Thêm thẻ p vào element cha của element chứa tên
                                            const parentElement = wards.parentNode;
                                            parentElement.appendChild(errorMessage);
                                            return false;
                                        }
                                        return true;
                                    }

                                    const wards = document.getElementById("wards");
                                    wards.addEventListener("blur", () => {
                                        const errorMessageElement = document.getElementById("errorWard");

                                        // Xóa message lỗi trước đó
                                        if (errorMessageElement) {
                                            errorMessageElement.parentNode.removeChild(errorMessageElement);
                                        }
                                        const isValid = validateWards(wards.value);
                                        // Bật/tắt nút submit tùy theo tên hợp lệ hay ko
                                        const submitButton = document.getElementById("submit");
                                        submitButton.disabled = !isValid;
                                    });

                                    function validateId(Id) {
                                        // Biểu thức Regex kiểm tra số điện thoại VN
                                        const regex = /^\d{9,12}$/;

                                        // Kiểm tra số điện thoại có hợp lệ hay không
                                        if (!regex.test(Id)) {
                                            // Lấy element chứa số điện thoại
                                            const IdCardElement = document.getElementById("IdCard");


                                            // Thêm thẻ p thông báo nhập sai số điện thoại
                                            const errorMessage = document.createElement("p");
                                            errorMessage.id = "error-IdCard";
                                            errorMessage.textContent = "CCCD không hợp lệ!";
                                            errorMessage.style.color = "red";
                                            errorMessage.style.margin = "0";

                                            // Thêm thẻ p vào element cha của element chứa số điện thoại
                                            const parentElement = IdCardElement.parentNode;
                                            parentElement.appendChild(errorMessage);

                                            return false;
                                        }

                                        return true;
                                    }

                                    const IdCard = document.getElementById("IdCard");

                                    IdCard.addEventListener("blur", () => {
                                        const errorMessageElement = document.getElementById("error-IdCard");

                                        // Xóa message lỗi trước đó
                                        if (errorMessageElement) {
                                            errorMessageElement.parentNode.removeChild(errorMessageElement);
                                        }
                                        const isValid = validateId(IdCard.value);

                                        // Bật/tắt nút submit tùy theo số điện thoại hợp lệ hay không
                                        const submitButton = document.getElementById("submit");
                                        submitButton.disabled = !isValid;
                                    });

                                    function validatengaycapId(dateOfIdCard) {

                                        if (dateOfIdCard.trim() === '') {
                                            // Nếu chưa tồn tại, thêm thẻ p thông báo nhập sai tên
                                            const errorMessage = document.createElement("p");
                                            errorMessage.id = "errordateOfIdCard";
                                            errorMessage.textContent = "Ngày cấp căn cước chưa hợp lệ!";
                                            errorMessage.style.color = "red";
                                            errorMessage.style.margin = "0";

                                            const dateOfIdCardElement = document.getElementById("dateOfIdCard");
                                            // Thêm thẻ p vào element cha của element chứa tên
                                            const parentElement = dateOfIdCardElement.parentNode;
                                            parentElement.appendChild(errorMessage);
                                            return false;
                                        }
                                        return true;
                                    }

                                    const dateOfIdCard = document.getElementById("dateOfIdCard");
                                    dateOfIdCard.addEventListener("blur", () => {

                                        const errorMessageElement = document.getElementById("errordateOfIdCard");

                                        // Xóa message lỗi trước đó
                                        if (errorMessageElement) {
                                            errorMessageElement.parentNode.removeChild(errorMessageElement);
                                        }
                                        const isValid = validatengaycapId(dateOfIdCard.value);
                                        // Bật/tắt nút submit tùy theo tên hợp lệ hay ko
                                        const submitButton = document.getElementById("submit");
                                        submitButton.disabled = !isValid;
                                    });

                                    function validatenoicapId(placeOfIdCard) {

                                        if (placeOfIdCard.trim() === '') {
                                            // Nếu chưa tồn tại, thêm thẻ p thông báo nhập sai tên
                                            const errorMessage = document.createElement("p");
                                            errorMessage.id = "errorplaceOfIdCard";
                                            errorMessage.textContent = "Noi cấp căn cước chưa hợp lệ!";
                                            errorMessage.style.color = "red";
                                            errorMessage.style.margin = "0";

                                            const dateOfIdCardElement = document.getElementById("placeOfIdCard");
                                            // Thêm thẻ p vào element cha của element chứa tên
                                            const parentElement = dateOfIdCardElement.parentNode;
                                            parentElement.appendChild(errorMessage);
                                            return false;
                                        }
                                        return true;
                                    }

                                    const placeOfIdCard = document.getElementById("placeOfIdCard");
                                    placeOfIdCard.addEventListener("blur", () => {

                                        const errorMessageElement = document.getElementById("errorplaceOfIdCard");

                                        // Xóa message lỗi trước đó
                                        if (errorMessageElement) {
                                            errorMessageElement.parentNode.removeChild(errorMessageElement);
                                        }
                                        const isValid = validatenoicapId(placeOfIdCard.value);
                                        // Bật/tắt nút submit tùy theo tên hợp lệ hay ko
                                        const submitButton = document.getElementById("submit");
                                        submitButton.disabled = !isValid;
                                    });


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
