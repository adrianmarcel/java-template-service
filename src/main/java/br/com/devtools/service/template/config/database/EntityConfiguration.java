package br.com.devtools.service.template.config.database;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("br.com.devtools")
public class EntityConfiguration {}
