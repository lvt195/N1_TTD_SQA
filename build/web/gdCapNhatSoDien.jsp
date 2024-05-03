<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Model.*,DAL.*,java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật số điện</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
<% 
Calendar c=Calendar.getInstance();
int nY= c.get(Calendar.YEAR);
int nM=c.get(Calendar.MONTH);
String gd=nY+"-"+String.format("%02d", nM + 1);
String gd_cu=nY+"-"+String.format("%02d", nM );
%>
<%
	String ten=request.getParameter("seach_by_username");
	String macongto=request.getParameter("seach_by_meter_code");
	String diachicongto=request.getParameter("seach_by_meter_address");
	List<ElectricBoard> lelec;
	if(ten==null &&macongto==null &&diachicongto== null){
		lelec=(new ElectricBoardDAO()).getElectricBoardThangTruoc();
		session.setAttribute("lelec", lelec);
	}
	else{
		lelec=(new ElectricBoardDAO()).getElectricBoardThangTruocTheoTimKiem(ten, macongto, diachicongto);
		session.setAttribute("lelec", lelec);
	}
%>
<script>
        function checkUpdateDate(updateDate,i) {
            var currentDate = new Date();
            var updateDateTime = new Date(updateDate);
            updateDateTime.setDate(updateDateTime.getDate()+1);

            if (updateDateTime < currentDate) {
                alert("Hiện đã hết thời gian chỉnh sửa, vui lòng liên hệ với quản lý hệ thống để biết thêm chi tiết ^v^");
                return false; // Ngăn chặn việc gửi form nếu hết hạn cập nhật
            } else {
                // Tiếp tục gửi form nếu không hết hạn cập nhật
                window.location.href='gdSua.jsp?i='+i;
                return true;
            }
        }
</script>
</head>
<body>
<jsp:include page="/menuadmin.jsp"/>
<h2 style="text-align: center; padding-top: 150px;color: rgb(65, 144, 197)">TRANG CẬP NHẬT SỐ ĐIỆN KÌ HÓA ĐƠN <%=gd%> </h2>
<br><jsp:include page="/seach.jsp"/><br>
<%if(lelec.size()==0){ %>
    <div class="alert alert-danger text-center" role="alert"> Không tìm thấy dữ liệu </div>
<%} else{%>
<div style="margin: 50px">
    <table e style="width: 100%;border: 1px solid black; border-collapse: collapse;">
		<thead style="background-color:rgb(65, 144, 197); color: #fff; font-weight: bold;">
			<td style="border: 1px solid black; padding: 0 15px 0 15px;text-align: center;">STT</td>
			<td style="border: 1px solid black; padding: 0 15px 0 15px;text-align: center;">Tên đại diện</td>
			<td style="border: 1px solid black; padding: 0 15px 0 15px;text-align: center;">Mã công tơ</td>
			<td style="border: 1px solid black; padding: 0 15px 0 15px;text-align: center;">Địa chỉ công tơ</td>
			<td style="border: 1px solid black; padding: 0 15px 0 15px;text-align: center;">Số công tơ kì hóa đơn <%= gd_cu %>(kWh)</td>
			<td style="border: 1px solid black; padding: 0 15px 0 15px;text-align: center;">Số công tơ kì hóa đơn hiện tại <%= gd %>(kWh)</td>
			<td style="border: 1px solid black; padding: 0 15px 0 15px;text-align: center;">Tổng số điện đã dùng trong tháng này <%= gd %>(kWh)</td>
			<td style="border: 1px solid black; padding: 0 15px 0 15px;text-align: center;">Thời gian bắt đầu lấy số công tơ</td>
			<td style="border: 1px solid black; padding: 0 15px 0 15px;text-align: center;">Thời gian kết thúc lấy số công tơ</td>
			<td style="border: 1px solid black; padding: 0 15px 0 15px;text-align: center;">Hành động</td>				
		</thead>
		<%
		for (int i = 0; i < lelec.size(); i++) {
			ElectricBoard elec=(new ElectricBoardDAO()).getElectricBoardHienTai(lelec.get(i).getMeter_code());
		%>
		<tr>
			<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;"><%=(i + 1)%></td>
			<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;"><%=lelec.get(i).geteRegistration().getFullName()%></td>
			<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;"><%=lelec.get(i).getMeter_code()%></td>
			<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;"><%=lelec.get(i).getMeter_address()%></td>
			<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;"><%=lelec.get(i).getMeter_number()%></td>
			<%if(elec==null){ %>
			<form action="${pageContext.request.contextPath}/capnhatsodien" method="GET">
				<input type="hidden" name="i" value="<%=i%>">
				<input type="hidden" name="sodiencu" value="<%=lelec.get(i).getMeter_number()%>">
				<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;"><input style="border: 1px solid #000;border-radius: 4px; padding: 4px" type="number" name="new_number" id="newNumber<%= i %>" min="<%=lelec.get(i).getMeter_number()%>" max="9999999999" placeholder="Nhập số công tơ điện hiện tại" required></td>
				<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;">__________</td>
				<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;"><%=lelec.get(i).getTime_edit()%></td>
				<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;">__________</td>
				<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;"><input type="submit" style="border: 1px solid #000;border-radius: 4px; padding: 4px; background: linear-gradient(10deg, #1c01ff, #ff357f); color: #fff" value="Cập nhật"></td>
			</form>
			<%}else{ %>
			<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;"><%=elec.getMeter_number() %></td>
			<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;"><%= (elec.getMeter_number()-lelec.get(i).getMeter_number())%></td>
			<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;"><%=lelec.get(i).getTime_edit()%></td>
			<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;"><%=elec.getTime_edit()%></td>
			<td style="border: 1px solid black; padding: 10px 15px 10px 15px;text-align: center;">
			<button style="border: 1px solid #000;border-radius: 4px; padding: 4px; background: linear-gradient(10deg, #1c01ff, #ff357f); color: #fff" onclick="return checkUpdateDate('<%= elec.getTime_update()%>','<%=i%>');">Sửa</button>
			</td>
			<%} %>
		</tr>
		<%
		}
		%>
	</table>
	<%} %>
</div>
	<p style="text-align: right; margin:20px 50px 10px 10px"><button style="border: 1px solid #000;border-radius: 4px; padding: 4px; background-color: #007bff; color: #fff" type="button" name="back" onclick="history.back()">Quay lại</button></p>
</body>
</html>