package dmitriikuzmin.quiz.service;

import dmitriikuzmin.quiz.model.Participant;

import javax.servlet.http.Part;
import java.util.List;

public interface ParticipantService {
    Participant add(Participant participant);
    Participant get(long id);
    Participant getByUsername(String username);
    List<Participant> getAll();
    Participant update(Participant participant);
    Participant delete(long id);
}
