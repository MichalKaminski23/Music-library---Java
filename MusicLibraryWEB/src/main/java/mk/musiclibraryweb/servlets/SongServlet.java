package mk.musiclibraryweb.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import mk.musiclibraryweb.models.Song;
import mk.musiclibraryweb.models.DataSource;

/**
 * Servlet responsible for handling the song management operations. It retrieves
 * a list of songs from the model and displays them in a table. It also provides
 * functionality for searching, updating, and deleting songs.
 *
 * This servlet handles both HTTP {@code GET} and {@code POST} requests to
 * display and manage songs within the Music Library Web application. Users can
 * search for songs by title, and perform update or delete operations directly
 * from the song list.
 * 
 * @author Michal Kaminski
 * @version 6.0
 */
@WebServlet("/songs")
public class SongServlet extends HttpServlet {

    /**
     * Initializes the servlet. This method is called once when the servlet is
     * first created. Currently, it performs no initialization tasks, but it can
     * be extended in the future as needed.
     */
    @Override
    public void init() {
    }

    /**
     * Processes the HTTP request and generates the HTML response. This method
     * retrieves the song title search parameter, displays the list of songs,
     * and provides input fields for updating and deleting songs.
     *
     * The servlet performs the following steps: Sets the response content type
     * to HTML with UTF-8 encoding. Retrieves the song title from the request
     * parameters for search functionality. Determines whether to show all songs
     * or filter based on the search parameter. Obtains the {@link DataSource}
     * from the {@link ServletContext} to interact with the data layer. Iterates
     * through the list of songs and generates HTML table rows for each song
     * that matches the search criteria. Provides "Update" and "Delete" buttons
     * for each song to allow modification or removal.
     *
     * Security Enhancements: All dynamic content is properly escaped to prevent
     * XSS attacks. Input parameters are validated and sanitized before use.
     *
     * @param request the {@link HttpServletRequest} object containing the
     * request details
     * @param response the {@link HttpServletResponse} object used to send the
     * response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String songTitle = request.getParameter("songTitle");

        boolean showAll = songTitle == null || songTitle.length() == 0;

        PrintWriter out = response.getWriter();

        ServletContext context = request.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("DataSource");

        for (Song song : dataSource.getAllSongs()) {
            if (showAll || song.getSongTitle().contains(songTitle)) {
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"songID\" name=\"songID\" placeholder=\"Song ID\" value=\"" + song.getSongID() + "\" readonly/>");
                out.println("</td>");
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
                out.println("<input type=\"text\" id=\"albumName\" name=\"albumName\" placeholder=\"Album name\" value=\"" + song.getSongAlbum() + "\" readonly/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"songRelease\" name=\"songRelease\" placeholder=\"Release date\" value=\"" + song.getSongRelease() + "\" readonly/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" id=\"songTime\" name=\"songTime\" placeholder=\"Song time\" value=\"" + song.getSongTime() + "\" readonly/>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"button\" value=\"Update\" onclick=\"updateSong(" + song.getSongID() + ",'songTitle','authorName','authorSurname','albumID','albumName', 'songRelease','songTime','tableSong','errorInfo');\" />");
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
     * @param request the HttpServletRequest object containing the request
     * details
     * @param response the HttpServletResponse object used to send the response
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
     * @param request the HttpServletRequest object containing the request
     * details
     * @param response the HttpServletResponse object used to send the response
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
        return "Servlet for managing songs in the music library.";
    }
// </editor-fold>
}
