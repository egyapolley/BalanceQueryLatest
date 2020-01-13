package com.database;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbUtils {

    public static Connection getConnection()

    {
        try {
            InitialContext initialContext = new InitialContext();
            DataSource datasource = (DataSource) initialContext.lookup("jdbc/oracleindb");
            Connection connection = datasource.getConnection();
            if (connection != null) return connection;

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    }

