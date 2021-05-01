package com.trevorism.gcloud.webapi.controller

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@OpenAPIDefinition(
        info = @Info(
                description = "API",
                version = "1",
                title = "API",
                contact = @Contact(name = "Trevor Brooks", url = "https://www.trevorism.com")
        )
)
@Path("/")
class RootController {

    @Operation(summary = "Returns 'pong' if the application is alive")
    @GET
    @Path("ping")
    @Produces(MediaType.APPLICATION_JSON)
    String ping() {
        "pong"
    }

    @Operation(summary = "Context root of the application")
    @GET
    String displayHelpLink(){
        '<h1>API</h1><br/>Visit the help page at <a href="/help">/help'
    }

    @Operation(summary = "Shows this help page")
    @GET
    @Path("help")
    Response help(){
        Response.temporaryRedirect(new URI("/swagger/index.html")).build()
    }
}
