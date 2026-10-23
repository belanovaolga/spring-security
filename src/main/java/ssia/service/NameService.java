package ssia.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ssia.utils.Authorities;
import ssia.utils.Names;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

    private static final String FANTASTICO = "Fantastico";
    private static final String ENERGICO = "Energico";
    private static final String PERFECTO = "Perfecto";

    private final Map<String, List<String>> secretNames =
            Map.of(
                    Names.NATALIE, List.of(ENERGICO, PERFECTO),
                    Names.EMMA, List.of(FANTASTICO)
            );

    @PreAuthorize("#name == authentication.principal.username")
    public List<String> getSecretNames(String name) {
        return secretNames.get(name);
    }

    @PreAuthorize("hasAuthority('" + Authorities.WRITE_AUTHORITY + "')")
    public String getName() {
        return FANTASTICO;
    }
}
