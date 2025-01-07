package mk.musiclibraryweb.servlets;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import mk.musiclibraryweb.models.Album;
import mk.musiclibraryweb.models.DataSource;

/**
 * Servlet implementation class AlbumsServlet.
 *
 * This servlet handles the retrieval and display of all albums in the Music
 * Library Web application. It processes HTTP {@code GET} and {@code POST}
 * requests to fetch album data from the data source and renders it as HTML
 * table rows. Each album entry includes its ID, name, associated songs, and a
 * button to delete the album.
 *
 * The servlet interacts with the {@link DataSource} interface to fetch album
 * and song data, ensuring that the presentation layer remains decoupled from
 * the business logic.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
@WebServlet("/albums")
public class AlbumsServlet extends HttpServlet {

    /**
     * Initializes the servlet. This method is called once when the servlet is
     * first loaded into memory.
     *
     * If there are any initialization parameters or resources to be set up,
     * they can be handled here.
     */
    @Override
    public void init() {
    }

    /**
     * Processes requests for both HTTP {@code GET} and {@code POST} methods.
     *
     * This method retrieves all albums from the data source, fetches the
     * associated song titles for each album, and generates HTML table rows
     * displaying the album information. Each row includes input fields for
     * album ID, album name, and songs, as well as a "Delete" button to remove
     * the album.
     *
     * Note: Proper error handling and input validation should be implemented to
     * manage scenarios where data retrieval fails or returns unexpected
     * results.
     *
     * @param request the {@link HttpServletRequest} object that contains the
     * request the client made of the servlet
     * @param response the {@link HttpServletResponse} object that contains the
     * response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        ServletContext context = request.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("DataSource");

        for (Album album : dataSource.getAllAlbums()) {
            List<String> songTitles = dataSource.findSongTitlesByAlbumID(album.getAlbumID());

            String songsString = "";
            if (songTitles != null && !songTitles.isEmpty()) {
                songsString = String.join(", ", songTitles);
            }

            out.println("<tr>");
            out.println("<td>");
            out.println("<input type=\"text\" id=\"albumID\" name=\"albumID\" placeholder=\"Album ID\" value=\"" + album.getAlbumID() + "\" readonly/>");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" id=\"albumName\" name=\"albumName\" placeholder=\"Album name\" value=\"" + album.getAlbumName() + "\" readonly/>");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"text\" id=\"songs\" name=\"songs\" placeholder=\"songs\" value=\"" + songsString + "\" readonly/>");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type=\"button\" value=\"Delete\" onclick=\"deleteAlbum(" + album.getAlbumID() + ", 'tableAlbum','errorInfo');\" />");
            out.println("</td>");
            out.println("</tr>");

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
        return "Servlet for displaying and managing albums in the Music Library Web application.";
    }// </editor-fold>
}