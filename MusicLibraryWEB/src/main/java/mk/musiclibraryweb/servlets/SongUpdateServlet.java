package mk.musiclibraryweb.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import mk.musiclibraryweb.models.SingletonModel;
import mk.musiclibraryweb.models.WrongInputException;

@WebServlet("/songUpdate")
public class SongUpdateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            int songID = Integer.parseInt(request.getParameter("songID"));
            String songTitle = request.getParameter("songTitle");
            String authorName = request.getParameter("authorName");
            String authorSurname = request.getParameter("authorSurname");
            String songAlbum = request.getParameter("songAlbum");
            String songRelease = request.getParameter("songRelease");
            String songTime = request.getParameter("songTime");

            SingletonModel.getInstance().updateSong(songID, songTitle, authorName, authorSurname, songAlbum, songRelease, songTime);
            response.sendRedirect(request.getContextPath() + "/songs");
        } catch (WrongInputException ex) {
            out.println(ex.toString());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
