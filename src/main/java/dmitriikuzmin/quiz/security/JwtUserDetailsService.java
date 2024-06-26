package dmitriikuzmin.quiz.security;



import dmitriikuzmin.quiz.repository.UserRepository;
import dmitriikuzmin.quiz.model.User;
import dmitriikuzmin.quiz.security.jwt.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("User with username: " + userName + " not found"));

        log.info("IN loadUserByUsername - user with username: {} successfully loaded", userName);
        return user.map(JwtUser::new).get();
    }
}
