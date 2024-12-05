package mk.musiclibraryweb.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import mk.musiclibraryweb.models.SingletonModel;
import mk.musiclibraryweb.models.Song;

@WebServlet("/songs")
public class SongServlet extends HttpServlet {

    @Override
    public void init() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String songTitle = request.getParameter("songTitle");

        boolean showAll = songTitle == null || songTitle.length() == 0;

        PrintWriter out = response.getWriter();
        for (Song song : SingletonModel.getInstance().getAllSongs()) {
            if (showAll || song.getSongTitle().contains(songTitle)) {
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"songTitle\" name=\"songTitle\" placeholder=\"Song title\" value=\"" + song.getSongTitle() + "\"/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"authorName\" name=\"authorName\" placeholder=\"Author name\" value=\"" + song.getAuthorName() + "\"/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"authorSurname\" name=\"authorSurname\" placeholder=\"Author surname\" value=\"" + song.getAuthorSurname() + "\"/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"songAlbum\" name=\"songAlbum\" placeholder=\"Song album\" value=\"" + song.getSongAlbum() + "\"/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"songRelease\" name=\"songRelease\" placeholder=\"Release date\" value=\"" + song.getSongRelease() + "\"/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"songTime\" name=\"songTime\" placeholder=\"Song time\" value=\"" + song.getSongTime() + "\"/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"button\" value=\"Update\" onclick=\"updateSong(" + song.getSongID() + ",'songTitle','authorName','authorSurname','songAlbum','songRelease','songTime','tableSong','errorInfo');\" />");
                out.println("</td>");                                                                                                                                                                   
                out.println("<td>");
                out.println("<input type=\"button\" value=\"Delete\" onclick=\"deleteSong(" + song.getSongID() + ", 'tableSong','errorInfo');\" />");
                out.println("</td>");
                out.println("</tr>");
            }
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
