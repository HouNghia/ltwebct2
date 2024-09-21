package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.services.UserService;
import vn.iostar.services.imp.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/forgetpassword")
public class ForgetPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/ForgetPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Lấy thông tin từ form
        String username = req.getParameter("username");
        String newPassword = req.getParameter("password");

        String alertMsg = "";

        // Kiểm tra dữ liệu có bị rỗng không
        if (username.isEmpty() || newPassword.isEmpty()) {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/ForgetPassword.jsp").forward(req, resp);
            return;
        }

        // Gọi dịch vụ kiểm tra tài khoản
        UserService service = new UserServiceImpl();

        // Kiểm tra xem tài khoản có tồn tại không
        if (!service.checkExistUsername(username)) {
            alertMsg = "Tài khoản không tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/ForgetPassword.jsp").forward(req, resp);
            return;
        }

        // Thay đổi mật khẩu mới cho tài khoản
        boolean isPasswordChanged = service.changePassword(username, newPassword);
        if (isPasswordChanged) {
            // Chuyển hướng đến trang đăng nhập sau khi đổi mật khẩu thành công
            req.getSession().setAttribute("successMessage", "Mật khẩu đã được cập nhật thành công!");
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "Có lỗi xảy ra. Vui lòng thử lại.";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/ForgetPassword.jsp").forward(req, resp);
        }
    }
}
