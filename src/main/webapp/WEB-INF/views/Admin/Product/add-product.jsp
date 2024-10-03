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

<body class="bg-white">
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <div class="container-fluid" id="main-content">
        <div class="row">
            <div class="col-lg-10 ms-auto p-4 overflow-hidden">
                <h3 class="mb-4">Thêm mới sản phẩm</h3>

                <form id="addProductForm">
                    <div class="form-group">
                        <label for="name">Tên sản phẩm:</label>
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                    <div class="form-group">
                        <label for="productType">Loại sản phẩm:</label>
                        <select class="form-control" id="productType" name="productType">
                            <c:forEach items="${productTypes}" var="productType">
                                <option value="${productType.productTypeId}">${productType.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="unit">Đơn vị tính:</label>
                        <select class="form-control" id="unit" name="unit">
                            <c:forEach items="${units}" var="unit">
                                <option value="${unit.unitId}">${unit.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="dateAdd">Ngày thêm:</label>
                        <input type="date" class="form-control" id="dateAdd" name="dateAdd">
                    </div>
                    <div class="form-group">
                        <label for="expired">Ngày hết hạn:</label>
                        <input type="date" class="form-control" id="expired" name="expired">
                    </div>
                    <div class="form-group">
                        <label for="price">Giá bán:</label>
                        <input type="number" class="form-control" id="price" name="price">
                    </div>
                    <div class="form-group">
                        <label for="capitalPrice">Giá vốn:</label>
                        <input type="number" class="form-control" id="capitalPrice" name="capitalPrice">
                    </div>
                    <div class="form-group">
                        <label for="status">Trạng thái:</label>
                        <select class="form-control" id="status" name="status">
                            <option value="Bán">Bán</option>
                            <option value="Ngừng bán">Ngừng bán</option>
                            <!-- Thêm các trạng thái khác nếu cần -->
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="description">Mô tả:</label>
                        <textarea class="form-control" id="description" name="description"></textarea>
                    </div>

                    <button type="button" class="btn btn-primary" onclick="addProduct()">Xác nhận thêm</button>
                    <button type="button" value="Hủy" class="btn btn-sm rounded-pill btn-danger" onclick="history.go(-1)">Hủy</button>
                </form>
            </div>
        </div>
    </div>

    <script>
        function addProduct() {
           var name = $("#name").val();
           var productTypeId = $("#productType").val();
           var unitId = $("#unit").val();
           var dateAdd = $("#dateAdd").val();
           var expired = $("#expired").val();
           var price = $("#price").val();
           var capitalPrice = $("#capitalPrice").val();
           var status = $("#status").val();
           var description = $("#description").val();

           // Lấy thông tin đơn vị từ API
           const unitAPI = 'http://localhost:8080/DemoMVC/find-unit/unitId=' + unitId;
           $.getJSON(unitAPI, function(unitData) {
               // Tạo đối tượng unit từ dữ liệu API
               var unit = {
                   "unitId": unitData.unitId,
                   "name": unitData.name,
                   "description": unitData.description
               };

               // Lấy thông tin loại sản phẩm từ API
               const productTypeAPI = 'http://localhost:8080/DemoMVC/find-product-type/productTypeId=' + productTypeId;
               $.getJSON(productTypeAPI, function(productTypeData) {
                   // Tạo đối tượng productType từ dữ liệu API
                   var productType = {
                       "productTypeId": productTypeData.productTypeId,
                       "name":  productTypeData.name,
                       "description": productTypeData.description
                   };

                   // Tạo đối tượng product với thông tin đã lấy được
                   var productData = {
                       "name": name,
                       "producType": productType,
                       "unit": unit,
                       "dateAdd": dateAdd,
                       "expired": expired,
                       "price": price,
                       "capitalPrice": capitalPrice,
                       "status": status,
                       "description": description
                   };

                   // Gửi yêu cầu POST để thêm sản phẩm mới
                   axios.post('http://localhost:8080/DemoMVC/add-product', productData)
                       .then(function (response) {
                           console.log(response.data);
                           alert("Thêm thành công!");
                           window.location.href = 'http://localhost:8080/DemoMVC/danh-sach-san-pham';
                       })
                       .catch(function (error) {
                           console.log(error);
                           alert("Lỗi xảy ra khi thêm!");
                       });
               });
           });
       }
    </script>

    <%@ include file="/WEB-INF/views/inc/scripts.jsp" %>
</body>
</html>
