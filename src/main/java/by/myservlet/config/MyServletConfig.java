package by.myservlet.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Create on  on 23.02.2017.

 @author Egor
 */

@Configuration
@ComponentScan("by.myservlet")
public class MyServletConfig extends WebMvcConfigurerAdapter{


}
