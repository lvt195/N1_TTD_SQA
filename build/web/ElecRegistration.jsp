<%-- 
    Document   : ElecRegistration
    Created on : Mar 24, 2024, 5:21:12 PM
    Author     : tuyen
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="DAL.districtDAO" %>
<%@ page import="Model.district" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng ký mua điện</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/menuuser.jsp"/>
        <div class="container" style="padding-top: 100px ">
            <h1 style="text-align: center">Đăng ký mua điện</h1>
            <form action="ElecRegistration" method="POST">
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Họ tên người yêu cầu *</th>
                                <th scope="col">Điện thoại liên hệ *</th>
                                <th scope="col">Email liên hệ</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><input type="text" class="form-control" name="fullName" id="fullName" required></td>
                                <td><input type="tel" class="form-control" name="phone" id="phone" maxlength="10" required></td>
                                <td><input type="email" class="form-control" name="gmail"></td>
                            </tr>
                            <tr>
                                <td colspan="3"><input type="text" class="form-control" name="placeOfResidence" id="placeOfResidence" placeholder="Nơi thường trú"></td>
                            </tr>
                            <tr>
                                <td colspan="3"><input type="text" class="form-control" name="elecAddress" id="elecAddress" placeholder="Địa chỉ dùng điện"></td>
                            </tr>
                            <tr>
                                <td>
                                    <select class="form-select" name="city" id="city">
                                        <option value="Hà Nội">Hà Nội</option>
                                    </select>
                                </td>                              
                                <td>
                                    <select class="form-select" name="district" id="district" onchange="loadWards">
                                        <option>--Chọn Quận/Huyện--</option>
                                        <%
                                            districtDAO districtDAO = new districtDAO();
                                            List<district> districts = districtDAO.getAll();
                                            for (district dis : districts) {
                                        %>
                                        <option><%= dis.getNameOfDistrict()%> </option>
                                        <% }%>
                                    </select> 
                                </td>
                                <td>
                                    <select class="form-select" name="wards" id="wards">
                                        <option value="">---Chọn phường xã---</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="text" class="form-control" name="idCard" placeholder="CMND/CCCD/Hộ chiếu *" required></td>
                                <td><input type="date" class="form-control" name="dateOfId" required></td>
                                <td><input type="text" class="form-control" name="placeOfId" placeholder="Nơi cấp *" required></td>
                                <td>
                                    <select class="form-select" name="phaseNumber">
                                        <option value="1">1</option>
                                        <option value="3">3</option>
                                    </select>
                                </td> 
                            </tr>
                            <tr>
                                <td colspan="4"><h4>Hồ Sơ khách hàng cung cấp:</h4></td>
                            </tr>
                            <tr>
                                <td>Giấy tờ tuỳ thân</td>
                                <td colspan="3">Nhập mã xác thực trong ảnh<input type="text" class="form-control"></td>
                            </tr>
                            <tr>
                                <td>Giấy tờ xác định chủ thể</td>
                                <td colspan="3"></td>
                            </tr>
                            <tr>
                                <td>Hình ảnh xác thực</td>
                                <td colspan="3"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <button type="submit" class="btn btn-primary" id="submit">Đăng ký</button>
            </form>
        </div>

        <!-- Bootstrap JS (Optional) -->
        <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script> -->
    </body>
</html>



<script>
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
        }
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
        }
    }

    const address = document.getElementById("elecAddress");
    address.addEventListener("blur", () => {

        const errorMessageElement = document.getElementById("errorAddress");

        // Xóa message lỗi trước đó
        if (errorMessageElement) {
            errorMessageElement.parentNode.removeChild(errorMessageElement);
        }
        const isValid = validateaddress(address.value, elecAddress);
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

    const btn = document.getElementById("submit");
    btn.addEventListener("click", () => {
        const errorMessageElement = document.getElementById("errorDistrict");
        // Xóa message lỗi trước đó
        if (errorMessageElement) {
            errorMessageElement.parentNode.removeChild(errorMessageElement1);
        }
        const isValid = validatedistrict(district.value);
        // Bật/tắt nút submit tùy theo tên hợp lệ hay ko
        const submitButton = document.getElementById("submit");
        submitButton.disabled = !isValid;
    });

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
