package com.epax.framework.utilities;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringFunctions {

    List<String> lstString=new ArrayList<>();
    public static boolean isListSorted(List<String> actualList, boolean isListAscending){
    	 List<String> sortedList ;
         List<String> ignoreCase;
         ignoreCase = actualList.stream().map(String::toLowerCase).collect(Collectors.toList());
         if(isListAscending!=true) {
         	sortedList = ignoreCase.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
         }
         else{
         	sortedList = ignoreCase.stream().sorted().collect(Collectors.toList());
             
         }
            if(ignoreCase.equals(sortedList)){
                return true;
            }
            else{
         	   System.out.println("Your Input List is :"+actualList + "Not Equal To Expected List :"+sortedList);
                return false;
            }
    }
    public static List<String> GetItemValue(List<String> amount){
    	Pattern p = Pattern.compile("[^A-Za-z0-9]");
    	List<String> amountValues = new ArrayList<>();
    	for(int index = 0; index < amount.size(); index++)
        {
	        String value = amount.get(index);
    		Matcher m = p.matcher(value);
            m.find();
            String characterToSplit = String.valueOf(value.charAt(m.start()));
            amountValues.add(value.split(Pattern.quote(characterToSplit))[1]);
        }
        return amountValues;
    }

    public static String GetItemValue(String amount){
    	Pattern p = Pattern.compile("[^A-Za-z0-9]");
    	//List<String> amountValues = new ArrayList<>();
    	    Matcher m = p.matcher(amount);
            m.find();
            String characterToSplit = String.valueOf(amount.charAt(m.start()));
            String refinedAmount = amount.split(Pattern.quote(characterToSplit))[1];
            return refinedAmount;
    }
    
    
    public static String verifyListOrderForDoubleValues(List<String> actualList)
    {
        String sortingOrder = "No Order";
        boolean flag = false;
        if (actualList.size() > 1) {
            for (int index = 0; index < actualList.size() - 1; index++) {
                if (Double.valueOf(actualList.get(index)) <= Double.valueOf(actualList.get(index+1))) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
            if(flag)
            {
                sortingOrder  = "Ascending Order";
            }
        }
        
        else{
            for (int index = 0; index < actualList.size() - 1; index++) {
                if (Double.valueOf(actualList.get(index)) >= Double.valueOf(actualList.get(index+1))) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
            if(flag)
            {
                sortingOrder = "Descending Order";
            }
        }
        return sortingOrder;
    }
    
    
}
