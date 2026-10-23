package ssia.repository;

import org.springframework.stereotype.Repository;
import ssia.model.Document;
import ssia.utils.Names;

import java.util.Map;

@Repository
public class DocumentRepository {

    private final Map<String, Document> documents = Map.of(
                    "abc123", new Document(Names.NATALIE),
                    "qwe123", new Document(Names.NATALIE),
                    "asd555", new Document(Names.EMMA)
    );

    public Document findDocument(String code) {
        return documents.get(code);
    }
}
