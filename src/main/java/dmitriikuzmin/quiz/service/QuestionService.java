package dmitriikuzmin.quiz.service;

import dmitriikuzmin.quiz.model.Question;

import java.util.List;

public interface QuestionService {
    Question add(Question question);
    Question get(long id);
    List<Question> getByQuiz(long quizId);
    Question update(Question question);
}
