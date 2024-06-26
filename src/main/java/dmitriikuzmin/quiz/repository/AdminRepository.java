package dmitriikuzmin.quiz.repository;

import dmitriikuzmin.quiz.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
