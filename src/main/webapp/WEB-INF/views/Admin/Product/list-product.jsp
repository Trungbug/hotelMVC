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
    <title>Quản lý khách sạn</title>
    <%@ include file="/WEB-INF/views/inc/links.jsp" %>

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</head>

<script>
$(document).ready(function() {
    const API_URL = 'http://localhost:8080/DemoMVC/list-product';

    $.getJSON(API_URL, function(data) {
        $.each(data, function(index, product) {
            // Chuyển đổi ngày tháng từ số epoch time sang chuỗi "YYYY-MM-DD"
            var dateAdd = new Date(product.dateAdd);
            var expired = new Date(product.expired);

            // Lấy các thành phần ngày, tháng, năm
            var dateAddFormatted = formatDate(dateAdd);
            var expiredFormatted = formatDate(expired);

            var row = '<tr>' +
                        '<td>' + product.productId + '</td>' +
                        '<td>' + product.name + '</td>' +
                        '<td>' + product.producType.name + '</td>' +
                        '<td>' + product.unit.name + '</td>' +
                        '<td>' + dateAddFormatted + '</td>' +
                        '<td>' + expiredFormatted + '</td>' +
                        '<td>' + product.price + '</td>' +
                        '<td>' + product.capitalPrice + '</td>' +
                        '<td>' + product.status + '</td>' +
                        '<td>' + product.description + '</td>' +
                        '<td><button class="btn btn-sm rounded-pill btn-danger delete-btn" data-id="' + product.productId + '">Xóa</button></td>' +
                        '<td><a href="/DemoMVC/sua-san-pham/productId=' + product.productId + '" class="btn btn-sm rounded-pill btn-primary">Sửa</a></td>' +
                      '</tr>';
            $('#products').append(row);
        });

        // Thêm sự kiện click vào nút xóa
        $('.delete-btn').click(function() {
            var unitId = $(this).data('id');
            confirmDelete(unitId);
        });
    });
});

// Hàm chuyển đổi ngày tháng sang định dạng "YYYY-MM-DD"
function formatDate(date) {
    var year = date.getFullYear();
    var month = ('0' + (date.getMonth() + 1)).slice(-2);
    var day = ('0' + date.getDate()).slice(-2);
    return year + '-' + month + '-' + day;
}

function confirmDelete(productId) {
    var result = confirm('Cảnh báo: Bạn có chắc muốn xóa sản phẩm này?');
    if (result === true) {
        // Gửi yêu cầu DELETE đến API
        $.ajax({
            url: 'http://localhost:8080/DemoMVC/xoa-san-pham/productId=' + productId,
            type: 'DELETE',
            success: function(result) {
                // Nếu xóa thành công, làm mới trang
                location.reload();
            },
            error: function(xhr, status, error) {
                alert('Đã xảy ra lỗi khi xóa sản phẩm!');
            }
        });
    }
}
</script>

<body class="bg-white">
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <div class="container-fluid" id="main-content">
        <div class="row">
            <div class="col-lg-10 ms-auto p-4 overflow-hidden">
                <h3 class="mb-4">Quản lý sản phẩm</h3>

                <div class="card border-0 shadow-sm mb-4">
                    <div class="card-body">

                        <div class="text-end mb-4">
                            <a href="http://localhost:8080/DemoMVC/them-san-pham" class="btn btn-success rounded-pill shadow-none btn-sm">
                                Thêm mới
                            </a>
                        </div>


                        <div class="table-responsive-md" style="height: 450px; overflow-y: scroll; overflow-x:scroll;">

                            <table class="table bg-white table-hover border">
                                <thead class="sticky-top">
                                    <tr class="bg-dark text-light">
                                    <th>ID</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Loại sản phẩm</th>
                                    <th>Đơn vị tính</th>
                                    <th>Ngày nhập</th>
                                    <th>HSD</th>
                                    <th>Giá bán</th>
                                    <th>Giá nhập</th>
                                    <th>Trạng thái</th>
                                    <th>Mô tả</th>
                                    <th></th>
                                    <th></th>
                                    </tr>
                                </thead>
                                <tbody id="products"></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/views/inc/scripts.jsp" %>
</body>
</html>
