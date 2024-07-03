package dmitriikuzmin.quiz.controller;

import dmitriikuzmin.quiz.dto.ResponseResult;
import dmitriikuzmin.quiz.model.Question;
import dmitriikuzmin.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("question")
public class QuestionController {
    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PutMapping()
    public ResponseEntity<ResponseResult<Question>> putResult(@RequestBody Question question) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.questionService.update(question)), HttpStatus.OK
            );
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }
}
