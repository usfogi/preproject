package dataSet;

import javax.persistence.*;


@Entity
@Table(name = "userList")
public class UsersDataSet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Long age;

    public UsersDataSet() {
    }

    public UsersDataSet(String name, String password , Long age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public UsersDataSet(Long id, String name, String password, long age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
