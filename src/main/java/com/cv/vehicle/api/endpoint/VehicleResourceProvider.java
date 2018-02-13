package com.cv.vehicle.api.endpoint;

import com.cv.vehicle.pojo.VehicleAgreement;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.stream.Collector;
import com.cv.vehicle.com.cv.vehicle.publisher.VinPublisher;

@Path("/vehicles")
public class VehicleResourceProvider {

    String str;
    @GET
    @Path("{vin}")
    public String getVehicleInfo() {
        return "Vehicle details to be published";
    }

    @POST
    public Response postVehicleInfoToJms(VehicleAgreement vehAgreement){
        //VinPublisher publisher = new VinPublisher();
        VinPublisher.send(vehAgreement);


        Map<String, String> map = System.getenv();
        map.forEach((k,v)->{
            str = str+k+" "+v+"\n";
        });
        Response resp = Response.ok().entity(str).build();
        return resp;
    }
}
