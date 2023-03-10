package kz.timka.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// замена web.xml тут настройка DispatcherServlet
public class MySpringMvcDispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class}; // говорим где находится конфиг класс
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"}; // все запросы от пользователя отправляем на DispatcherServlet
    }
}
