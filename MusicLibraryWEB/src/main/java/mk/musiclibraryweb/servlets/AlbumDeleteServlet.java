package mk.musiclibraryweb.servlets;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.musiclibraryweb.models.DataSource;

/**
 * Servlet implementation class AlbumDeleteServlet.
 *
 * This servlet handles the deletion of albums from the Music Library Web
 * application. It processes HTTP {@code GET} and {@code POST} requests to
 * delete an album based on the provided album ID. Upon successful deletion, it
 * redirects the user to the albums overview page. If an error occurs during the
 * deletion process, appropriate error handling should be implemented to inform
 * the user.
 *
 * The servlet interacts with the {@link DataSource} interface to perform the
 * deletion operation, ensuring that the business logic remains decoupled from
 * the presentation layer.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
@WebServlet("/albumDelete")
public class AlbumDeleteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP {@code GET} and {@code POST} methods.
     *
     * This method retrieves the album ID from the request parameters, invokes
     * the {@link DataSource#deleteAlbum(int)} method to delete the specified
     * album, and redirects the user to the albums overview page upon successful
     * deletion.
     *
     * Note: Proper error handling should be implemented to manage scenarios
     * where the album ID is missing, invalid, or the deletion fails.
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

        String stringID = request.getParameter("id");
        int albumID = Integer.parseInt(stringID);

        ServletContext context = request.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("DataSource");

        dataSource.deleteAlbum(albumID);

        response.sendRedirect(request.getContextPath() + "/albums");
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
        return "Servlet for deleting albums from the Music Library Web application.";
    }// </editor-fold>

}
