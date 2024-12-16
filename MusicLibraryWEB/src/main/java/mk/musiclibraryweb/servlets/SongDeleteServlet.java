package mk.musiclibraryweb.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import mk.musiclibraryweb.models.DataBaseSource;
import mk.musiclibraryweb.models.Song;
import mk.musiclibraryweb.models.SongList;
import mk.musiclibraryweb.models.DataSource;

/**
 * Servlet responsible for deleting a song from the music library.
 * This servlet processes requests to delete a song by its ID.
 * After the deletion, the user is redirected to the songs list.
 * 
 * @author Michal Kaminski
 * @version 5.0
 */
@WebServlet("/songDelete")
public class SongDeleteServlet extends HttpServlet {
private final ArrayList<Song> allSongs = new ArrayList<Song>();
    /**
     * Processes the HTTP request for deleting a song from the music library.
     * It retrieves the song ID from the request parameter, deletes the song,
     * and redirects the user to the songs list page.
     * 
     * @param request the HttpServletRequest object containing the request details
     * @param response the HttpServletResponse object used to send the response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String idArg = request.getParameter("id");

        int id = Integer.parseInt(idArg);
        ServletContext context = request.getServletContext();
        DataSource dataSource = (DataSource)context.getAttribute("DataSource");
        dataSource.delete(id);
        
        response.sendRedirect(request.getContextPath() + "/songs");
    }

    /**
     * Handles the HTTP GET method for deleting a song.
     * Delegates the request processing to the processRequest method.
     * 
     * @param request the HttpServletRequest object containing the request details
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
     * Handles the HTTP POST method for deleting a song.
     * Delegates the request processing to the processRequest method.
     * 
     * @param request the HttpServletRequest object containing the request details
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
        return "Servlet for deleting a song from the music library";
    }
}
