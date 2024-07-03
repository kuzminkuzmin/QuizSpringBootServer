package dmitriikuzmin.quiz.service;

import dmitriikuzmin.quiz.model.Question;
import dmitriikuzmin.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository questionRepository;

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(Question question) {
        try {
            return questionRepository.save(question);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Question get(long id) {
        return this.questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

    }

    @Override
    public List<Question> getByQuiz(long quizId) {
        return this.questionRepository.findByQuizId(quizId);
    }

    @Override
    public Question update(Question question) {
        try {
            Question base = this.get(question.getId());
            base.setResult(question.getResult());
            return questionRepository.save(base);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
