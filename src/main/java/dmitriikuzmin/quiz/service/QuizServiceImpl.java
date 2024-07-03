package dmitriikuzmin.quiz.service;

import dmitriikuzmin.quiz.dto.ResponseResult;
import dmitriikuzmin.quiz.model.*;
import dmitriikuzmin.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QuizServiceImpl implements QuizService {
    private QuizRepository quizRepository;
    private ParticipantService participantService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("https://opentdb.com/api.php?")
    private String url;

    @Autowired
    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Autowired
    public void setParticipantService(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @Override
    public Quiz add(String settings, long participantId) {
        try {
            Participant participant = this.participantService.get(participantId);
            ResponseEntity<Quiz> response = this.restTemplate.exchange(this.url + settings,
                    HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
            Objects.requireNonNull(response.getBody()).setParticipant(participant);
            for (Question question : response.getBody().getQuestions()) {
                question.setQuiz(response.getBody());
            }
            return this.quizRepository.save(Objects.requireNonNull(response.getBody()));
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Quiz get(long id) {
        return this.quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));
    }

    @Override
    public List<Quiz> getByParticipantId(long participantId) {
        return this.quizRepository.findByParticipant_Id(participantId);
    }

    @Override
    public Quiz delete(long id) {
        try {
            Quiz quiz = this.get(id);
            this.quizRepository.delete(quiz);
            return quiz;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
