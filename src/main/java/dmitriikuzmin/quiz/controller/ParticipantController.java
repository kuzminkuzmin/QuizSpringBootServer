package dmitriikuzmin.quiz.controller;

import dmitriikuzmin.quiz.dto.ResponseResult;
import dmitriikuzmin.quiz.model.Participant;
import dmitriikuzmin.quiz.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("participant")
public class ParticipantController {
    private ParticipantService participantService;

    @Autowired
    public void setParticipantService(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Participant>> add(@RequestBody Participant participant) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.participantService.add(participant)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseResult<Participant>> get(@PathVariable long id) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.participantService.get(id)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/byName/{username}")
    public ResponseEntity<ResponseResult<Participant>> getByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.participantService.getByUsername(username)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Participant>>> getAll() {
        return new ResponseEntity<>(
                new ResponseResult<>(null, this.participantService.getAll()), HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Participant>> update(@RequestBody Participant participant) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.participantService.update(participant)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult<Participant>> delete(@PathVariable long id) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.participantService.delete(id)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }
}
