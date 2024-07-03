package dmitriikuzmin.quiz.service;

import dmitriikuzmin.quiz.model.Quiz;

import java.util.List;

public interface QuizService {
    Quiz add(String settings, long participantId);

    Quiz get(long id);

    List<Quiz> getByParticipantId(long participantId);

    Quiz delete(long id);
}
