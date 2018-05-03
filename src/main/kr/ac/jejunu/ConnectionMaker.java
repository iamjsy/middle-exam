package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public interface ConnectionMaker {
    public Connection getConnection() throws ClassNotFoundException, SQLException;
}
