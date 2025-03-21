package org.teresadev.jobportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teresadev.jobportal.entity.Users;
import org.teresadev.jobportal.repository.UsersRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {

    final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users addNew(Users user) {
        user.setActive(true);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        return usersRepository.save(user);
    }

    public Optional<Users> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

}
