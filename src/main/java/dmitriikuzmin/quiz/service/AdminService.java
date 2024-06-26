package dmitriikuzmin.quiz.service;

import dmitriikuzmin.quiz.model.Admin;

public interface AdminService {
    Admin add(Admin admin);
    Admin get(long id);
    Admin update(Admin admin);
    Admin delete(long id);
}
