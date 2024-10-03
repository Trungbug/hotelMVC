<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Base64" %>
<%@ page isELIgnored="false" %>
<%@ page import="org.apache.tomcat.util.http.fileupload.*" %>
<%@page session="false" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm mới sản phẩm</title>
    <%@ include file="/WEB-INF/views/inc/links.jsp" %>

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    <!-- Axios for sending HTTP requests -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

</head>
<script>
$(document).ready(function() {
   const urlParams = new URLSearchParams(window.location.search);
   const getUrlId = urlParams.get('id');
   console.log("URL ID vai lon luon:", getUrlId);
    // Nếu có giá trị từ URL, gán vào các input
    if (getUrlId) {
        const API_URL = 'http://localhost:8080/DemoMVC/cosovatchat/' + getUrlId;
        $.getJSON(API_URL, function(data) {
        console.log("Data received:", data);
            if (data) {
                $("#name").val(data.name);
                $("#price").val(data.price);
                $("#quantity").val(data.quantity);
                // Chuyển đổi timestamp thành định dạng ngày tháng
                var dateBuy = new Date(data.date_buy);
                var formattedDate = dateBuy.getFullYear() + "-" + (dateBuy.getMonth() + 1).toString().padStart(2, '0') + "-" + dateBuy.getDate().toString().padStart(2, '0');
                $("#date-buy").val(formattedDate);
                $("#status").val(data.status);
                $("#manufacturer").val(data.manufacturer);
                var typeId = data.facilitiesTypeId;

                const facilitiesTypesUrl = 'http://localhost:8080/DemoMVC/find-facilities-type/id=' + typeId;
                $.getJSON(facilitiesTypesUrl, function(facilitiesTypeData) {
                    $("#facilitiesTypeId").empty();
                    $("#facilitiesTypeId").append($('<option></option>').val(facilitiesTypeData.id).text(facilitiesTypeData.facilitiesTypeName));
                });
            }
        });
    }
});
</script>


<body class="bg-white">
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <div class="container-fluid" id="main-content">
        <div class="row">
            <div class="col-lg-10 ms-auto p-4 overflow-hidden">
                <h3 class="mb-4">Thêm cơ sở vật chất</h3>

                <form id="addFacilitiesForm">
                   <div class="form-group">
                       <label for="name">Tên cơ sở vật chất:</label>
                       <input type="text" class="form-control" id="name" name="name">
                   </div>
                   <div class="form-group">
                       <label for="price">Giá cơ sở vật chất:</label>
                       <input type="number" class="form-control" id="price" name="price">
                   </div>
                   <div class="form-group">
                       <label for="quantity">Số lượng:</label>
                       <input type="number" class="form-control" id="quantity" name="quantity">
                   </div>
                   <div class="form-group">
                       <label for="date-buy">Ngày mua:</label>
                       <input type="date" class="form-control" id="date-buy" name="date-buy">
                   </div>
                   <div class="form-group">
                       <label for="status">Trạng thái:</label>
                       <select class="form-control" id="status" name="status">
                           <option value="Hoạt động">Hoạt động</option>
                           <option value="Bảo trì">Bảo trì</option>
                           <option value="Hỏng">Hỏng</option>
                       </select>
                   </div>
                   <div class="form-group">
                       <label for="manufacturer">Hãng sản xuất:</label>
                       <input type="text" class="form-control" id="manufacturer" name="manufacturer">
                   </div>
                   <div class="form-group">
                       <label for="facilitiesTypeId">Loại cơ sở vật chất:</label>
                       <select class="form-control" id="facilitiesTypeId" name="facilitiesTypeId">
                           <!-- Dữ liệu loại cơ sở vật chất sẽ được load sau -->
                       </select>
                   </div>
                   <button type="button" class="btn btn-primary" onclick="addFacilities()">Xác nhận sửa</button>
                   <button type="button" class="btn btn-sm rounded-pill btn-danger" onclick="history.go(-1)">Hủy</button>
                </form>
            </div>
        </div>
    </div>

    <script>
       function addFacilities() {
           var id = "<%= request.getParameter("id") %>";
           var name = $("#name").val();
           var price = parseFloat($("#price").val()); // Parse price to a number
           var quantity = $("#quantity").val();
           var total = price * quantity;
           var dateBuy = $("#date-buy").val();
           var status = $("#status").val();
           var manufacturer = $("#manufacturer").val();
           var facilitiesTypeId = $("#facilitiesTypeId").val();

           const facilitiesTypeJson = 'http://localhost:8080/DemoMVC/find-facilities-type/id=' + facilitiesTypeId;
           $.getJSON(facilitiesTypeJson, function(facilitiesTypeData) {
               var facilitiesTypeGet = {
                   "id": facilitiesTypeData.id,
                   "facilitiesTypeName":  facilitiesTypeData.facilitiesTypeName,
               };

               // Tạo đối tượng product với thông tin đã lấy được
               var facilitiesData = {
                   "name": name,
                   "price": price,
                   "quantity": quantity,
                   "totalPrice": total,
                   "date_buy": dateBuy,
                   "status": status,
                   "manufacturer": manufacturer,
                   "facilitiesType": facilitiesTypeGet
               };

               // Gửi yêu cầu POST để thêm sản phẩm mới
               axios.put('http://localhost:8080/DemoMVC/update/'+ id, facilitiesData)
                   .then(function (response) {
                       console.log(response.data);
                       alert("Sửa thành công!");
                       window.location.href = 'http://localhost:8080/DemoMVC/danh-sach-co-so-vat-chat';
                   })
                   .catch(function (error) {
                       console.log(error);
                       alert("Lỗi xảy ra khi sửa! Vui lòng kiểm tra lại thông tin.");
                   });
           });
       }
    </script>

    <%@ include file="/WEB-INF/views/inc/scripts.jsp" %>
</body>
</html>
