package dmitriikuzmin.quiz.service;

import dmitriikuzmin.quiz.repository.AdminRepository;
import dmitriikuzmin.quiz.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Admin add(Admin admin) {
        try {
            admin.setPassword(this.bCryptPasswordEncoder.encode(admin.getPassword()));
            return this.adminRepository.save(admin);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Admin already exists");
        }
    }

    @Override
    public Admin get(long id) {
        return this.adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
    }

    @Override
    public Admin getByUsername(String username) {
        return this.adminRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
    }

    @Override
    public Admin update(Admin admin) {
        try {
            Admin base = this.get(admin.getId());
            base.setPosition(admin.getPosition());
            return this.adminRepository.save(base);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Admin already exists");
        }
    }

    @Override
    public Admin delete(long id) {
        try {
            Admin admin = this.get(id);
            this.adminRepository.delete(admin);
            return admin;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Admin not found");
        }
    }
}
