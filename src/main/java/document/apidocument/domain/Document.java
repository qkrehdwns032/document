package document.apidocument.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Table
public class Document {

    @Id
    @GeneratedValue
    public Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "statusCodeSuccess")
    private Integer statusCode;

    @Column(name = "statusCodeFail")
    private Integer statusCodeFail;

    @Column(name = "mediaType")
    private String mediaType;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<Endpoint> endpoints;
}
