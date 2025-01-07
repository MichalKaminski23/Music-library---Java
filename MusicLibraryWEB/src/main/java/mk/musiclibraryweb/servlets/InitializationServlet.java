package mk.musiclibraryweb.servlets;

import jakarta.servlet.http.HttpServlet;
import mk.musiclibraryweb.models.DataBaseSource;
import mk.musiclibraryweb.models.DataSource;

/**
 * Servlet responsible for initializing the {@link DataSource} in the servlet
 * context.
 *
 * This servlet ensures that a {@link DataSource} instance is available in the
 * servlet context for use by other servlets within the Music Library Web
 * application. During initialization, it checks whether a {@link DataSource}
 * has already been set. If not, it creates a new instance of
 * {@link DataBaseSource} and stores it in the servlet context under the
 * attribute name {@code "DataSource"}.
 *
 * By centralizing the initialization of the {@link DataSource}, this servlet
 * promotes a consistent and efficient management of database connections and
 * operations across the application.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
public class InitializationServlet extends HttpServlet {

    /**
     * Initializes the servlet and ensures that a {@link DataSource} is
     * available in the context.
     *
     * This method is called once when the servlet is first loaded into memory.
     * It performs the following actions: Retrieves the to access
     * application-wide attributes. Checks if a {@link DataSource} is
     * already present in the context. If absent, creates a new
     * {@link DataBaseSource} instance and sets it as an attribute in the
     * context for other servlets to utilize.
     *
     * Proper initialization of the {@link DataSource} is crucial for ensuring
     * that all subsequent database operations performed by other servlets
     * function correctly.
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
