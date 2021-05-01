package com.trevorism.gcloud

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource
import org.glassfish.jersey.server.ResourceConfig

class JerseyConfiguration extends ResourceConfig {
    JerseyConfiguration() {
        register(OpenApiResource)
        packages("com.trevorism.gcloud")
    }
}
