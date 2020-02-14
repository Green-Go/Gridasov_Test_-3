package ru.Gridasov.Haulmont.Cofiguration;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import ru.Gridasov.Haulmont.ui.ProjectUi;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

public class Web {

    @WebServlet("/*")
    @VaadinServletConfiguration(ui = ProjectUi.class, productionMode = false)
    public static class Test3Servlet extends VaadinServlet{
    }

    @WebListener
    public static class Test3Listener implements ServletContextListener {

        @Override
        public void contextInitialized(ServletContextEvent servletContextEvent) {

        }

        @Override
        public void contextDestroyed(ServletContextEvent servletContextEvent) {

        }
    }
}
