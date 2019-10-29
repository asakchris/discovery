package com.example.swagger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.swagger.config.SwaggerServicesConfig;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Configuration
@Primary
public class DocumentationController implements SwaggerResourcesProvider {
  public static final String SWAGGER_VERSION_INFO = "2.0";

  @Autowired
  private SwaggerServicesConfig swaggerServiceList;

  @Override
  public List<SwaggerResource> get() {
    List<SwaggerResource> resources = new ArrayList<>();
    swaggerServiceList.getServices().forEach(service -> resources
        .add(swaggerResource(service.getName(), service.getUrl(), service.getVersion())));

    return resources;
  }

  private SwaggerResource swaggerResource(String name, String location, String version) {
    SwaggerResource swaggerResource = new SwaggerResource();
    swaggerResource.setName(name);
    swaggerResource.setLocation(location);
    swaggerResource.setSwaggerVersion(version);
    return swaggerResource;
  }

}
