package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String rolename;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {

    }

    public Role(String rolename) {

        if (rolename.equals("ROLE_ADMIN")) {
            this.id = 1L;

        }else if(rolename.equals("ROLE_USER")) {
            this.id = 2L;
        }
        this.rolename = rolename;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String role) {
        this.rolename = role;
    }

    @Override
    public String getAuthority() {
        return rolename;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
