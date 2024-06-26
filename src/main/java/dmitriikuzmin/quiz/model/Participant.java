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
@Table(name = "participants")
public class Participant extends User{
    @NonNull
    private String test;
}
