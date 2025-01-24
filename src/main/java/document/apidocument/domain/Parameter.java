package document.apidocument.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class Parameter {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "annotation")
    private String annotation;

    @Column(name = "type")
    private String type;

    @Column(name = "data")
    private String data;

    @ManyToOne
    @JoinColumn(name = "endpointId")
    @JsonBackReference
    private Endpoint endpoint;
}