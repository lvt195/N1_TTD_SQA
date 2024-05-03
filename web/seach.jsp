<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seach</title>
<script>
    function validateForm() {
        var username = document.forms["seach"]["seach_by_username"].value;
        var meterCode = document.forms["seach"]["seach_by_meter_code"].value;
        var meterAddress = document.forms["seach"]["seach_by_meter_address"].value;

        if (username == "" && meterCode == "" && meterAddress == "") {
            alert("Vui lòng nhập ít nhất một trường để tìm kiếm.");
            return false;
        }
    }
</script>
<%
	String ten=request.getParameter("seach_by_username");
	String macongto=request.getParameter("seach_by_meter_code");
	String diachicongto=request.getParameter("seach_by_meter_address");
	
%>
</head>
<body>
<p >
    <form style="margin: 50px" name="seach" action="#" method="get" onsubmit="return validateForm()">
        <label>Nhập tên người đại diện để tìm kiếm:</label><br/>
        <input type="text" style="width: 100%;border: 1px solid #000;border-radius: 4px; padding: 4px;" name="seach_by_username" placeholder="Nhập mã tên người đại diện"><br/> 
        <label style="margin-top: 10px">Nhập mã công tơ để tìm kiếm:</label><br/>
        <input type="text" style="width: 100%;border: 1px solid #000;border-radius: 4px; padding: 4px;" name="seach_by_meter_code" placeholder="Nhập mã công tơ"><br/>
        <label style="margin-top: 10px">Nhập địa chỉ công tơ để tìm kiếm:</label><br/>
        <input type="text" style="width: 100%;border: 1px solid #000;border-radius: 4px; padding: 4px;" name="seach_by_meter_address" placeholder="nhập địa chỉ công tơ"><br/>
        <input type="submit" style="width: 100%;border: 1px solid #000;border-radius: 4px; padding: 4px; background-color: #007bff; color: #fff; margin-top: 10px" value="Tìm kiếm">
    </form>
    <% if(ten==""&&macongto==""&&diachicongto==""||ten==null &&macongto==null &&diachicongto== null){
    }else{%>
    <p>Tìm kiếm theo:
    <%if(ten!=""){ %>
    Tên:<%=ten %>
    <%} %>
    <%if(macongto!=""){ %>
    ------Mã công tơ:<%=macongto %>
    <%} %> 
    <%if(diachicongto!=""){ %>
    ------Địa chỉ công tơ:<%=diachicongto %>
    <%} %></p>
    <%}%>
</p>
</body>
</html>
