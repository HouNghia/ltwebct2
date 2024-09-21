package vn.iostar.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.models.User;
import vn.iostar.services.UserService;
import vn.iostar.services.imp.UserServiceImpl;
import vn.iostar.ultis.Constant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Phương thức GET xử lý yêu cầu ban đầu và kiểm tra session/cookie
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); 
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/home"); 
            return;
        }
        // Kiểm tra cookie lưu username nếu có
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = req.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/home"); // Chuyển hướng đến trang chính
                    return;
                }
            }
        }
        // Nếu không có session hay cookie, chuyển hướng đến trang đăng nhập
        req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
    }

    // Phương thức POST xử lý đăng nhập
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Lấy thông tin đăng nhập từ form
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Xử lý kiểm tra Remember Me
        boolean isRememberMe = false;
        String remember = req.getParameter("remember");
        if ("on".equals(remember)) {
            isRememberMe = true;
        }

        // Kiểm tra trường hợp thông tin đăng nhập rỗng
        if (username.isEmpty() || password.isEmpty()) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
            return;
        }

        // Gọi phương thức login từ UserService
        UserService service = new UserServiceImpl();
        User user = service.login(username, password);

        if (user != null) {
            // Đăng nhập thành công
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);

            // Xử lý Remember Me nếu được chọn
            if (isRememberMe) {
                saveRememberMe(resp, username);
            }

            // Chuyển hướng đến trang chính
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            if (service.findByUserName(username) == null) {
                req.setAttribute("alert", "Tên đăng nhập không tồn tại");
            } else {
                req.setAttribute("alert", "Mật khẩu không đúng");
            }
            req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
        }
    }

    // Lưu thông tin Remember Me dưới dạng cookie
    private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(24 * 60 * 60); 
        response.addCookie(cookie);
    }
}
