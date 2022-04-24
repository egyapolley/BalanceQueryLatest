package com.restservices;

import com.helpers.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Stream;

@Path("/")
public class CheckBalance {



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get_balances/{id}")
    public FinalResult checkBalance(@PathParam("id") String callingMsisdn) {

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(getBalanceResources.properties.getProperty("clientURL")).queryParam("subscriberNumber", callingMsisdn).queryParam("channel", "CHATAPP");
        AccountBalanceResult accountBalanceResult = webTarget.request(MediaType.APPLICATION_JSON).header("Authorization", getBalanceResources.properties.getProperty("authId"))
                .get(AccountBalanceResult.class);

        FinalResult finalResult = new FinalResult();

        int statusCode = accountBalanceResult.getStatus();
        finalResult.setStatus_code(Integer.toString(statusCode));
        finalResult.setSubscriberNumber(callingMsisdn);
        ArrayList<AccountBalanceItem> finalDataBalances = new ArrayList<>();
        if (statusCode == 0) {
            finalResult.setStatus_description("Success");

            ArrayList<AccountBalanceItem> cash_balances = accountBalanceResult.getAccount_balance().getCash_balance();
            finalDataBalances.addAll(cash_balances);

            ArrayList<AccountBalanceItem> data_balances = accountBalanceResult.getAccount_balance().getData_balance();
            finalDataBalances.addAll(data_balances);

            ArrayList<Data_Balance_Type> data_balance_types = new ArrayList<>();

            for (AccountBalanceItem balanceItem:finalDataBalances) {
                Data_Balance_Type data_balance_type = new Data_Balance_Type();
                data_balance_type.setName(balanceItem.getBalance_type().equalsIgnoreCase("Cash")?"Cash Balance":balanceItem.getBalance_type());

                double balanceValue = Double.parseDouble(balanceItem.getValue());
                if (balanceItem.getBalance_type().contains("Data")){
                    balanceValue = balanceValue/1024;
                }
                data_balance_type.setBalance(balanceValue);
                data_balance_type.setExpiry_date(data_balance_type.getBalance() <= 0 ?null:balanceItem.getExpiry_date());
                data_balance_types.add(data_balance_type);
            }

            ArrayList<AccountBalanceItem> unlimited_balances = accountBalanceResult.getAccount_balance().getUnlimited_balance();

            ArrayList<Unlimited_Balance_Types> unlimited_balance_types = new ArrayList<>();

            for (AccountBalanceItem balanceItem: unlimited_balances) {
                Unlimited_Balance_Types unlimitedBalanceType = new Unlimited_Balance_Types();
                unlimitedBalanceType.setName(balanceItem.getBalance_type());
                unlimitedBalanceType.setStatus(balanceItem.getValue().toLowerCase());
                unlimitedBalanceType.setExpiry(balanceItem.getExpiry_date());
                unlimited_balance_types.add(unlimitedBalanceType);
            }

            finalResult.setDataBalance_types(data_balance_types);
            finalResult.setUnlimited_balance_types(unlimited_balance_types);

        }else {
            finalResult.setStatus_description(accountBalanceResult.getMessage());
        }

        return finalResult;


    }
}
