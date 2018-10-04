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

/**
 *
 * @author hp
 */
public class verification extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestResponse(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    protected void requestResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        try {
            String identity = request.getParameter("identity");
            String values = request.getParameter("values");
            String receiver = request.getParameter("receiver");
            writer.println("Identification Number :" + identity);
            writer.println("Password :" + values);
            writer.println("Token :" + receiver);
            Cookie[] cookie = request.getCookies();
            String tokens = cookie[0].getValue();
            writer.println("token is :"+tokens);
            if (receiver.equals(tokens)) {
                writer.println("Token has been verfied");
            } else {
                writer.println("Token verification has Failed");
            }
        }finally {
            writer.close();
        }
    }

}
