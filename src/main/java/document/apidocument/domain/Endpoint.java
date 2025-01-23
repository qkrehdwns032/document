package document.apidocument.domain;

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

    @ManyToOne
    @JoinColumn(name = "documentId")
    private Document document;

    @OneToMany(mappedBy = "endpoint", cascade = CascadeType.ALL)
    private List<Parameter> parameters;
}
