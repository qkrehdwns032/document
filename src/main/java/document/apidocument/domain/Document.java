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

    @Column(name = "method")
    private String method;

    @Column(name = "endPoint")
    private String endPoint;

    @Column(name = "statusCodeSuccess")
    private Long statusCode;

    @Column(name = "statusCodeFail")
    private Long statusCodeFail;

    @Column(name = "mediaType")
    private String mediaType;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;
}
