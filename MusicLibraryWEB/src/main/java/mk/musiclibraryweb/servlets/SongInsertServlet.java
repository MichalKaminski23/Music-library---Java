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
 * response. The servlet expects song details (ID, title, author, etc.) as
 * request parameters, validates the input, and if all checks pass, inserts the
 * new song into the library. If an error occurs during the input or validation,
 * a message is returned to the user, and the status code is set to BAD_REQUEST.
 *
 * @author Michal Kaminski
 * @version 6.0
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

            if (stringID == null || stringID.isBlank()
                    || songTitle == null || songTitle.isBlank()
                    || authorName == null || authorName.isBlank()
                    || authorSurname == null || authorSurname.isBlank()
                    || songAlbum == null || songAlbum.isBlank()
                    || songRelease == null || songRelease.isBlank()
                    || songTime == null || songTime.isBlank()) {
                throw new WrongInputException("All fields must be filled!");
            }

            if (!stringID.matches("\\d+") || Integer.parseInt(stringID) <= 0) {
                throw new WrongInputException("Song ID must be a positive integer!");
            }

            int songID = Integer.parseInt(stringID);

            if (!songTime.matches("\\d+") || Integer.parseInt(songTime) <= 0) {
                throw new WrongInputException("Song time must be a valid positive number!");
            }

            if (!songRelease.matches("(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(19|20)\\d{2}")) {
                throw new WrongInputException("Song release date must be in the format dd.MM.yyyy!");
            }

            if (dataSource.isSongTitleTaken(songTitle)) {
                throw new WrongInputException("A song with the same title already exists!");
            }

            if (dataSource.isSongIDTaken(songID)) {
                throw new WrongInputException("A song with the same ID already exists!");
            }

            Song song = new Song(songID, songTitle, authorName, authorSurname, songAlbum, songRelease, songTime);
            dataSource.insert(song);

            response.sendRedirect(request.getContextPath() + "/songs");
        } catch (WrongInputException ex) {
            out.println("<html><body><h3>Error:</h3><p>" + ex.getMessage() + "</p></body></html>");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
        return "Servlet for inserting new songs into the music library.";
    }
    // </editor-fold>
}
