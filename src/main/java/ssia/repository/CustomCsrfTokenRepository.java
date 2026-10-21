package ssia.repository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;
import ssia.model.Token;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private final JpaTokenRepository jpaTokenRepository;

    public CustomCsrfTokenRepository(JpaTokenRepository jpaTokenRepository) {
        this.jpaTokenRepository = jpaTokenRepository;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest httpServletRequest) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    public void saveToken(CsrfToken csrfToken,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        Optional<Token> existingToken = getToken(httpServletRequest);

        if (existingToken.isPresent()) {

            Token token = existingToken.get();
            token.setToken(csrfToken.getToken());
        } else {

            Token token = new Token();
            token.setToken(csrfToken.getToken());
            token.setIdentifier(httpServletRequest.getHeader("X-IDENTIFIER"));
            jpaTokenRepository.save(token);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest httpServletRequest) {

        Optional<Token> existingToken = getToken(httpServletRequest);

        if (existingToken.isPresent()) {

            Token token = existingToken.get();
            return new DefaultCsrfToken(
                    "X-CSRF-TOKEN",
                    "_csrf",
                    token.getToken());
        }

        return null;
    }

    public Optional<Token> getToken(HttpServletRequest httpServletRequest) {
        String identifier = httpServletRequest.getHeader("X-IDENTIFIER");
        return jpaTokenRepository.findTokenByIdentifier(identifier);
    }
}
