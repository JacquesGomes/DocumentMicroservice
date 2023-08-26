package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name="DOCUMENT")
public class Document {

    @GeneratedValue(strategy = GenerationType.TABLE)
    @Id
    @Column(name = "PK_ID")
    private Long id = null;

    @Column(name = "DOC_ID", unique = true)
    private String docId = "";

    @Column(name = "NAME")
    private String name = "";

    @Column(name = "DESCRIPTION")
    private String description = "";

    @Column(name = "PATH")
    private String path = "";

    public Document() {
    }

    public Document(Long id, String docId, String name, String description, String path) {
        this.id = id;
        this.docId = docId;
        this.name = name;
        this.description = description;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String noteInformation(){
        return "id: "+ id + ", docId: " + docId + ", name: " + name + ", " +
                "description: " + description + ", path: " + path;
    }
}
