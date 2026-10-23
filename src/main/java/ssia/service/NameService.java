package ssia.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ssia.roles.Authorities;

@Service
public class NameService {

    @PreAuthorize("hasAuthority('" + Authorities.WRITE_AUTHORITY + "')")
    public String getName() {
        return "Fantastico";
    }
}
