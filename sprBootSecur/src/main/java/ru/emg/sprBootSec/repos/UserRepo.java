package ru.emg.sprBootSec.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.emg.sprBootSec.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}