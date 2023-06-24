package snapp.pay.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
@Entity
public class User  extends SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private String contact;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserGroup> userGroup;

    public User(String nam, String email, String contact) {
        super();
        this.name = nam;
        this.email = email;
        this.contact = contact;
    }

}
