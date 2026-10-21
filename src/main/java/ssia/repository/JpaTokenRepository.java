package ssia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssia.model.Token;

import java.util.Optional;

public interface JpaTokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findTokenByIdentifier(String identifier);
}
