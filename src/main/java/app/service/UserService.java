package app.service;

import app.entity.User;
import app.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepo userRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public void saveUser(User user){
        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(newUser);
    }

    public boolean isRegistered(String email) {
        User byEmail = userRepo.findByEmail(email);
        return byEmail != null && email.equals(byEmail.getUsername());
    }

    public boolean isValid(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password);
    }
}
