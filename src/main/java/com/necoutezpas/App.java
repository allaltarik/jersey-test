package com.necoutezpas;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        ResourceConfig config = new CustomResourceConfig();
        config.packages("com.necoutezpas");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));


        Server server = new Server(2222);
        ServletContextHandler context = new ServletContextHandler(server, "/mobinaute");
        context.addServlet(new ServletHolder(TestServlet.class), "/v1/device");
        context.addServlet(servlet, "/v2/*");

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }
}
