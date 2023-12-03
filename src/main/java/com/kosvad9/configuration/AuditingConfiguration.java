package com.kosvad9.configuration;

import com.kosvad9.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;

@Configuration
@EnableEnversRepositories(basePackageClasses = ApplicationRunner.class)
public class AuditingConfiguration {
}
