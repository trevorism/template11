package com.trevorism.gcloud

import jakarta.ws.rs.core.Application
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.glassfish.jersey.servlet.ServletContainer

class Startup extends Application{

    private static final String JERSEY_SERVLET_NAME = "jersey-container-servlet"

    static void main(String[] args) throws Exception {
        System.setProperty("org.eclipse.jetty.LEVEL", "DEBUG");

        new Startup().start()
    }

    void start() throws Exception {
        Server server = new Server(Integer.valueOf(8080))

        ServletContextHandler context = new ServletContextHandler(server, "/")
        context.setContextPath("/")

        ServletHolder servlet = new ServletHolder(JERSEY_SERVLET_NAME, new ServletContainer(new JerseyConfiguration()))
        servlet.setInitParameter("jersey.config.server.wadl.disableWadl","true")
        context.addServlet(servlet, "/*")

        ServletHolder holderHome = new ServletHolder("static-home", DefaultServlet.class);

        holderHome.setInitParameter("resourceBase", Startup.class.getResource("/swagger").toExternalForm())
        holderHome.setInitParameter("dirAllowed", "true")
        holderHome.setInitParameter("pathInfoOnly", "true")
        context.addServlet(holderHome, "/swagger/*")

        ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
        holderPwd.setInitParameter("dirAllowed", "true");
        context.addServlet(holderPwd, "/");

        try {
            server.start()
            server.join()
        } finally {
            server.destroy()
        }
    }

}
