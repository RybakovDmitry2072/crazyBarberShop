package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.models.Image;
import org.example.crazybarbershop.repository.iml.ImageRepositoryImpl;
import org.example.crazybarbershop.repository.interfaces.ImageRepository;
import org.example.crazybarbershop.util.DataBaseConnectionProvider;
import org.example.crazybarbershop.util.JSPHelper;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(JSPHelper.getPath("index")).forward(req,resp);

    }

}

