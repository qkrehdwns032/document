package document.apidocument.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table
@Entity
public class Endpoint {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "path")
    private String path;

    @Column(name = "method")
    private String method;

    @Column(name = "description")
    private String description;

    @Column(name = "contentType")
    private String contentType;

    @Column(name = "statusCodeSuccess")
    private Integer statusCodeSuccess;

    @Column(name = "statusCodeFail")
    private Integer statusCodeFail;

    @ManyToOne
    @JoinColumn(name = "documentId")
    @JsonBackReference
    private Document document;

    @OneToMany(mappedBy = "endpoint", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Parameter> parameters;
}
