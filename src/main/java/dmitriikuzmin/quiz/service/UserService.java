package dmitriikuzmin.quiz.service;

import dmitriikuzmin.quiz.model.User;

public interface UserService {
    User findByUsername(String username);
    User getById(long id);
}
