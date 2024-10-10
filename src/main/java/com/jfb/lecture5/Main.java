package com.jfb.lecture5;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfb.lecture5.model.BusTicket;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        int x = 0;
        int ticketType = 0;
        int price = 0;
        int startDate = 0;
        boolean validationResult;
        int validTicket = 0;
        int total = 0;

        do {
            String input = getInput();
            validationResult = true;

            BusTicket busTicket = null;
            try {
                busTicket = new ObjectMapper().readValue(input, BusTicket.class);

                if (validateStartDate(busTicket.getStartDate()) == false) {
                    validationResult = false;
                    startDate++;
                }

                if (validateTicketType(busTicket.getTicketType(), busTicket.getStartDate()) == false) {
                    validationResult = false;
                    ticketType++;
                }

                if (validatePrice(busTicket.getPrice()) == false) {
                    validationResult = false;
                    price++;
                }

                if (validationResult) {
                    validTicket++;
                } else {
                    System.out.println("Bus Ticket is invalid");
                }

                System.out.println(busTicket);
            } catch (JsonParseException e) {
                System.out.println("Unexpected input date");
            }

            total++;
            x++;

        } while (x < 5);

        System.out.println("Total = " + total);
        System.out.println("Valid = " + validTicket);

        if (startDate >= ticketType && startDate >= price) {
            System.out.println("Most popular violation = Start Data");
        } else {
            if (ticketType >= price && ticketType >= startDate) {
                System.out.println("Most popular violation = Ticket Type");
            } else {
                if (price >= ticketType && price >= startDate) {
                    System.out.println("Most popular violation = Price");
                }
            }
        }
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }

    private static boolean validateStartDate(String startDate) {
        if (startDate == null || startDate.isEmpty()) {
            return false;
        } else {
            LocalDate currentDate = LocalDate.now();
            int data = startDate.compareTo(String.valueOf(currentDate));
            if (data > 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    private static boolean validateTicketType (String ticketType, String startDate) {
        if (ticketType == null || ticketType.isEmpty()) {
            return  false;
        }

        switch (ticketType) {
            case "DAY", "WEEK", "YEAR":
                return true;
            case "MONTH": {
                if (!startDate.isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            }
            default: return false;
        }
    }

    private static boolean validatePrice (String price){
        if (price == null) {
            return false;
        } else {
            int i = Integer.parseInt(price);
            if (i == 0 || i % 2 == 1) {
                return false;
            } else {
                return true;
            }
        }
    }

}