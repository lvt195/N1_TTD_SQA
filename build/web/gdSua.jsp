<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Model.*,DAL.*,java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa cập nhật</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
<%
List<ElectricBoard> lelec=(List<ElectricBoard>)session.getAttribute("lelec");
int i= Integer.parseInt(request.getParameter("i"));
ElectricBoard elec=(new ElectricBoardDAO()).getElectricBoardHienTai(lelec.get(i).getMeter_code());
%>
</head>
<body>  
    <jsp:include page="/menuadmin.jsp"/>
    <h2 style="text-align: center; padding-top: 150px;margin-bottom: 30px;color: rgb(65, 144, 197)">SỬA CẬP NHẬT</h2>
    <div style="width: 35%;border: 1px solid #000;border-radius: 5px;padding: 30px;margin: 0 auto;">
	<form action="${pageContext.request.contextPath}/capnhatsodien" method="POST" accept-charset="UTF-8">
		<input type="hidden" name="i" value="<%=i%>">
		<input type="hidden" name="id" value="<%= elec.getId()%>">
		<input type="hidden" name="id_elecregistration" value="<%= elec.geteRegistration().getId()%>">
                Ông(Bà): 
                <input style="width: 100%;border: 1px solid #000;border-radius: 4px;padding: 10px;margin: 5px;margin-bottom: 15px" type="text" name="fullName" value="<%= elec.geteRegistration().getFullName()%>" readonly>
		Mã công tơ:
                <input style="width: 100%;border: 1px solid #000;border-radius: 4px;padding: 10px;margin: 5px;margin-bottom: 15px" type="text" name="meter_code" value="<%= elec.getMeter_code()%>" readonly>
		Địa chỉ của công tơ: 
                <input style="width: 100%;border: 1px solid #000;border-radius: 4px;padding: 10px;margin: 5px;margin-bottom: 15px" type="text" name="meter_address" value="<%= elec.getMeter_address() %>" readonly>
		Số công tơ kì hóa đơn trước (<%=lelec.get(i).getPeroid() %>):
                <input style="width: 100%;border: 1px solid #000;border-radius: 4px;padding: 10px;margin: 5px;margin-bottom: 15px" type="text" name="sodiencu" value="<%= lelec.get(i).getMeter_number() %>" readonly>
		Kì hóa đơn hiện tại (<%= elec.getPeroid() %>): 
                <input style="width: 100%;border: 1px solid #000;border-radius: 4px;padding: 10px;margin: 5px;margin-bottom: 15px" type="text" name="Meter_number_old" value="<%= elec.getMeter_number() %>" readonly>
		Thời gian bắt đầu lấy số công tơ điện:
                <input style="width: 100%;border: 1px solid #000;border-radius: 4px;padding: 10px;margin: 5px;margin-bottom: 15px" type="text" name="time_start" value="<%= lelec.get(i).getTime_start()%>" readonly>
		Thời gian cập nhật gần nhất:
                <input style="width: 100%;border: 1px solid #000;border-radius: 4px;padding: 10px;margin: 5px;margin-bottom: 15px" type="text" name="time_update" value="<%= elec.getTime_update()%>" readonly>
		Thời gian sửa gần nhất:
                <input style="width: 100%;border: 1px solid #000;border-radius: 4px;padding: 10px;margin: 5px;margin-bottom: 15px" type="text" name="time_edit" value="<%= elec.getTime_edit()%>" readonly>
		<input type="hidden" name="period" value="<%= elec.getPeroid() %>">
		Meter_number_new: 
                <input style="width: 100%;border: 1px solid #000;border-radius: 4px;padding: 10px;margin: 5px;margin-bottom: 15px" type="number" name="Meter_number_new" id="Meter_number_new" min="<%=lelec.get(i).getMeter_number()%>" max="9999999999" placeholder="Nhập số công tơ mới" required>
		<input type="submit" style="width: 100%;border: 1px solid #000;border-radius: 4px; padding: 4px;margin: 5px;margin-bottom: 15px;background: linear-gradient(10deg, #1c01ff, #ff357f); color: #fff" value="   Sửa   ">
	</form>
	<p><button type="button" name="back" style="width: 100%;border: 1px solid #000;border-radius: 4px; padding: 4px;margin:5px; background-color: #007bff; color: #fff" onclick="history.back()">  Quay lại </button></p>
    </div>
    <br/>
</body>
</html>
