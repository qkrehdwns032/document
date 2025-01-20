package document.apidocument.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "projectName")
    private String projectName;

    @Column(name = "description")
    private String description;

    @Column(name = "isPrivate")
    private Boolean isPrivate;

    @ManyToMany
    private List<User> users;

    @OneToMany(mappedBy = "project")
    private List<Document> documents;
}
