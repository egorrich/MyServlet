package by.myservlet.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


/**
*
 * Create on 23.02.17.
 *
 * @author egor

*/
public class AppInitializer implements WebApplicationInitializer{

    private static final Logger log = LoggerFactory.getLogger(AppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("AppInitializer loading");
        XmlWebApplicationContext ctx = new XmlWebApplicationContext();
        ctx.setConfigLocation("/WEB-INF/spring.xml");
        servletContext.addListener(new ContextLoaderListener(ctx));
    }
}

