package ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        String usersByUsernameQuery =
                "select username, password, true as enabled from users where username = ?";

        String authsByUserQuery =
                "select u.username, a.name " +
                        "from users u join authority a on u.id = a.users " +
                        "where u.username = ?";

        var manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery(usersByUsernameQuery);
        manager.setAuthoritiesByUsernameQuery(authsByUserQuery);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
