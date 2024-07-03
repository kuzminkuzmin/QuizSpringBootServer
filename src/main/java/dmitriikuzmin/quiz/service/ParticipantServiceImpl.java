package dmitriikuzmin.quiz.service;

import dmitriikuzmin.quiz.repository.ParticipantRepository;
import dmitriikuzmin.quiz.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        try {
            participant.setPassword(this.bCryptPasswordEncoder.encode(participant.getPassword()));
            return this.participantRepository.save(participant);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Participant already exists");
        }
    }

    @Override
    public Participant get(long id) {
        return this.participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found"));
    }

    @Override
    public Participant getByUsername(String username) {
        return this.participantRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found"));
    }

    @Override
    public List<Participant> getAll() {
        return this.participantRepository.findAll();
    }

    @Override
    public Participant update(Participant participant) {
        try {
            Participant base = this.get(participant.getId());
            base.setFirstName(participant.getFirstName());
            base.setLastName(participant.getLastName());
            base.setQuizzes(participant.getQuizzes());
            return this.participantRepository.save(base);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Participant already exists");
        }
    }

    @Override
    public Participant delete(long id) {
        try {
            Participant base = this.get(id);
            this.participantRepository.delete(base);
            return base;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Participant not found");
        }
    }
}
