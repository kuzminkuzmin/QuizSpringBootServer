package dmitriikuzmin.quiz.service;

import dmitriikuzmin.quiz.repository.ParticipantRepository;
import dmitriikuzmin.quiz.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    private ParticipantRepository participantRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setParticipantRepository(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Participant add(Participant participant) {
        return null;
    }

    @Override
    public Participant get(long id) {
        return null;
    }

    @Override
    public Participant update(Participant participant) {
        return null;
    }

    @Override
    public Participant delete(long id) {
        return null;
    }
}
