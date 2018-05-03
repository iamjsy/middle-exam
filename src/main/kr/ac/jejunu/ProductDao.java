package kr.ac.jejunu;

import java.sql.*;

public class ProductDao  {
    private final ConnectionMaker connectionMaker;

    public ProductDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public Product get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product;
        try {
            connection = connectionMaker.getConnection();
            preparedStatement = connection.preparedStatement("selct * from product where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            product = new Product();
            product.setLong(resultSet.getLong("id"));
            product.setTitle(resultSet.getString("title"));
            product.setPrice(resultSet.getInt("price"));
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return product;
    }

    public Long insert(Product product) throws SQLException, ClassNotFoundException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id;
        try {
            Connection connection = connectionMaker.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "insert into product(title, price) values (?, ?)");
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("select last_insert_id()");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return id;
    }

}