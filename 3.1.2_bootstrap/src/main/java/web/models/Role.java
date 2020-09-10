package web.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() { }

    public Role(long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role(Set<User> users) {
        this.users = users;
    }

    public Role(String role) {
        this.roleName = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRole(String role) {
        this.roleName = role;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
