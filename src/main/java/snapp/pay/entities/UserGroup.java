package snapp.pay.entities;

import lombok.*;
import javax.persistence.*;

/**
 *  UserGroup this class keep relation between user and group
 *  @Author Kiarash Shamaei 2023-06-25
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user_gang")
@Entity
public class UserGroup  extends SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gang_id", referencedColumnName = "id")
    private Group gang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public UserGroup(User user) {
        this.user = user;
    }

    public UserGroup(Group gang, User user) {
        this.gang = gang;
        this.user = user;
    }

}
