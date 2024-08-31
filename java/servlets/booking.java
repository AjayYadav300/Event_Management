/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.UserDAOImpl;
import Databases.DBConnect;
import classes.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static servlets.Registration.id;

/**
 *
 * @author arpit
 */
public class booking extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     Connection con=null;
    PreparedStatement prt=null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet booking</title>");            
            out.println("</head>");
            out.println("<body>");
                HttpSession session=request.getSession();
               User u=(User)session.getAttribute("userobj");
               int id=u.getId();
               String email=u.getEmail();
               String pname=request.getParameter("pname");
               String pno=request.getParameter("pno");
               String pex=request.getParameter("pex");
               String pc=request.getParameter("pc");
               out.println(id);
               
               Class.forName("com.mysql.jdbc.Driver").newInstance();
          //out.println("2Hello user");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydemo","root","root");
         prt=con.prepareStatement("insert into booking(id,name,card,edate,cvv,email) values(?,?,?,?,?,?)");
          //out.println("3Hello user");
         prt.setInt(1,id);
         prt.setString(2,pname);
         prt.setString(3,pno);
         prt.setString(4,pex);
         prt.setString(5,pc);
         prt.setString(6,email);
          //out.println("4Hello user");
         int i=prt.executeUpdate();
          //out.println("5Hello user");
         if(i==1)
         {
             id++;
             session.setAttribute("succMsg","Booking and Payment Successsfully..");
             response.sendRedirect("myjsp/payment.jsp");
             
         }
         else{
             id--;
             session.setAttribute("failedMsg","Something wrong on server..");
             response.sendRedirect("myjsp/payment.jsp");
             
                     
           }
            out.println("</body>");
            out.println("</html>");
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(booking.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(booking.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(booking.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(booking.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
