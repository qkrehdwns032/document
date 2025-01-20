package document.apidocument.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
public class Parameter {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "paramName")
    private String paramName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "endpointId")
    private Endpoint endpoint;
}