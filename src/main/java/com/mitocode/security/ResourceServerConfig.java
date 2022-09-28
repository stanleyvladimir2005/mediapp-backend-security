package com.mitocode.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

//Segunda Clase
@Configuration
@EnableResourceServer
@Deprecated
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
    private ResourceServerTokenServices tokenServices;	
	
    @Value("${security.jwt.resource-ids}")
    private String resourceIds;
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
                http
                .exceptionHandling().authenticationEntryPoint(new AuthException())
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()                  
                .antMatchers("/swagger-ui.html" ).permitAll()
                .antMatchers("/v1/consulta-examenes/**" ).authenticated()
                .antMatchers("/v1/pacientes/**" ).authenticated()
                .antMatchers("/v1/consultas/**" ).authenticated()
                .antMatchers("/languages/**" ).authenticated()
                .antMatchers("/v1/especialidades/**" ).authenticated()
                .antMatchers("/v1/examenes/**" ).authenticated()
                .antMatchers("/v1/medicos/**" ).authenticated()
                .antMatchers("/v1/menus/**" ).authenticated()
                .antMatchers("/tokens/anulate/**" ).permitAll()
                .antMatchers("/tokens/**" ).authenticated()                
                .anyRequest().authenticated();
    }
}