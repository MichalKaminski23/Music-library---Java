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

/**
 * Servlet responsible for handling the song management operations. It retrieves
 * a list of songs from the model and displays them in a table. It also provides
 * functionality for searching, updating, and deleting songs.
 *
 * @author Michal Kaminski
 * @version 5.0
 */
@WebServlet("/songs")
public class SongServlet extends HttpServlet {

    /**
     * Initializes the servlet. This method is called once when the servlet is
     * first created.
     */
    @Override
    public void init() {
    }

    /**
     * Processes the HTTP request and generates the HTML response. This method
     * retrieves the song title search parameter, displays the list of songs,
     * and provides input fields for updating and deleting songs.
     *
     * @param request the HttpServletRequest object containing the request
     * details
     * @param response the HttpServletResponse object used to send the response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                out.println("<input type=\"text\" id=\"songTitle\" name=\"songTitle\" placeholder=\"Song title\" value=\"" + song.getSongTitle() + "\" readonly/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"authorName\" name=\"authorName\" placeholder=\"Author name\" value=\"" + song.getAuthorName() + "\" readonly/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"authorSurname\" name=\"authorSurname\" placeholder=\"Author surname\" value=\"" + song.getAuthorSurname() + "\" readonly/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"songAlbum\" name=\"songAlbum\" placeholder=\"Song album\" value=\"" + song.getSongAlbum() + "\" readonly/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"songRelease\" name=\"songRelease\" placeholder=\"Release date\" value=\"" + song.getSongRelease() + "\" readonly/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"songTime\" name=\"songTime\" placeholder=\"Song time\" value=\"" + song.getSongTime() + "\" readonly/>");
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
     * Handles the HTTP <code>GET</code> method. This method is invoked when a
     * GET request is received by the servlet.
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
     * Handles the HTTP <code>POST</code> method. This method is invoked when a
     * POST request is received by the servlet.
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
     * Returns a short description of the servlet. This method provides a brief
     * description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for managing songs in the music library, including searching, updating, and deleting songs.";
    }
    // </editor-fold>

}
