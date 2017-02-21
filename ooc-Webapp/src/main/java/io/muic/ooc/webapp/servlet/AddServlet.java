/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.MySQLService;
import io.muic.ooc.webapp.service.SecurityService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author gigadot
 */
public class AddServlet extends HttpServlet {

    private MySQLService mySQLService;
    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean authorized = securityService.isAuthorized(request);
            if (authorized) {
                // do MVC in here
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/add.jsp");
                rd.include(request, response);
            } else {
                response.sendRedirect("/login");
            }
        }catch (Exception e){

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("Add "+username);
        System.out.println("Add "+password);
        if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
            String error = "Username or password is missing.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/add.jsp");
            rd.include(request, response);
        }else{
            try {
                if(mySQLService.findOne(username)){
                    String error = "This Username is already exist.";
                    request.setAttribute("error", error);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/add.jsp");
                    rd.include(request, response);
                }else {
                    String digest = DigestUtils.md5Hex(password);
                    mySQLService.addDataBase(username, digest);
                    response.sendRedirect("/");
                }
            } catch (Exception e){

            }

        }
    }

    public void setMySQLService(MySQLService mySQLService) {
        this.mySQLService = mySQLService;
    }
    public void setSecurityService(SecurityService securityService) {this.securityService = securityService;}
}
