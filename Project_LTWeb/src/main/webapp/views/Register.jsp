<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản mới</title>
</head>
<body>
    <div class="container">
        <h2 class="my-4">Tạo tài khoản mới</h2>

        <!-- Hiển thị thông báo lỗi nếu có -->
        <c:if test="${alert != null}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/register" method="post">
            
            <!-- Nhập Họ và tên -->
            <div class="form-group">
                <label for="fullname">Họ và tên</label>
                <input type="text" placeholder="Họ và tên" name="fullname" class="form-control" required>
            </div>

            <!-- Nhập Email -->
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" placeholder="Email" name="email" class="form-control" required>
            </div>

            <!-- Nhập Tài khoản (username) -->
            <div class="form-group">
                <label for="username">Tài khoản</label>
                <input type="text" placeholder="Tài khoản" name="username" class="form-control" required>
            </div>

            <!-- Nhập Mật khẩu -->
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" placeholder="Mật khẩu" name="password" class="form-control" required>
            </div>

            <!-- Nhập Số điện thoại -->
            <div class="form-group">
                <label for="phone">Số điện thoại</label>
                <input type="text" placeholder="Số điện thoại" name="phone" class="form-control" required>
            </div>

            <!-- Nút Đăng ký -->
            <button type="submit" class="btn btn-primary">Đăng ký</button>
        </form>
    </div>
    
</body>
</html>
