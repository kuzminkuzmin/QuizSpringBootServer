package dmitriikuzmin.quiz.controller;

import dmitriikuzmin.quiz.dto.ResponseResult;
import dmitriikuzmin.quiz.model.Quiz;
import dmitriikuzmin.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    private QuizService quizService;

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/{participantId}")
    public ResponseEntity<ResponseResult<Quiz>> add(
            @PathVariable long participantId, @RequestParam int amount, int category, String difficulty) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(
                            null, this.quizService.add(
                            amount, category, difficulty, participantId)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseResult<Quiz>> get(@PathVariable long id) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.quizService.get(id)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/byParticipant/{participantId}")
    public ResponseEntity<ResponseResult<List<Quiz>>> getByParticipantId(@PathVariable long participantId) {
        return new ResponseEntity<>(
                new ResponseResult<>(null, this.quizService.getByParticipantId(participantId)), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult<Quiz>> delete(@PathVariable long id) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.quizService.delete(id)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }
}
