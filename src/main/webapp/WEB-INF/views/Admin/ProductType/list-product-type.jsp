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
    const API_URL = 'http://localhost:8080/DemoMVC/list-product-type';

    $.getJSON(API_URL, function(data) {
        $.each(data, function(index, productType) {
            var row = '<tr>' +
                        '<td>' + productType.productTypeId + '</td>' +
                        '<td>' + productType.name + '</td>' +
                        '<td>' + productType.description + '</td>' +
                        '<td><button class="btn btn-sm rounded-pill btn-danger delete-btn" data-id="' + productType.productTypeId + '">Xóa</button></td>' +
                        '<td><a href="/DemoMVC/sua-loai-san-pham/productTypeId=' + productType.productTypeId + '" class="btn btn-sm rounded-pill btn-primary">Sửa</a></td>' +
                      '</tr>';
            $('#productTypes').append(row);
        });

        // Thêm sự kiện click vào nút xóa
        $('.delete-btn').click(function() {
            var unitId = $(this).data('id');
            confirmDelete(unitId);
        });
    });
});

function confirmDelete(productTypeId) {
    var result = confirm('Cảnh báo: Bạn có chắc muốn xóa loại sản phẩm này?');
    if (result === true) {
        // Gửi yêu cầu DELETE đến API
        $.ajax({
            url: 'http://localhost:8080/DemoMVC/xoa-loai-san-pham/productTypeId=' + productTypeId,
            type: 'DELETE',
            success: function(result) {
                // Nếu xóa thành công, làm mới trang
                location.reload();
            },
            error: function(xhr, status, error) {
                alert('Đã xảy ra lỗi khi xóa loại sản phẩm!');
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
                <h3 class="mb-4">Quản lý loại sản phẩm</h3>

                <div class="card border-0 shadow-sm mb-4">
                    <div class="card-body">

                        <div class="text-end mb-4">
                            <a href="http://localhost:8080/DemoMVC/them-loai-san-pham" class="btn btn-success rounded-pill shadow-none btn-sm">
                                Thêm mới
                            </a>
                        </div>


                        <div class="table-responsive-md" style="height: 450px; overflow-y: scroll; overflow-x:scroll;">

                            <table class="table bg-white table-hover border">
                                <thead class="sticky-top">
                                    <tr class="bg-dark text-light">
                                    <th>ID</th>
                                    <th>Tên loại sản phẩm</th>
                                    <th>Mô tả</th>
                                    <th></th>
                                    <th></th>
                                    </tr>
                                </thead>
                                <tbody id="productTypes"></tbody>
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
