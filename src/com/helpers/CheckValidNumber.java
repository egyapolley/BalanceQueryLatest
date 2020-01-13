package com.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckValidNumber {



    public static boolean checkNumber(Connection connection, String msisdn)
    {
        String sql="select msisdn from valid_accounts where msisdn=?";
        if (!msisdn.isEmpty()&&msisdn.startsWith("233255")&&msisdn.length()==12)
        {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,msisdn);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) return true;


            } catch (SQLException e) {
              return false;
            }

        }
        return false;

    }
}
