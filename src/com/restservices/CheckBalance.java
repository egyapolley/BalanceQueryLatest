package com.restservices;

import com.helpers.FinalResult;
import com.helpers.QueryDB;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class CheckBalance {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get_balances/{id}")
    public FinalResult checkBalance(@PathParam("id") String callingMsisdn)
    {
        QueryDB queryDB=new QueryDB() ;
        FinalResult getbalances = queryDB.getbalances(callingMsisdn);
        return getbalances;
    }
}
