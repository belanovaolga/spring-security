package ssia.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import ssia.model.Employee;
import ssia.utils.Names;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final Map<String, Employee> records =
            Map.of(Names.EMMA,
                    new Employee("Emma Thompson",
                            List.of("Karamazov Brothers"),
                            List.of("accountant", "reader")),
                    Names.NATALIE,
                    new Employee("Natalie Parker",
                            List.of("Beautiful Paris"),
                            List.of("researcher"))
            );

    @PostAuthorize("returnObject.roles.contains('reader')")
    public Employee getBookDetails(String name) {
        return records.get(name);
    }
}
