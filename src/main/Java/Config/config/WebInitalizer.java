package Config.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitalizer implements WebApplicationInitializer{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(Configuration.class);
        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic servelet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servelet.setLoadOnStartup(1);
        servelet.addMapping("/");
    }
}

//extends AbstractAnnotationConfigDispatcherServletInitializer
//public class WebInitalizer extends AbstractAnnotationConfigDispatcherServletInitializer {
//    @Override
//    protected String[] getServletMappings() {
//        return new String[] {"/"};
//    }
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] {Configuration.class};
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[0];
//    }
//}