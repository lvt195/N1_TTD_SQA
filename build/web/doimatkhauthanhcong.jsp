
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đổi mật khẩu</title>
    <style>
        /* Mã CSS */
        .container {
            text-align: center;
            margin: 250px auto;
            width: 500px;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        .btn-oke {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .btn-oke:hover {
            background-color: #0056b3;
        }

        .btn-oke:active {
            background-color: #003c8f;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Đổi mật khẩu thành công</h1>
        <button class="btn-oke">Oke</button>
    </div>

    <script>
        // Mã JavaScript
        const btnOke = document.querySelector('.btn-oke');

        btnOke.addEventListener('click', function() {
            // Chuyển hướng đến trang .jsp
            window.location.href = "doimatkhau.jsp";
        });
    </script>
</body>
</html>
