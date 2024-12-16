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
import mk.musiclibraryweb.models.WrongInputException;
import mk.musiclibraryweb.models.DataSource;

/**
 * Servlet responsible for handling the insertion of new songs into the music
 * library. It retrieves the song details from the request and adds the new song
 * to the model. If there is an error in the input, it returns a bad request
 * response.
 *
 * @author Michal Kaminski
 * @version 5.0
 */
@WebServlet("/songInsert")
public class SongInsertServlet extends HttpServlet {

    /**
     * Processes the HTTP request to insert a new song into the music library.
     * It reads the song details from the request parameters, and attempts to
     * add the song. If the input is valid, the song is added, and the user is
     * redirected to the songs list. If there is an error in the input, an
     * exception is thrown and the error message is returned.
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
    String authorName = request.getParameter("authorName");
    String authorSurname = request.getParameter("authorSurname");
    String songAlbum = request.getParameter("songAlbum");
    String songRelease = request.getParameter("songRelease");
    String songTime = request.getParameter("songTime");

    ServletContext context = request.getServletContext();
    DataSource dataSource = (DataSource) context.getAttribute("DataSource");

    try (PrintWriter out = response.getWriter()) {
        if (isNullOrEmpty(songTitle) || isNullOrEmpty(authorName) || isNullOrEmpty(authorSurname) ||
            isNullOrEmpty(songAlbum) || isNullOrEmpty(songRelease) || isNullOrEmpty(songTime)) {
            
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h2>Invalid input: All fields are required!</h2>");
            return;
        }

        try {
            Song song = new Song(songTitle, authorName, authorSurname, songAlbum, songRelease, songTime);
            dataSource.persistObject(song);

            response.sendRedirect(request.getContextPath() + "/songs");
        } catch (WrongInputException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h2>Error: " + ex.getMessage() + "</h2>");
        }
    }
}

// Pomocnicza metoda do walidacji pustych danych
private boolean isNullOrEmpty(String str) {
    return str == null || str.trim().isEmpty();
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
        return "Servlet for inserting new songs into the music library.";
    }
    // </editor-fold>

}
