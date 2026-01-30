package com.flipkart;

/**
 * Hello world!
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipkart.rest.FlipFitGymOwnerController;
import com.flipkart.rest.FlipFitCustomerController;
import com.flipkart.rest.FlipFitAdminController;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");

        // Register all FlipFit REST Controllers
        e.jersey().register(new FlipFitGymOwnerController());
        e.jersey().register(new FlipFitCustomerController());
        e.jersey().register(new FlipFitAdminController());

        LOGGER.info("All controllers registered successfully!");
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
