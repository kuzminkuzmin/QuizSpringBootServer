package dmitriikuzmin.quiz.repository;

import dmitriikuzmin.quiz.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
