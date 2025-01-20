package document.apidocument.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table
public class User {

    @Id
    @GeneratedValue
    public Long id;

    @Column(name = "role")
    private String role;

    @Column(name = "username")
    private String username;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "loginId")
    private String loginId;

    @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Project> projects;
}
