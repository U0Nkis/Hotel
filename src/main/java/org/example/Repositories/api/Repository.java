package org.example.Repositories.api;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void save(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
}
