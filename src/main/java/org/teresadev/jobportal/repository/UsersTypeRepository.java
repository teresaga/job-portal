package org.teresadev.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teresadev.jobportal.entity.UsersType;

public interface UsersTypeRepository extends JpaRepository<UsersType, Integer> {
}
