package org.teresadev.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teresadev.jobportal.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
