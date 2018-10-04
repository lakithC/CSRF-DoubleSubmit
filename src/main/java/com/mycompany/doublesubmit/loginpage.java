/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.doublesubmit;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author hp
 */
public class loginpage extends HttpServlet {

    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private static String tokens(){
        String tokenone = null;
        byte[] bytes = new byte[16];
        try{
            SecureRandom random = SecureRandom.getInstanceStrong();
            random.nextBytes(bytes);
            tokenone = new String(Base64.getEncoder().encode(bytes));
        }
        catch(NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }
        return tokenone;
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
    
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            Cookie[] cookie = request.getCookies();
            cookie[0].setPath("/");
            
            if (username.equals("admin") && password.equals("admin")) {
                HttpSession http = request.getSession();
                http.setAttribute("username", username);
                String tokentwo = tokens();
                System.out.println("Token :"+tokentwo);
                Cookie cook = new Cookie(cookie[0].getValue(), tokentwo);
                cookie[0].setValue(tokentwo);
                cookie[0].setPath("/");
                response.addCookie(cook);
                http.setAttribute("token", tokentwo);
                response.sendRedirect("view.jsp");
            } else {
                writer.println("Invalid credentials, username or password. Therefore, use admin for username and password");
            }
        }finally{
            writer.close();
        }
    
    }

}
