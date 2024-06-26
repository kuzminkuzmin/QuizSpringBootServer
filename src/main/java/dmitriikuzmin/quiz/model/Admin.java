package dmitriikuzmin.quiz.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "admins")
public class Admin extends User {
    @NonNull
    private String position;
}
