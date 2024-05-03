<%-- 
    Document   : dangky
    Created on : Apr 27, 2023, 4:06:24 PM
    Author     : hi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/menu.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/dangky.css">
        
    </head>
   <body>
            <jsp:include page="menu.jsp" />
            <div class="dangky">
                <form action="sign-up">
                    <div class="form-group">
                        <label style="font-size: 30px ; color: black">Nhập thông tin tài khoản</label>
                         
                    </div>
                    <div class="form-group">                       
                        <label for="contact">Số điện thoại/Email <span style="color: red">*</span></label>
                        <input type="text" class="form-control" placeholder="Nhập số điện thoại hoặc Email" id="contact" name="contact" required>
                    
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Username <span style="color: red">*</span></label>
                        <input class="form-control" placeholder="Nhập Username" name="username" maxlength="50" pattern="[a-zA-Z0-9]+" required>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Họ và tên <span style="color: red">*</span></label>
                        <input type="text" class="form-control" maxlength="50" pattern="[a-zA-Z0-9\s]+"  aria-describedby="emailHelp" placeholder="Nhập họ và tên tài khoản" name="hovaten" required>
                        
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Số CMND/CCCD/Hộ chiếu <span style="color: red">*</span></label>
                        <input type="text" class="form-control" aria-describedby="emailHelp" maxlength="50" pattern="[0-9]+" placeholder="Nhập số CMND/CCCD/Hộ chiếu" name="dinhdanh" required>
                        
                    </div>
                    <div class="form-group">
                        <label for="password">Mật khẩu <span style="color: red">*</span></label>
                        <input type="password" class="form-control" id="password" placeholder="Mật khẩu" name="password" required>
                    </div>
                    <div class="form-group">
                        <label for="confirm_password">Nhập lại mật khẩu <span style="color: red">*</span></label>
                        <input type="password" class="form-control" id="confirm_password" placeholder="Nhập lại mật khẩu"name="confirm_password" required>
                    </div>
                    
                    
                    <button type="submit" class="btn btn-primary">Đăng ký</button>
                </form>
            </div>
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script>
          // Kiểm tra xem mật khẩu và nhập lại mật khẩu có khớp nhau không
          document.addEventListener("DOMContentLoaded", function() {
            var password = document.getElementById("password");
            var confirm_password = document.getElementById("confirm_password");
            var contact = document.getElementById("contact");

            function validatePassword() {
              var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+={}\[\]:;<>,.?~]).{10,}$/;
                if (!passwordPattern.test(password.value)) {
                  password.setCustomValidity("Mật khẩu phải có ít nhất 10 ký tự và bao gồm chữ hoa, chữ thường, ký tự số và ký tự đặc biệt");
                } else {
                  password.setCustomValidity('');
                }

                if (password.value !== confirm_password.value) {
                  confirm_password.setCustomValidity("Mật khẩu không khớp");
                } else {
                  confirm_password.setCustomValidity('');
                }
            }
            function validateContact() {   
               var contactPattern = /^(?:\+?(84|0)\d{9}|[^\s@]+@[^\s@]+\.[^\s@]+)$/;
                if (!contactPattern.test(contact.value)) {
                  contact.setCustomValidity("Vui lòng nhập số điện thoại hoặc email hợp lệ");
                } else {
                  contact.setCustomValidity('');
                }
            }         

            password.onchange = validatePassword;
            confirm_password.onkeyup = validatePassword;
            contact.oninput = validateContact;
          });
        </script>   
    </body>
</html>
