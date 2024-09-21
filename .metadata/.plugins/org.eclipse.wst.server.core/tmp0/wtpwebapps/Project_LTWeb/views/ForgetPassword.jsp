<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu</title>
</head>
<body>
    <h2>Khôi phục mật khẩu</h2>

    <!-- Hiển thị thông báo lỗi nếu có -->
    <c:if test="${alert != null}">
        <div class="alert alert-danger">
            ${alert}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/forgetpassword" method="post">
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
                <input type="password" placeholder="Mật khẩu mới" name="password" class="form-control" required>
            </div>
        </section>

        <button type="submit" class="btn btn-primary">Cập nhật mật khẩu</button>
    </form>
</body>
</html>
