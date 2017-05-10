package com.necoutezpas;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Singleton;

public class CustomResourceConfig  extends ResourceConfig {
    public CustomResourceConfig() {
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindAsContract(MyDependency.class).in(Singleton.class);
            }
        });
    }
}
