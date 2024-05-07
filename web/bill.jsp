<%-- 
    Document   : bill.jsp
    Created on : May 6, 2024, 8:18:55 PM
    Author     : lvt-195
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thanh toán tiền điện sinh hoạt của hộ cá nhân</title>
        <link rel="stylesheet" href="css/menu.css">
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .hidden-column {
                display: none;
            }
        </style>
    </head>
    <body>
<!--        <div class="menu">
            <div class="tacvu">
                 EVNHANOI
            </div>
            <div class="tacvu">
                 <button class="button" name="back" onclick="history.back()" style="border: none;" > Trang Chủ </button>
            </div>
        </div>-->
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

        <div class="container" style="padding-top: 120px">
            <h1 class="text-center mb-4">Thanh toán tiền điện sinh hoạt của hộ cá nhân</h1>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Mã hoá đơn</th>
                            <th scope="col">Kỳ hoá đơn</th>
                            <th scope="col">Số điện tiêu thụ</th>
                            <th scope="col">Hạn thanh toán</th>
                            <th scope="col">Trạng thái thanh toán</th>
                            <th scope="col">Hành động</th>
                        </tr>
                    </thead>
                    <tbody id="billTableBody">
                        <!-- Dữ liệu hoá đơn sẽ được tạo ra bằng JavaScript -->
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Bootstrap JS và jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script>
            let menu = "menu.jsp";

            function parseDate(dateString) {
                const parts = dateString.split('/');
                if (parts.length === 3) {
                    const day = parseInt(parts[0], 10);
                    const month = parseInt(parts[1], 10) - 1;
                    const year = parseInt(parts[2], 10);
                    return new Date(year, month, day);
                }
                return null;
            }

            async function fetchElectricityData() {
                try {
                    // Hiển thị thông báo chờ đợi
                    document.getElementById('billTableBody').innerHTML = '<tr><td colspan="7" class="text-center">Đang tải dữ liệu...</td></tr>';

                    const response = await fetch('/BaiCoNgoc/getHoaDon');
                    
                    const rawData = await response.text();
                    const parsedData = JSON.parse(rawData);
                    
                    // Kiểm tra xem thuộc tính `vaitro` có tồn tại trong dữ liệu không
                    let role = "null";

                    // Kiểm tra và lấy giá trị `vaitro` từ tất cả các hóa đơn
//                    const vaitroList = parsedData.map(item => item.vaitro);
                    
                    // Nếu có ít nhất một hóa đơn có `vaitro` là "admin", gán giá trị cho biến `menu`
//                    if (vaitroList.includes("admin")) {
//                        menu = "menuadmin.jsp";
//                    } else if (vaitroList.includes("user")) {
//                        menu = "menuuser.jsp";
//                    }
                    
                    
                    let tableContent = '';

                    if (parsedData.length === 0) {
                        // Nếu không có dữ liệu, hiển thị thông báo
                        tableContent = `
                <tr>
                    <td colspan="7" class="text-center">Chưa có hoá đơn</td>
                </tr>
            `;
                    } else {
                        parsedData.forEach((item, index) => {
                            
                            
                            const stt = index + 1; // Số thứ tự bắt đầu từ 1

                            // Mặc định là không có màu
                            let rowClass = '';
                            const currentDate = new Date();
                            currentDate.setHours(0, 0, 0, 0);
                            const paymentDueDate = parseDate(item.payment_due_date);

                            if (item.is_paid) {
                                rowClass = 'table-success'; // Thêm lớp CSS 'table-success' để hiển thị hóa đơn đã thanh toán bằng màu xanh lá cây
                            } else if (paymentDueDate < currentDate) {
                                rowClass = 'table-danger'; // Thêm lớp CSS 'table-danger' để hiển thị hóa đơn quá hạn bằng màu đỏ
                            } else {
                                rowClass = 'table-warning'; // Thêm lớp CSS 'table-warning' để hiển thị hóa đơn chưa đến hạn bằng màu vàng
                            }
                            let id_bi=item.id_bill;
                            //Phai lam sao ha ban=))
                            let tr = `
                                <tr class="${rowClass}">
                                    <td><p>stt</p></td>
                                    <td>${item.id_bill}</td>
                                    <td>${item.period}</td>
                                    <td>${item.meter_number}</td>
                                    <td>${item.payment_due_date}</td>
                                    <td>${item.is_paid ? 'Đã thanh toán' : 'Chưa thanh toán'}</td>
                                    <td><a href="billDetail.html?id=${item.id_bill}" class="btn btn-primary">Chi tiết</a></td>
                                </tr>
                            `;
                            // no dang k do duoc data vao chuoi tr kia 
                            // minh chuyen ve text duoc k nhe

//                            tableContent += `
//                                <tr class="${rowClass}">
//                                    <td><p>stt</p></td>
//                                    <td>${item.id_bill}</td>
//                                    <td>${item.period}</td>
//                                    <td>${item.meter_number}</td>
//                                    <td>${item.payment_due_date}</td>
//                                    <td>${item.is_paid ? 'Đã thanh toán' : 'Chưa thanh toán'}</td>
//                                    <td><a href="billDetail.html?id=${item.id_bill}" class="btn btn-primary">Chi tiết</a></td>
//                                </tr>
//                            `;
                        });
                        
                        let arr = parsedData.map((item, index) => {
                            let rowClass = '';
                            const currentDate = new Date();
                            currentDate.setHours(0, 0, 0, 0);
                            const paymentDueDate = parseDate(item.payment_due_date);

                            if (item.is_paid) {
                                rowClass = 'table-success'; // Thêm lớp CSS 'table-success' để hiển thị hóa đơn đã thanh toán bằng màu xanh lá cây
                            } else if (paymentDueDate < currentDate) {
                                rowClass = 'table-danger'; // Thêm lớp CSS 'table-danger' để hiển thị hóa đơn quá hạn bằng màu đỏ
                            } else {
                                rowClass = 'table-warning'; // Thêm lớp CSS 'table-warning' để hiển thị hóa đơn chưa đến hạn bằng màu vàng
                            }
                            
                            let stringHTML = '<tr class=' + rowClass + '>';
                            stringHTML += '<td><p>' + (index + 1) + '</p></td>'; // Số thứ tự (index + 1)
                            stringHTML += '<td>' + item.id_bill + '</td>'; // ID hóa đơn
                            stringHTML += '<td>' + item.period + '</td>'; // Kỳ
                            stringHTML += '<td>' + item.meter_number + '</td>'; // Số đồng hồ đo
                            stringHTML += '<td>' + item.payment_due_date + '</td>'; // Ngày đáo hạn
                            stringHTML += '<td>' + (item.is_paid ? 'Đã thanh toán' : 'Chưa thanh toán') + '</td>'; // Trạng thái thanh toán
                            stringHTML += '<td><a href="billDetail.html?id=' + item.id_bill + '" class="btn btn-primary">Chi tiết</a></td>'; // Liên kết đến trang chi tiết hóa đơn
                            stringHTML += '</tr>';
                            return stringHTML;
                            
//                            noi chuoi xong nho join arr vao nhe
                        });
                        let contentHTML = arr.join("");
                        console.log(contentHTML);
                        document.getElementById('billTableBody').innerHTML = contentHTML;
                        
                    }
                   
                    // Cập nhật nội dung bảng
                    

                    
                    

                } catch (error) {
                    console.error("Error fetching data:", error);
                    // Hiển thị thông báo lỗi cho người dùng
                    document.getElementById('billTableBody').innerHTML = '<tr><td colspan="7" class="text-center">Lỗi khi lấy dữ liệu từ máy chủ.<br> Vui lòng thử lại sau.</td></tr>';
                }

                // Lấy nội dung menu từ file JSP
                $(document).ready(function () {
                    $.get(menu, function (data) {
                        $("#menuPlaceholder").html(data);
                    });
                });
            }

// Gọi hàm fetchElectricityData khi trang web được tải
            fetchElectricityData();

        </script>
    </body>
</html>

