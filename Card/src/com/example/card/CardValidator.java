package com.example.card;

public class CardValidator {
	public static boolean validate(String creditCard) {
	       
    	
        int sum = 0;
        int length = creditCard.length();
        for (int i = 0; i < creditCard.length(); i++) {
            if (0 == (i % 2)) {
                sum += creditCard.charAt(length - i - 1) - '0';
            } else {
                sum += sumDigits((creditCard.charAt(length - i - 1) - '0') * 2);
            }
        }
        return 0 == (sum % 10);
    }

    private static int sumDigits(int i) {
        return (i % 10) + (i / 10);
    }

}
