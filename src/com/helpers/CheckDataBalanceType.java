package com.helpers;

public class CheckDataBalanceType {

    private static final String BIG="big";
    private static final String SMALL="small";

    public static String checkBalanceType(String BalanceType)
    {
        String returnValue=BIG;
        switch (BalanceType){
            case "1GBSurfplus Data":
            case "1.5GBSurfplus Data":
            case "2GBSurfplus Data":
            case "3GBSurfplus Data":
            case "4.5GBSurfplus Data":
                returnValue=SMALL;
                break;

        }
        return returnValue;
    }
}
