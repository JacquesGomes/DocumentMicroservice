package org.example.controller;

import org.example.model.Document;
import org.example.storage.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class DocumentController {
    private static final Logger log =
            LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentRepository documentRepository = null;

    @ExceptionHandler
    public ResponseEntity<String> handle(ResponseStatusException rse) {
        return new ResponseEntity<String>(rse.getMessage(), rse.getStatusCode());
    }

    @PostMapping("document")
    public void storeDocument(@RequestBody Document document) {
        try{
            document = documentRepository.saveAndFlush(document);
            log.info("Document saved to the database: " + document.noteInformation());
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "An error ocurred when saving the documento to the " +
                    "database");
        }

    }

    @GetMapping("document/{docid}")
    public Document getDocument (@PathVariable String docid){
        Document document = null;

        try {

            document = documentRepository.findDocumentByDocId(docid);
            if(document == null || document.getDocId().equals("")){
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Document not found with docId: " + docid);
            } else {
                log.info("Retrieved doc with success!");
            }
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "Error retrieving the document");
        }

        return document;
    }

    @GetMapping("documents")
    public List<Document> getAllDocuments() {
        List<Document> documents;

        try {
            documents = documentRepository.findAll();
            if (documents.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "No documents found in the database");
            } else {
                log.info("Retrieved documents with success!");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error retrieving documents");
        }
        return documents;
    }

    @GetMapping("documents/exist")
    public boolean checkDocumentsExist(@RequestParam List<String> docids) {
        for (String docid : docids) {
            Document document = documentRepository.findDocumentByDocId(docid);
            if (document == null || document.getDocId().equals("")) {
                return false; // At least one document doesn't exist
            }
        }
        return true; // All documents exist
    }

}
