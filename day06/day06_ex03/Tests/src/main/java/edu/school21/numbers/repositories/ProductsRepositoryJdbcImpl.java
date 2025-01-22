package edu.school21.numbers.repositories;

import edu.school21.numbers.model.Product;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private DataSource dataSource = null;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try {
            PreparedStatement statement = getDataSource().getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                products.add(new Product(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

//    @Override
//    public List<Product> findAll() {
//        List<Product> products = new ArrayList<>();
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(
//                     "SELECT * FROM product")) {
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    products.add(new Product(
//                            resultSet.getLong("id"),
//                            resultSet.getString("name"),
//                            resultSet.getDouble("price")
//                    ));
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Connection failed: " + e.getMessage());
//        }
//        return products;
//    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> product = Optional.empty();
        String sql = "SELECT * FROM product WHERE id = ?";
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product productResult = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3));
                product = Optional.ofNullable(productResult);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET name = ?, price = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDouble(3, product.getId());
//            System.out.println("Количество измененных строк: " + preparedStatement.executeUpdate() + '\n');
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product (name, description, price) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
