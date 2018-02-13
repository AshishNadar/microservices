package com.cv.vehicle.core;

import com.cv.vehicle.api.endpoint.VehicleResourceProvider;
import com.cv.vehicle.com.cv.vehicle.publisher.VinPublisher;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class VehicleApplication extends Application<VehicleConfiguration>{
    @Override
    public void run(VehicleConfiguration config, Environment env) throws Exception {

        new VinPublisher(config);
        env.jersey().register(new VehicleResourceProvider());
    }

    public static void main(String[] args) {
        try {
            new VehicleApplication().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
