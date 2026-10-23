package ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ssia.utils.Roles;
import ssia.utils.Names;

@Configuration
@EnableMethodSecurity
public class ProjectConfig {

    private static final String PASSWORD = "12345";

    private final DocumentsPermissionEvaluator evaluator;

    public ProjectConfig(DocumentsPermissionEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Bean
    protected MethodSecurityExpressionHandler createExpressionHandler() {

        var expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(evaluator);

        return expressionHandler;
    }

    @Bean
    public UserDetailsService userDetailsService() {

        var service = new InMemoryUserDetailsManager();
        var u1 = User.withUsername(Names.NATALIE)
                .password(PASSWORD)
                .roles(Roles.ADMIN_ROLE)
                .build();
        var u2 = User.withUsername(Names.EMMA)
                .password(PASSWORD)
                .roles(Roles.MANAGER_ROLE)
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
