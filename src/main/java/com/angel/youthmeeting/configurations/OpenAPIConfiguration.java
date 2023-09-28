package com.angel.youthmeeting.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Youth Meeting API Documentation",
                description = "Documentation for all api end points of youth meeting project"),
        servers = @Server(url = "http://localhost:8080")
)
class OpenAPIConfiguration {
}