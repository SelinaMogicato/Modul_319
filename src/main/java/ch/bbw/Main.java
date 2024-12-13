package ch.bbw;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            TicketMachine ticketMachine = new TicketMachine();
            String postalCode = "";

            System.out.println("--- Willkommen beim Ticketautomaten ---");

            while (true) {
                System.out.print("Bitte geben Sie eine Postleitzahl ein (8000, 3000, 1000, 8303, 8953): ");
                postalCode = scanner.nextLine();
                if (ticketMachine.selectDestination(postalCode)) {
                    break;
                }
            }

            System.out.print("Bitte wählen Sie einen Tickettyp (½ Preis, 1. Klasse, 2. Klasse): ");
            String ticketType = scanner.nextLine();

            System.out.print("Möchten Sie ein Retourbillett? (ja/nein): ");
            boolean isReturn = scanner.nextLine().equalsIgnoreCase("ja");

            ticketMachine.calculatePrice(ticketType, isReturn);

            while (!ticketMachine.isPaymentComplete()) {
                System.out.print("Bitte Münze einwerfen (Wert in CHF): ");
                int coin = scanner.nextInt();
                ticketMachine.insertCoin(coin);

                if (!ticketMachine.isPaymentComplete()) {
                    System.out.println("Noch zu zahlender Betrag: " + (ticketMachine.total - ticketMachine.balance) + " CHF.");
                }
            }

            ticketMachine.calculateChange();
            ticketMachine.printTicket(postalCode, ticketType, isReturn);

            System.out.print("Möchten Sie ein weiteres Ticket kaufen? (ja/nein): ");
            scanner.nextLine();
            String again = scanner.nextLine();
            if (!again.equalsIgnoreCase("ja")) {
                System.out.println("Vielen Dank und auf Wiedersehen!");
                break;
            }
        }

        scanner.close();
    }
}
