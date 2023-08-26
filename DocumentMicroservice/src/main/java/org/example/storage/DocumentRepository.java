package org.example.storage;

import org.example.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Document findDocumentByDocId(String docId);
}
