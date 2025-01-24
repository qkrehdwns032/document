package document.apidocument.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    @Column(name = "projectCreator")
    private String projectCreator;

    @ManyToMany
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private List<Document> documents = new ArrayList<>();
}
