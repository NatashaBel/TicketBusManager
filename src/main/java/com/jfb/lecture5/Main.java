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

                if (busTicket.getStartDate() == null || busTicket.getStartDate().isEmpty()) {
                    validationResult = false;
                    startDate++;

                } else {
                    LocalDate currentDate = LocalDate.now();
                    int data = busTicket.getStartDate().compareTo(String.valueOf(currentDate));
                    if (data > 0) {
                        validationResult = false;
                        startDate++;
                    }
                }

                if (busTicket.getTicketType() == null || busTicket.getTicketType().isEmpty()) {
                    validationResult = false;
                    ticketType++;
                }

                switch (busTicket.getTicketType()) {
                    case "DAY", "WEEK", "YEAR":
                        break;
                    case "MONTH": {
                        if (!busTicket.getStartDate().isEmpty()) {
                            validationResult = false;
                            ticketType++;
                        }
                        break;
                    }
                    default:
                        validationResult = false;
                        ticketType++;
                }

                if (busTicket.getPrice() == null) {
                    validationResult = false;
                    price++;
                } else {
                    int i = Integer.parseInt(busTicket.getPrice());
                    if (i == 0 || i % 2 == 1) {
                        validationResult = false;
                        price++;
                    }
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

        if (startDate > ticketType && startDate > price) {
            System.out.println("Most popular violation = Start Data");
        } else {
            if (ticketType > price && ticketType > startDate) {
                System.out.println("Most popular violation = Ticket Type");
            } else {
                if (price > ticketType && price > startDate) {
                    System.out.println("Most popular violation = Price");
                }
            }
        }
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }
}