package org.jwt.auth.utility.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditConfiguration();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
