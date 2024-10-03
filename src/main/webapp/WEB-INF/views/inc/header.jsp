<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<%@page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý khách sạn</title>
     <link rel="stylesheet" href="<c:url value='/resource/css/styles.css'/>">
</head>
<body class="bg-white">

    <div class="container-fluid bg-dark text-light p-3 d-flex align-items-center justify-content-between shadow-sm sticky-top">
        <h3 class="mb-0">ADMIN</h3>
        <a href="" class="btn btn-light btn-sm">Đăng Xuất</a>
    </div>

    <div class="col-lg-2 bg-dark border-top border-3 border-secondary" id="dashboard-menu">
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container-fluid flex-lg-column align-items-stretch">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#adminDropdown" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse flex-column align-items-stretch mt-2" id="adminDropdown">
                    <ul class="nav nav-pills flex-column">
                        <li class="nav-item">
                            <c:url var="urlDashBoard" value="/dashboard" />
                            <a class="nav-link text-white" href="${urlDashBoard}">Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <c:url var="urlListRoom" value="/danh-sach-phong" />
                            <a class="nav-link text-white" href="${urlListRoom}">Quản lý phòng</a>
                        </li>
                         <li class="nav-item">
                            <c:url var="urlListRoomType" value="/danh-sach-loai-phong" />
                            <a class="nav-link text-white" href="${urlListRoomType}">Quản lý loại phòng</a>
                         </li>
                         <li class="nav-item">
                             <c:url var="urlListFacilitiesType" value="/danh-sach-loai-co-so-vat-chat" />
                             <a class="nav-link text-white" href="${urlListFacilitiesType}">Quản lý loại cơ sở vật chất</a>
                         </li>
                         <li class="nav-item">
                              <c:url var="urlListFacilities" value="/danh-sach-co-so-vat-chat" />
                              <a class="nav-link text-white" href="${urlListFacilities}">Quản lý cơ sở vật chất</a>
                         </li>
                         <li class="nav-item">
                               <c:url var="urlUnit" value="/danh-sach-loai-don-vi" />
                               <a class="nav-link text-white" href="${urlUnit}">Danh sách loại đơn vị</a>
                         </li>
                         <li class="nav-item">
                               <c:url var="urlProductType" value="/danh-sach-loai-san-pham" />
                               <a class="nav-link text-white" href="${urlProductType}">Danh sách loại sản phẩm</a>
                         </li>
                         <li class="nav-item">
                               <c:url var="urlProduct" value="/danh-sach-san-pham" />
                               <a class="nav-link text-white" href="${urlProduct}">Danh sách sản phẩm</a>
                         </li>
                         <li class="nav-item">
                                <c:url var="urlBooking" value="/danh-sach-dat-phong" />
                                <a class="nav-link text-white" href="${urlBooking}">Danh sách đặt phòng</a>
                         </li>
                         <li class="nav-item">
                               <c:url var="urlRoomBook" value="/danh-sach-phong-co-nguoi-o" />
                               <a class="nav-link text-white" href="${urlRoomBook}">Danh sách phòng đang có người ở</a>
                         </li>
                          <li class="nav-item">
                              <c:url var="urlRoomBill" value="/danh-sach-hoa-don-phong" />
                              <a class="nav-link text-white" href="${urlRoomBill}">Danh sách hóa đơn phòng</a>
                          </li>
                        <!-- Các mục khác được chuyển đổi tương tự -->
                        <!-- ... -->
                        <li class="nav-item">
                            <a class="nav-link text-white" href="">Cài đặt</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>

    <!-- Include các tệp script JSP hoặc các thẻ script JS nếu cần -->

</body>
</html>