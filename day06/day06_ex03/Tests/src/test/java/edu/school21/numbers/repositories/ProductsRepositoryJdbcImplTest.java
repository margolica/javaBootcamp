package edu.school21.numbers.repositories;

import edu.school21.numbers.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "Cheese", 250.00),
            new Product(2L, "Bread", 100.00),
            new Product(3L, "Milk", 80.00),
            new Product(4L, "Ice cream", 140.00),
            new Product(5L, "Chocolate", 80.00)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L, "Cheese", 250);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "Cheese", 350);
    final Product EXPECTED_DELETE_PRODUCT = new Product(2L, "Bread", 100);

    public EmbeddedDatabase dataBase = null;
    ProductsRepositoryJdbcImpl productsRepository = null;

    @BeforeEach
    void init() {
        dataBase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .continueOnError(true)
                .ignoreFailedDrops(true)
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataBase);
    }

    @AfterEach
    public void closeConnection() throws SQLException {
        dataBase.shutdown();
        dataBase.getConnection().close();
    }

    @Test
    void testFindAll() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    void testFindById() {
        Product obj = productsRepository.findById(1L).get();
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, obj);
    }

    @Test
    void testUpdate() {
        productsRepository.update(EXPECTED_FIND_BY_ID_PRODUCT);
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(1L).get());
    }

    @Test
    void testDelete() {
        productsRepository.delete(1L);
        Assertions.assertEquals(EXPECTED_DELETE_PRODUCT, productsRepository.findById(2L).get());
    }
}
