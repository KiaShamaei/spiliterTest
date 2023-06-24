package snapp.pay.entities;

import lombok.*;
import javax.persistence.*;


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
