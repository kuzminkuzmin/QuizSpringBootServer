package dmitriikuzmin.quiz.service;

import dmitriikuzmin.quiz.model.Participant;

public interface ParticipantService {
    Participant add(Participant participant);
    Participant get(long id);
    Participant update(Participant participant);
    Participant delete(long id);
}
