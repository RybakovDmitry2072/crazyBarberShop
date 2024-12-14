package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем сессию
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Удаляем атрибуты сессии
            session.removeAttribute("user");
            // Инвалидируем сессию
            session.invalidate();
        }

        // Перенаправляем пользователя на страницу входа или главную страницу
        response.sendRedirect(request.getContextPath());
    }
}
