package document.apidocument.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue
    public Long id;

    @Column(name = "role") // admin과 user?
    private String role;

    @Column(name = "username")
    private String username;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "loginId",unique = true)
    private String loginId;

    @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Project> projects = new ArrayList<>();
}
