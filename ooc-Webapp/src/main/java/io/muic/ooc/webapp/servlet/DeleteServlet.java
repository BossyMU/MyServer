/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.MySQLService;
import io.muic.ooc.webapp.service.SecurityService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gigadot
 */
public class DeleteServlet extends HttpServlet {

    private MySQLService mySQLService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
        String username = (String) request.getSession()
                .getAttribute("username");
        System.out.println("USER"+username);
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("Delete");
        String usernameFromSession = (String) request.getSession()
                .getAttribute("username");
        System.out.println("YEAHHHHHHHH" + username);
        try {
            if (!usernameFromSession.equals(username)) {
                mySQLService.deleteDataBase(username);
            }
        } catch (Exception e) {}
        response.sendRedirect("/");
    }

    public void setMySQLService(MySQLService mySQLService) {
        this.mySQLService = mySQLService;
    }
}
