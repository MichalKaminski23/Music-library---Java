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
 * Servlet responsible for updating song details in the music library. This
 * servlet processes requests to update an existing song's information. If the
 * input data is valid, the song is updated, and the user is redirected to the
 * songs list. If there is an error, a message is displayed with a bad request
 * status.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
@WebServlet("/songUpdate")
public class SongUpdateServlet extends HttpServlet {

    /**
     * Processes the HTTP request for updating song details. It retrieves the
     * song details from the request parameters, validates the input, and
     * updates the song in the DataSource instance.
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
        PrintWriter out = response.getWriter();

        ServletContext context = request.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("DataSource");

        try {
            String stringID = request.getParameter("songID");
            String songTitle = request.getParameter("songTitle");
            String authorName = request.getParameter("authorName");
            String authorSurname = request.getParameter("authorSurname");
            String songAlbum = request.getParameter("songAlbum");
            String songRelease = request.getParameter("songRelease");
            String songTime = request.getParameter("songTime");

            if (songTitle == null || songTitle.isBlank() || authorName == null || authorName.isBlank()
                    || authorSurname == null || authorSurname.isBlank() || songAlbum == null || songAlbum.isBlank()
                    || songRelease == null || songRelease.isBlank() || songTime == null || songTime.isBlank()) {
                throw new WrongInputException("All fields must be filled!");
            }

            int songID = Integer.parseInt(stringID);

            if (!songTime.matches("\\d+") || Integer.parseInt(songTime) <= 0) {
                throw new WrongInputException("Song time must be a valid number!");
            }

            if (!songRelease.matches("(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(19|20)\\d{2}")) {
                throw new WrongInputException("Song release date must be in the format dd.MM.yyyy!");
            }

            if (dataSource.isSongTitleTaken(songTitle)) {
                throw new WrongInputException("A song with the same title already exists!");
            }

            Song existingSong = dataSource.findById(songID);
            existingSong.setSongTitle(songTitle);
            existingSong.setAuthorName(authorName);
            existingSong.setAuthorSurname(authorSurname);
            existingSong.setSongAlbum(songAlbum);
            existingSong.setSongRelease(songRelease);
            existingSong.setSongTime(songTime);
            dataSource.update(existingSong);

            response.sendRedirect(request.getContextPath() + "/songs");
        } catch (WrongInputException ex) {
            out.println("<html><body><h3>Error:</h3><p>" + ex.getMessage() + "</p></body></html>");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP GET method for updating song details. Delegates the
     * request processing to the processRequest method.
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
     * Handles the HTTP POST method for updating song details. Delegates the
     * request processing to the processRequest method.
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
        return "Servlet for updating song details in the music library";
    }
    // </editor-fold>
}
