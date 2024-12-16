package mk.musiclibraryweb.servlets;

import jakarta.servlet.http.HttpServlet;
import mk.musiclibraryweb.models.DataBaseSource;
import mk.musiclibraryweb.models.DataSource;

public class InitializationServlet extends HttpServlet { 
    
    @Override
    public void init() {
        
        var context = getServletContext();        
        DataSource dataSource = (DataSource)context.getAttribute("DataSource");
        if(dataSource == null) {
            //context.setAttribute("DataSource", new People());
            context.setAttribute("DataSource", new DataBaseSource());
        }
        
    }   
    
}
