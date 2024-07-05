package dmitriikuzmin.quiz.controller;

import dmitriikuzmin.quiz.dto.ResponseResult;
import dmitriikuzmin.quiz.model.Admin;
import dmitriikuzmin.quiz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Admin>> add(@RequestBody Admin admin) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.adminService.add(admin)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseResult<Admin>> get(@PathVariable long id) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.adminService.get(id)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/byName/{username}")
    public ResponseEntity<ResponseResult<Admin>> getByName(@PathVariable String username) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.adminService.getByUsername(username)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Admin>> update(@RequestBody Admin admin) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.adminService.update(admin)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult<Admin>> delete(@PathVariable long id) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.adminService.delete(id)), HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        }
    }
}
