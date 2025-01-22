package edu.school21.numbers.repositories;

import edu.school21.numbers.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepositoryJdbcImpl {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void update(Product product);

    void save(Product product);

    void delete(Long id);
}
