package dmitriikuzmin.quiz.repository;

import dmitriikuzmin.quiz.model.Participant;
import dmitriikuzmin.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByParticipant_Id(Long id);
}
