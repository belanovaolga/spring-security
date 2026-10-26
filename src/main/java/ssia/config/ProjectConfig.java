package ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ssia.utils.Authorities;
import ssia.utils.Names;

@Configuration
@EnableMethodSecurity
public class ProjectConfig {

    private static final String PASSWORD = "12345";

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        var uds = new InMemoryUserDetailsManager();
        var u1 = User.withUsername(Names.NIKOLAI)
                .password(PASSWORD)
                .authorities(Authorities.READ_AUTHORITY)
                .build();
        var u2 = User.withUsername(Names.JULIEN)
                .password(PASSWORD)
                .authorities(Authorities.WRITE_AUTHORITY)
                .build();

        uds.createUser(u1);
        uds.createUser(u2);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
