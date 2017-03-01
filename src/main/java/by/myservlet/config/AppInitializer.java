package by.myservlet.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

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
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcherServlet", DispatcherServlet.class);
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
        dispatcherServlet.setInitParameter("contextConfigLocation", "/WEB-INF/spring.xml");

        servletContext.setInitParameter("contextConfigLocation", "/WEB-INF/spring.xml");
        servletContext.addListener(new ContextLoaderListener());
        servletContext.addFilter("hibernateFilter", OpenSessionInViewFilter.class)
                .addMappingForUrlPatterns(null, false, "/*");
    }
}

