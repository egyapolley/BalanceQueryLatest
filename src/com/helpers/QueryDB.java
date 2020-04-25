package com.helpers;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class QueryDB {
    private static final String DATA_WALLET = "Data Balance";
    private static final String CASH_WALLET = "Cash Balance";
    private static final String WEEKEND_BUNDLE = "Weekend Bundle";
    private static final double KB = 1048576;

    public FinalResult getbalances(String callingMSISDN) {
        FinalResult errorResult = new FinalResult();


        FinalResult sucessResult = new FinalResult();
        boolean autorecovery = false;
        double main_data_balance = 0;
        String main_data_expiry = "";
        String surfplusCountExpiry="";


        boolean empty = true;
        boolean no_cash = true;
        boolean no_maindata = true;
        boolean no_sub_data = true;


        double sub_data_balance = 0;
        String sub_data_expiry = "";


        ArrayList<Unlimited_Balance_Types> unlimitedBalance_types = new ArrayList<>();


        ArrayList<Data_Balance_Type> dataBalance_types = new ArrayList<>();
        String sql = "select balance_type, balance, expiry from account_balances where msisdn=?";
        String check_number_sql = "select msisdn from valid_accounts where msisdn=?";

        InitialContext initialContext = null;
        try {
            initialContext = new InitialContext();
            DataSource datasource = (DataSource) initialContext.lookup("jdbc/oracleindb");
            PreparedStatement preparedStatement1 = null;
            ResultSet resultSet1 = null;
            try (Connection connection = datasource.getConnection()) {
                if (connection != null) {
                    preparedStatement1 = connection.prepareStatement(check_number_sql);
                    preparedStatement1.setString(1, callingMSISDN);
                    resultSet1 = preparedStatement1.executeQuery();
                    if (resultSet1.next()) {

                        PreparedStatement preparedStatement = null;
                        ResultSet resultSet = null;
                        try {
                            preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setString(1, callingMSISDN);
                            resultSet = preparedStatement.executeQuery();
                            while (resultSet.next()) {

                                empty = false;

                                String balance_type = resultSet.getString("balance_type");
                                if (balance_type.equals("Data") || balance_type.matches(".*GBSurfplus Data")) {
                                    String databalanceType = CheckDataBalanceType.checkBalanceType(balance_type);
                                    if (databalanceType.equals("small")) {
                                        sub_data_balance += resultSet.getDouble("balance");
                                        if (sub_data_balance > 0) no_sub_data = false;
                                       // sub_data_expiry = resultSet.getString("expiry");
                                        sub_data_expiry = "01-01-1970 00:01:00";
                                    } else {
                                        main_data_balance += resultSet.getDouble("balance");
                                    }
                                    no_maindata = (main_data_balance <= 0);
                                    //main_data_expiry = resultSet.getString("expiry");
                                    main_data_expiry = "01-01-1970 00:01:00";

                                } else if (balance_type.equals("Surfplus Count")) {
                                    int balance = resultSet.getInt("balance");
                                    if (balance >= 1) surfplusCountExpiry = resultSet.getString("expiry");

                                } else if (balance_type.equals("ULDayNitePlan Status") || balance_type.equals("ULBusiness2 Status")) {
                                    int balance = resultSet.getInt("balance");
                                    if (balance >= 1)
                                        unlimitedBalance_types.add(new Unlimited_Balance_Types("Unlimited Data", "active", resultSet.getString("expiry")));

                                } else if (balance_type.equals("ULNitePlan Status")) {
                                    int balance = resultSet.getInt("balance");
                                    if (balance >= 1)
                                        unlimitedBalance_types.add(new Unlimited_Balance_Types("Night Plan", "active", resultSet.getString("expiry")));

                                } else if (balance_type.equals("UL_AlwaysON_Standard Status")) {
                                    int balance = resultSet.getInt("balance");
                                    if (balance >= 1)
                                        unlimitedBalance_types.add(new Unlimited_Balance_Types("AlwaysON STANDARD Package", "active", resultSet.getString("expiry")));

                                } else if (balance_type.equals("UL_AlwaysON_Super Status")) {
                                    int balance = resultSet.getInt("balance");
                                    if (balance >= 1)
                                        unlimitedBalance_types.add(new Unlimited_Balance_Types("AlwaysON SUPER Package", "active", resultSet.getString("expiry")));

                                } else if (balance_type.equals("UL_AlwaysON_Ultra Status")) {
                                    int balance = resultSet.getInt("balance");
                                    if (balance >= 1)
                                        unlimitedBalance_types.add(new Unlimited_Balance_Types("AlwaysON ULTRA Package", "active", resultSet.getString("expiry")));

                                } else if (balance_type.equals("UL_AlwaysON_Starter Status")) {
                                    int balance = resultSet.getInt("balance");
                                    if (balance >= 1)
                                        unlimitedBalance_types.add(new Unlimited_Balance_Types("AlwaysON STARTER Package", "active", resultSet.getString("expiry")));

                                } else if (balance_type.equals("UL_AlwaysON_Lite Status")) {
                                    int balance = resultSet.getInt("balance");
                                    if (balance >= 1)
                                        unlimitedBalance_types.add(new Unlimited_Balance_Types("AlwaysON LITE Package", "active", resultSet.getString("expiry")));

                                } else if (balance_type.equals("UL_AlwaysON_Streamer Status")) {
                                    int balance = resultSet.getInt("balance");
                                    if (balance >= 1)
                                        unlimitedBalance_types.add(new Unlimited_Balance_Types("AlwaysON STREAMER Package", "active", resultSet.getString("expiry")));
                                } else if (balance_type.equals("Taxify Status")) {
                                    int balance = resultSet.getInt("balance");
                                    if (balance >= 1)
                                        unlimitedBalance_types.add(new Unlimited_Balance_Types("RideON Package", "active", resultSet.getString("expiry")));
                                }else if (balance_type.contains("Staff_AlwaysON")){
                                    int balance = resultSet.getInt("balance");
                                    if (balance >= 1)
                                        unlimitedBalance_types.add(new Unlimited_Balance_Types("Staff AlwaysON Package", "active", null));
                                }
                                else if (balance_type.equals("Ten4Ten Data")) {
                                    double balance = resultSet.getDouble("balance");
                                    String expiry = resultSet.getString("expiry");
                                    dataBalance_types.add(new Data_Balance_Type(WEEKEND_BUNDLE, balance / KB, expiry));

                                } else if (balance_type.equals("General Cash")) {
                                    no_cash = false;
                                    dataBalance_types.add(new Data_Balance_Type(CASH_WALLET, resultSet.getDouble("balance") / 100.0, null));

                                } else {
                                    double balance = resultSet.getDouble("balance");

                                    String expiry1 = resultSet.getString("expiry");
                                    String expiry = expiry1.equals("01-01-1970 00:01:00") ? "" : expiry1;
                                    dataBalance_types.add(new Data_Balance_Type(balance_type, balance / KB, expiry.equals("") ? null : expiry));
                                }
                            }


                            if (empty) {
                                sucessResult.setStatus_code("0");
                                sucessResult.setSubscriberNumber(callingMSISDN);
                                dataBalance_types.add(new Data_Balance_Type(CASH_WALLET, 0, null));
                                dataBalance_types.add(new Data_Balance_Type(DATA_WALLET, 0, null));
                                sucessResult.setDataBalance_types(dataBalance_types);
                                sucessResult.setStatus_description("Success");
                                return sucessResult;
                            }

                            if (no_cash) {
                                dataBalance_types.add(new Data_Balance_Type(CASH_WALLET, 0, null));
                            }


                            if (!no_sub_data) {

                                //String sub_data_expiry1 = sub_data_expiry.equals("01-01-1970 00:01:00") ? "" : sub_data_expiry;
                                String sub_data_expiry1 = surfplusCountExpiry.equals("01-01-1970 00:01:00") ? "" : surfplusCountExpiry;
                                dataBalance_types.add(new Data_Balance_Type(DATA_WALLET, sub_data_balance / KB, sub_data_expiry1));
                            }


                            if (autorecovery && !no_maindata) {
                                String main_data_expiry1 = main_data_expiry.equals("01-01-1970 00:01:00") ? "" : main_data_expiry;
                                String dateformat = FormatExpiryDate.dateformate(main_data_expiry1);
                                dataBalance_types.add(new Data_Balance_Type(DATA_WALLET, main_data_balance / KB, dateformat.equals("") ? null : dateformat));
                            } else if (!no_maindata) {
                                //String main_data_expiry1 = main_data_expiry.equals("01-01-1970 00:01:00") ? "" : main_data_expiry;
                                String main_data_expiry1 = surfplusCountExpiry.equals("01-01-1970 00:01:00") ? "" : surfplusCountExpiry;
                                dataBalance_types.add(new Data_Balance_Type(DATA_WALLET, main_data_balance / KB, main_data_expiry1.equals("") ? null : main_data_expiry1));
                            } else {
                                dataBalance_types.add(new Data_Balance_Type(DATA_WALLET, 0, null));
                            }

                            sucessResult.setDataBalance_types(dataBalance_types);
                            sucessResult.setSubscriberNumber(callingMSISDN);
                            sucessResult.setUnlimited_balance_types(unlimitedBalance_types);
                            sucessResult.setStatus_code("0");
                            sucessResult.setStatus_description("Success");
                            return sucessResult;

                        } catch (SQLException e) {
                            errorResult.setStatus_code("2");
                            errorResult.setSubscriberNumber(callingMSISDN);
                            errorResult.setStatus_description("Application failure");
                            return errorResult;
                        } finally {
                            if (preparedStatement != null) preparedStatement.close();
                            if (resultSet != null) resultSet.close();

                        }
                    }

                    errorResult.setStatus_code("1");
                    errorResult.setSubscriberNumber(callingMSISDN);
                    errorResult.setStatus_description("Invalid Surfline Number :" + callingMSISDN);
                    return errorResult;


                }


            } finally {


                if (resultSet1 != null) resultSet1.close();
                if (preparedStatement1 != null) preparedStatement1.close();

            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            errorResult.setStatus_code("2");
            errorResult.setSubscriberNumber(callingMSISDN);
            errorResult.setStatus_description("Application failure");
            return errorResult;
        }

        errorResult.setStatus_code("2");
        errorResult.setSubscriberNumber(callingMSISDN);
        errorResult.setStatus_description("Application failure");
        return errorResult;
    }
}


