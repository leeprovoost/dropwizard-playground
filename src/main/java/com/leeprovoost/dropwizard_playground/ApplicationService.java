package com.leeprovoost.dropwizard_playground;

import com.leeprovoost.dropwizard_playground.health.RegistrationHealthCheck;
import com.leeprovoost.dropwizard_playground.repository.RegistrationRepository;
import com.leeprovoost.dropwizard_playground.resources.RegistrationResource;
import com.leeprovoost.dropwizard_playground.service.RegistrationService;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class ApplicationService extends Service<ApplicationConfiguration> {

    public static void main(String[] args) throws Exception {
        new ApplicationService().run(args);
    }
   
	@Override
	public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {
		bootstrap.setName("dropwizard-playground");
		bootstrap.addBundle(new AssetsBundle("/swagger-ui"));
	}

	@Override
	public void run(ApplicationConfiguration configuration, Environment environment) throws Exception {

        DB mongoDB = null;
        
        // TODO handle database exceptions
        mongoDB = new MongoClient(
                configuration.getMongoHost(),
                configuration.getMongoPort()
        ).getDB(configuration.getMongoDb());

        final RegistrationRepository registrationRepository = new RegistrationRepository(mongoDB);

        RegistrationService registrationService = new RegistrationService(registrationRepository);
        environment.addResource(new RegistrationResource(registrationService));
        
        // Swagger
        environment.addResource(new ApiListingResourceJSON());
        environment.addProvider(new ResourceListingProvider());
        environment.addProvider(new ApiDeclarationProvider());
        ScannerFactory.setScanner(new DefaultJaxrsScanner());
        ClassReaders.setReader(new DefaultJaxrsApiReader());
        SwaggerConfig config = ConfigFactory.config();
        config.setApiVersion("1.0.0");
        config.setBasePath("http://localhost:3000");
        
        // Healthchecks
        environment.addHealthCheck(new RegistrationHealthCheck());
        
	}

}
