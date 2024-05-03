<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nhập mã OTP</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #f5f5f5;
    }
    .otp-container {
        text-align: center;
        border: 1px solid #ccc;
        border-radius: 5px;
        padding: 8PX;
    }
    .otp-input {
        width: 40px;
        height: 40px;
        font-size: 18px;
        text-align: center;
        margin: 0 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .otp-input:focus {
        outline: none;
        border-color: #007bff;
    }
    .submit-btn {
        margin-top: 20px;
        padding: 10px 20px;
        font-size: 16px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .message {
        margin-top: 20px;
        font-size: 16px;
        color: #007bff;
    }
</style>
</head>
<body>

<div class="otp-container">
    <h2>Nhập mã OTP</h2>
    <form id="otpForm">
        <div> 
            <input type="text" class="otp-input" maxlength="1" required>
            <input type="text" class="otp-input" maxlength="1" required>
            <input type="text" class="otp-input" maxlength="1" required>
            <input type="text" class="otp-input" maxlength="1" required>
            <input type="text" class="otp-input" maxlength="1" required>
            <input type="text" class="otp-input" maxlength="1" required>
        </div>
        <button type="submit" class="submit-btn">Xác nhận</button>
    </form>
    <div id="message" class="message"></div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
    $('.otp-input').keyup(function() {
        var $this = $(this);
        if ($this.val().length === 1) {
            $this.next('.otp-input').focus(); // Di chuyển tới ô nhập tiếp theo khi đã nhập đủ 1 ký tự
        }
    });

    $('#otpForm').submit(function(e) {
        e.preventDefault(); // Ngăn chặn form gửi đi mặc định

        var otp = '';
        $('.otp-input').each(function() {
            otp += $(this).val(); // Lấy giá trị từng ô nhập và ghép thành mã OTP
        });

        // Gửi yêu cầu API để kiểm tra mã OTP
        $.ajax({
            url: 'checkOtp.jsp', // Đường dẫn tới trang xử lý kiểm tra mã OTP
            method: 'POST',
            data: { otp: otp },
            success: function(response) {
                // Hiển thị kết quả từ API
                $('#message').text(response);
            },
            error: function(xhr, status, error) {
                console.error(error); // Hiển thị lỗi nếu có
            }
        });
    });
});
</script>

</body>
</html>
