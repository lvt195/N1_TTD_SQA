<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đổi mật khẩu</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/dangky.css">
        <link rel="stylesheet" href="css/menu.css">
        <link rel="stylesheet" href="css/dangnhap.css">
         </head>
    <body>
        <%String vt=(String)session.getAttribute("vaitro");
        if(vt.equals("user"))
        %><jsp:include page="/menuuser.jsp"/>
        <%
         if(vt.equals("admin")){
        
        %>
        <jsp:include page="/menuadmin.jsp"/>
        <%
            }
        %>
        <div class="dangky">
            <form action="${pageContext.request.contextPath}/doimatkhautrongdatabasecontroller">
                <div class="form-group">
                        <label style="font-size: 30px ; color: black">Đổi mật khẩu </label>
                    </div>
                <div class="form-group">
                        <label for="password">Mật khẩu hiện tại <span style="color: red">*</span></label>
                        <input type="password" class="form-control" id="crpassword" placeholder="Mật khẩu" name="crpass" required>
                    </div>
                <div class="form-group">
                        <label for="password">Mật khẩu mới <span style="color: red">*</span></label>
                        <input type="password" class="form-control" id="password" placeholder="Mật khẩu" name="newpass1" required>
                    </div>
                    <div class="form-group">
                        <label for="confirm_password">Nhập lại mật khẩu mới <span style="color: red">*</span></label>
                        <input type="password" class="form-control" id="confirm_password" placeholder="Nhập lại mật khẩu"name="newpass2" required>
                    </div>
                
                
                <button type="submit" class="btn btn-primary">Cập Nhật</button>
                
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
          });
        </script>   

    </body>
</html>
