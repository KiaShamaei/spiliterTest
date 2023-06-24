package snapp.pay.entities;

import lombok.*;
import javax.persistence.*;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "gang")
@Entity
public class Group  extends SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "gang", fetch = FetchType.LAZY)
    private List<UserGroup> userGroups;

    @OneToMany(mappedBy = "gang", fetch = FetchType.LAZY)
    private List<Bill> bills;

    @OneToOne
    @JoinColumn(name = "admin_user_id", referencedColumnName = "id")
    private User adminUser;

    public Group(String nam, User adminUser) {
        super();
        this.name = nam;
        this.adminUser = adminUser;
    }
}
