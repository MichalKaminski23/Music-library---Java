package mk.musiclibraryweb.servlets;

import jakarta.servlet.http.HttpServlet;
import mk.musiclibraryweb.models.DataBaseSource;
import mk.musiclibraryweb.models.DataSource;

/**
 * Servlet responsible for initializing the DataSource in the servlet context.
 *
 * This servlet checks if the DataSource is already available in the servlet
 * context. If it is not, it creates a new instance of {@link DataBaseSource}
 * and sets it as an attribute in the servlet context for later use by other
 * servlets.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
public class InitializationServlet extends HttpServlet {

    /**
     * Initializes the servlet and ensures that a {@link DataSource} is
     * available in the context.
     *
     * This method is called when the servlet is first loaded. It checks whether
     * a {@link DataSource} has already been set in the servlet context. If it
     * hasn't, it creates a new {@link DataBaseSource} instance and sets it as
     * an attribute in the context for other components to use.
     */
    @Override
    public void init() {
        var context = getServletContext();

        DataSource dataSource = (DataSource) context.getAttribute("DataSource");

        if (dataSource == null) {
            context.setAttribute("DataSource", new DataBaseSource());
        }
    }
}
