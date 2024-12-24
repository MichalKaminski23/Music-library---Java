package mk.musiclibraryweb.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import mk.musiclibraryweb.models.DataSource;

/**
 * Servlet responsible for deleting a song from the music library. This servlet
 * processes requests to delete a song by its ID. After the deletion, the user
 * is redirected to the songs list. The servlet expects the song ID as a request
 * parameter, deletes the song from the data source, and then redirects the user
 * to the page displaying the list of songs.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
@WebServlet("/songDelete")
public class SongDeleteServlet extends HttpServlet {

    /**
     * Processes the HTTP request for deleting a song from the music library. It
     * retrieves the song ID from the request parameter, deletes the song, and
     * redirects the user to the songs list page.
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

        String stringID = request.getParameter("id");
        int songID = Integer.parseInt(stringID);

        ServletContext context = request.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("DataSource");
        dataSource.delete(songID);

        response.sendRedirect(request.getContextPath() + "/songs");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP GET method for deleting a song. Delegates the request
     * processing to the processRequest method.
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
     * Handles the HTTP POST method for deleting a song. Delegates the request
     * processing to the processRequest method.
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
        return "Servlet for deleting a song from the music library";
    }
    // </editor-fold>
}
