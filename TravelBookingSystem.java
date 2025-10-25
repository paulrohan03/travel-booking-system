import java.util.*;

class TravelOption {
    String id;
    String type; // Flight, Train, Bus
    String source;
    String destination;
    double price;

    public TravelOption(String id, String type, String source, String destination, double price) {
        this.id = id;
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s -> %s | $%.2f", id, type, source, destination, price);
    }
}

class Booking {
    String bookingId;
    String customerName;
    TravelOption travelOption;

    public Booking(String bookingId, String customerName, TravelOption travelOption) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.travelOption = travelOption;
    }

    @Override
    public String toString() {
        return String.format("BookingID: %s | Customer: %s | %s", bookingId, customerName, travelOption);
    }
}

public class TravelBookingSystem {
    private static List<TravelOption> options = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();
        System.out.println("Welcome to the Travel Booking System!");

        while (true) {
            System.out.println("\n1. View Travel Options");
            System.out.println("2. Book Travel");
            System.out.println("3. View My Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewTravelOptions();
                    break;
                case 2:
                    bookTravel();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the Travel Booking System!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void seedData() {
        options.add(new TravelOption("F101", "Flight", "Delhi", "Mumbai", 5000));
        options.add(new TravelOption("T201", "Train", "Delhi", "Kolkata", 1500));
        options.add(new TravelOption("B301", "Bus", "Delhi", "Agra", 500));
        options.add(new TravelOption("F102", "Flight", "Kolkata", "Chennai", 6000));
        options.add(new TravelOption("T202", "Train", "Mumbai", "Pune", 800));
    }

    private static void viewTravelOptions() {
        System.out.println("\nAvailable Travel Options:");
        for (TravelOption option : options) {
            System.out.println(option);
        }
    }

    private static void bookTravel() {
        viewTravelOptions();
        System.out.print("\nEnter Travel ID to book: ");
        String travelId = scanner.nextLine();
        TravelOption selected = null;
        for (TravelOption option : options) {
            if (option.id.equalsIgnoreCase(travelId)) {
                selected = option;
                break;
            }
        }
        if (selected == null) {
            System.out.println("Invalid Travel ID. Booking failed.");
            return;
        }

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        String bookingId = "BK" + (bookings.size() + 1);
        Booking newBooking = new Booking(bookingId, name, selected);
        bookings.add(newBooking);
        System.out.println("Booking successful!\n" + newBooking);
    }

    private static void viewBookings() {
        System.out.print("Enter your name to view bookings: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Booking booking : bookings) {
            if (booking.customerName.equalsIgnoreCase(name)) {
                System.out.println(booking);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bookings found for " + name);
        }
    }
}