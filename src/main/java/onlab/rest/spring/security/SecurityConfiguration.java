package onlab.rest.spring.security;
 
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    private static String REALM="MY_TEST_REALM";
    
    @Autowired
    DataSource dataSource;
     
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {     
        auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select user_name as principal, password as credentials, 1 as enabled FROM USER_TABLE where user_name=lower(?)")
		.authoritiesByUsernameQuery(
				"select user_name as principal, role as role from user_table where user_name=lower(?)");
    }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
  
      http.csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/user/").permitAll()
        .antMatchers("/user/idbyname/**").access("hasRole('ROLE_USER')")
        .antMatchers("/user/namebyid/**").access("hasRole('ROLE_USER')")
        .antMatchers("/user/instituteidbyname/**").access("hasRole('ROLE_USER')")
        .antMatchers("/user/**").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.GET, "/institute/").permitAll()
        .antMatchers("/institute/**").permitAll()
        .antMatchers("/comment/**").access("hasRole('ROLE_USER')")
        .antMatchers("/subject/**").access("hasRole('ROLE_USER')")
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
    }
     
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }
     
    /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}