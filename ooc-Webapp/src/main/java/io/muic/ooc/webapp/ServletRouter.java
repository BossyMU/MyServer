/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp;

import io.muic.ooc.webapp.service.MySQLService;
import io.muic.ooc.webapp.servlet.*;
import io.muic.ooc.webapp.service.SecurityService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

/**
 *
 * @author gigadot
 */
public class ServletRouter {
    
    private SecurityService securityService;

    private MySQLService mySQLService = new MySQLService();

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void init(Context ctx) {
        initHome(ctx);
        initLogin(ctx);
        initDelete(ctx);
        initAdd(ctx);
        initEdit(ctx);
        initLogout(ctx);
    }

    private void initHome(Context ctx) {
        HomeServlet homeServlet = new HomeServlet();
        homeServlet.setSecurityManager(securityService);
        Tomcat.addServlet(ctx, "HomeServlet", homeServlet);
        ctx.addServletMapping("/index.jsp", "HomeServlet");
    }

    private void initLogin(Context ctx) {
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.setSecurityService(securityService);
        Tomcat.addServlet(ctx, "LoginServlet", loginServlet);
        ctx.addServletMapping("/login", "LoginServlet");
    }

    private void initDelete(Context ctx) {
        DeleteServlet deleteServlet = new DeleteServlet();
        deleteServlet.setMySQLService(mySQLService);
        Tomcat.addServlet(ctx, "DeleteServlet", deleteServlet);
        ctx.addServletMapping("/delete", "DeleteServlet");
    }

    private void initLogout(Context ctx) {
        LogoutServlet logoutServlet = new LogoutServlet();
        logoutServlet.setSecurityService(securityService);
        Tomcat.addServlet(ctx, "LogoutServlet", logoutServlet);
        ctx.addServletMapping("/logout", "LogoutServlet");
    }

    private void initAdd(Context ctx) {
        AddServlet addServlet = new AddServlet();
        addServlet.setMySQLService(mySQLService);
        Tomcat.addServlet(ctx, "AddServlet", addServlet);
        ctx.addServletMapping("/add", "AddServlet");
    }

    private void initEdit(Context ctx) {
        EditServlet editServlet = new EditServlet();
        editServlet.setMySQLService(mySQLService);
        Tomcat.addServlet(ctx, "EditServlet", editServlet);
        ctx.addServletMapping("/edit", "EditServlet");
    }
}
