<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
</head>
<body>
    <h2>Đăng nhập</h2>

    <!-- Hiển thị thông báo đăng ký thành công nếu có -->
    <c:if test="${not empty sessionScope.successMessage}">
        <div class="alert alert-success">
            ${sessionScope.successMessage}
        </div>
        <!-- Xóa thông báo sau khi hiển thị -->
        <c:remove var="successMessage" scope="session" />
    </c:if>

    <!-- Hiển thị thông báo lỗi đăng nhập nếu có -->
    <c:if test="${alert != null}">
        <h3 class="alert alert-danger">${alert}</h3>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <section>
            <div class="input-group">
                <span class="input-group-addon">
                    <i class="fa fa-user"></i>
                </span>
                <input type="text" placeholder="Tài khoản" name="username" class="form-control" required>
            </div>
        </section>

        <section>
            <div class="input-group">
                <span class="input-group-addon">
                    <i class="fa fa-lock"></i>
                </span>
                <input type="password" placeholder="Mật khẩu" name="password" class="form-control" required>
            </div>
        </section>

        <button type="submit" class="btn btn-primary">Đăng nhập</button>
    </form>

    <!-- Thêm liên kết đến trang đăng ký -->
    <div>
        <p>Bạn chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a></p>
    </div>

    <!-- Thêm liên kết đến trang quên mật khẩu -->
    <div>
        <p>Quên mật khẩu? <a href="${pageContext.request.contextPath}/forgotPassword">Lấy lại mật khẩu</a></p>
    </div>

</body>
</html>
