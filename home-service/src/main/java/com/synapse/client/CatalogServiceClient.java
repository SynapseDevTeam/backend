package com.synapse.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/catalog")
@RegisterRestClient(configKey = "catalog-api")
public interface CatalogServiceClient {
    @GET
    @Path("/{id}")
    ProductDTO getProductById(@PathParam("id") String id);
}
