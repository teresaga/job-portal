package org.teresadev.jobportal.services;

import org.springframework.stereotype.Service;
import org.teresadev.jobportal.entity.UsersType;
import org.teresadev.jobportal.repository.UsersTypeRepository;

import java.util.List;

@Service
public class UsersTypeService {

    private final UsersTypeRepository usersTypeRepository;

    public UsersTypeService(UsersTypeRepository usersTypeRepository) {
        this.usersTypeRepository = usersTypeRepository;
    }

    public List<UsersType> getAll() {
        return usersTypeRepository.findAll();
    }
}
