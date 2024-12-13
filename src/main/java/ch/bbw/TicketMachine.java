package ch.bbw;

class TicketMachine {
    public int balance;
    public int total;

    public TicketMachine() {
        balance = 0;
        total = 0;
    }

    public boolean selectDestination(String postalCode) {
        switch (postalCode) {
            case "8000":
                System.out.println("Ziel gefunden: Zürich");
                total = 10;
                return true;
            case "3000":
                System.out.println("Ziel gefunden: Bern");
                total = 18;
                return true;
            case "1000":
                System.out.println("Ziel gefunden: Lausanne");
                total = 20;
                return true;
            case "8303":
                System.out.println("Ziel gefunden: Bassersdorf");
                total = 12;
                return true;
            case "8953":
                System.out.println("Ziel gefunden: Dietikon");
                total = 14;
                return true;
            default:
                System.out.println("Postleitzahl nicht vorhanden. Bitte erneut versuchen.");
                return false;
        }
    }

    public void calculatePrice(String ticketType, boolean isReturn) {
        if (ticketType.equals("1. Klasse")) {
            total += total / 2;
        } else if (ticketType.equals("½ Preis")) {
            total /= 2;
        }

        if (isReturn) {
            total *= 2;
        }

        System.out.println("Der Ticketpreis beträgt: " + total + " CHF.");
    }

    public void insertCoin(int value) {
        balance += value;
        System.out.println("Münze eingeworfen: " + value + " CHF. Aktuelles Guthaben: " + balance + " CHF.");
    }

    public boolean isPaymentComplete() {
        return balance >= total;
    }

    public void calculateChange() {
        int change = balance - total;
        if (change > 0) {
            System.out.println("Retourgeld: " + change + " CHF.");
            int[] coins = {5, 2, 1};
            for (int coin : coins) {
                int count = change / coin;
                if (count > 0) {
                    System.out.println(count + " x " + coin + " CHF");
                    change %= coin;
                }
            }
        }
    }

    public void printTicket(String postalCode, String ticketType, boolean isReturn) {
        System.out.println("\n--- Ticket ---");
        System.out.println("Ziel: " + getDestinationName(postalCode));
        System.out.println("Klasse: " + ticketType);
        System.out.println("Retour: " + (isReturn ? "Ja" : "Nein"));
        System.out.println("Preis: " + total + " CHF");
        System.out.println("Vielen Dank für Ihre Fahrt mit uns!");
    }

    private String getDestinationName(String postalCode) {
        return switch (postalCode) {
            case "8000" -> "Zürich";
            case "3000" -> "Bern";
            case "1000" -> "Lausanne";
            case "8303" -> "Bassersdorf";
            case "8953" -> "Dietikon";
            default -> "Unbekannt";
        };
    }
}