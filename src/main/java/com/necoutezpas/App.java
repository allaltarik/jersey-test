package com.necoutezpas;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
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

        // Build the Swagger Bean
        final HandlerList handlers = new HandlerList();
        // Handler for Swagger UI, static handler.
        handlers.addHandler(buildSwaggerUI());
        // Handler for API
        handlers.addHandler(buildApi());

        Server server = new Server(8080);
        try {
            server.setHandler( handlers );
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

    private static void buildSwagger()
    {
        // This configures Swagger
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion( "1.0.0" );
        beanConfig.setResourcePackage("com.necoutezpas");
        beanConfig.setScan( true );
        beanConfig.setBasePath( "/mobinaute/v2" );
        beanConfig.setDescription( "Test API to demonstrate Swagger with Jersey2");
        beanConfig.setTitle( "Test API" );
    }

    private static ContextHandler buildApi()
    {
        buildSwagger();

        ResourceConfig config = new CustomResourceConfig();
        config.packages("com.necoutezpas", ApiListingResource.class.getPackage().getName());
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/mobinaute");
        context.addServlet(new ServletHolder(TestServlet.class), "/v1/device");
        context.addServlet(servlet, "/v2/*");
        return context;
    }

    private static ContextHandler buildSwaggerUI() throws Exception
    {
        final ResourceHandler swaggerUIResourceHandler = new ResourceHandler();
        swaggerUIResourceHandler.setResourceBase( App.class.getClassLoader().getResource( "swaggerui" ).toURI().toString() );
        final ContextHandler swaggerUIContext = new ContextHandler();
        swaggerUIContext.setContextPath("/docs/");
        swaggerUIContext.setHandler(swaggerUIResourceHandler);

        return swaggerUIContext;
    }
}
