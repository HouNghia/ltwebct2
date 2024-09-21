package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.services.UserService;
import vn.iostar.services.imp.UserServiceImpl;
import vn.iostar.ultis.Constant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect(req.getContextPath() + "/admin");
            return;
        }

        // Kiểm tra cookie để xem người dùng đã đăng nhập chưa
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = req.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/admin");
                    return;
                }
            }
        }

        // Chuyển đến trang đăng ký nếu chưa đăng nhập
        req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Lấy các tham số từ form đăng ký
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        // Lấy ngày hiện tại
        long millis = System.currentTimeMillis();
        java.sql.Date createDate = new java.sql.Date(millis);

        UserService service = new UserServiceImpl();
        String alertMsg = "";

        // Kiểm tra xem email hoặc tên tài khoản đã tồn tại chưa
        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
            return;
        }
        if (service.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
            return;
        }

        // Đăng ký tài khoản
        boolean isSuccess = service.register(email, password, username, fullname, phone, createDate);

        if (isSuccess) {
            HttpSession session = req.getSession();
            session.setAttribute("successMessage", "Đăng ký thành công! Vui lòng đăng nhập.");
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "Lỗi hệ thống!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
        }
    }


}
