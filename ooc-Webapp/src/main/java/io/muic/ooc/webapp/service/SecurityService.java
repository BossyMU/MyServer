/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.service;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gigadot
 */
public class SecurityService {
    
//    private Map<String, String> userCredentials = new HashMap<String, String>() {{
//        put("admin", "123456");
//        put("muic", "1111");
//        put("tw","twtw");
//    }};

    public boolean isAuthorized(HttpServletRequest request) throws Exception{
        String username = (String) request.getSession()
                .getAttribute("username");
        // do checking
        System.out.println(username);
        MySQLService sql = new MySQLService();
        return (username != null && sql.findOne(username));
    }
    
    public boolean authenticate(String username, String password, HttpServletRequest request){
//        String passwordInDB = userCredentials.get(username);
//        boolean isMatched = StringUtils.equals(password, passwordInDB);
        try {
            MySQLService sql = new MySQLService();
            String digest = DigestUtils.md5Hex(password);
            boolean isMatched = sql.checkMatch(username, digest);
            System.out.println(isMatched);
            if (isMatched) {
                request.getSession().setAttribute("username", username);
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            System.out.println(e);

        }
        return false;
    }
    
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
    
}

