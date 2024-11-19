package ru.vsu.cs.trufanov.Repositories.api;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    T save(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
}
