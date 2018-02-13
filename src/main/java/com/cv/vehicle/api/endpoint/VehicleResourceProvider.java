package com.cv.vehicle.api.endpoint;

import com.cv.vehicle.pojo.VehicleAgreement;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
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
        VinPublisher.send(vehAgreement);
        str = "Data pushed to activeMQ topic -> vin";
        Response resp = Response.ok().entity(str).build();
        return resp;
    }
}
