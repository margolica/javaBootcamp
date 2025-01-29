package edu.school21.service.repositories;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UsersRepository<T> extends CrudRepository {
    public Optional<T> findByEmail(String email);

    public Long maxUserId();
}
