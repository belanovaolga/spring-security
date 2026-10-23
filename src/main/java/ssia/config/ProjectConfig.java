package ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ssia.roles.Authorities;

@Configuration
@EnableMethodSecurity
public class ProjectConfig {

    private static final String PASSWORD = "12345";
    private static final String NATALIE = "natalie";
    private static final String EMMA = "emma";

    @Bean
    public UserDetailsService userDetailsService() {

        var service = new InMemoryUserDetailsManager();
        var u1 = User.withUsername(NATALIE)
                .password(PASSWORD)
                .authorities(Authorities.READ_AUTHORITY)
                .build();
        var u2 = User.withUsername(EMMA)
                .password(PASSWORD)
                .authorities(Authorities.WRITE_AUTHORITY)
                .build();

        service.createUser(u1);
        service.createUser(u2);

        return service;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
