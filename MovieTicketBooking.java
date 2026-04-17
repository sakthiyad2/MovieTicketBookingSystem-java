import java.util.*;

class Show {
    int showId;
    String movieName;
    String time;

    Show(int showId, String movieName, String time) {
        this.showId = showId;
        this.movieName = movieName;
        this.time = time;
    }

    void displayShow() {
        System.out.println(showId + " - " + movieName + " (" + time + ")");
    }
}

class Customer {
    String name;
    int showId;
    int seat;

    Customer(String name, int showId, int seat) {
        this.name = name;
        this.showId = showId;
        this.seat = seat;
    }

    void displayCustomer() {
        System.out.println("Customer: " + name + " | Show ID: " + showId + " | Seat: " + seat);
    }
}

class MovieTicketSystem {

    ArrayList<Show> shows = new ArrayList<>();
    ArrayList<Customer> bookings = new ArrayList<>();
    int totalSeats = 10;

    MovieTicketSystem() {
        shows.add(new Show(1, "Avengers", "10:00 AM"));
        shows.add(new Show(2, "Spider-Man", "1:00 PM"));
        shows.add(new Show(3, "Batman", "6:00 PM"));
    }

    void showMovies() {
        System.out.println("\nAvailable Shows:");
        for (Show s : shows) {
            s.displayShow();
        }
    }

    void showSeats(int showId) {
        System.out.println("\nSeat Status for Show " + showId + ":");

        for (int i = 1; i <= totalSeats; i++) {
            boolean booked = false;

            for (Customer c : bookings) {
                if (c.showId == showId && c.seat == i) {
                    booked = true;
                    break;
                }
            }

            if (booked)
                System.out.println("Seat " + i + " : Booked");
            else
                System.out.println("Seat " + i + " : Available");
        }
    }

    void bookTicket(String name, int showId, int seat) throws Exception {

        if (seat < 1 || seat > totalSeats) {
            throw new Exception("Invalid seat number");
        }

        for (Customer c : bookings) {
            if (c.showId == showId && c.seat == seat) {
                throw new Exception("Seat already booked");
            }
        }

        bookings.add(new Customer(name, showId, seat));
        System.out.println("Ticket booked successfully!");
    }

    void showCustomers() {

        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }

        System.out.println("\nCustomer Bookings:");
        for (Customer c : bookings) {
            c.displayCustomer();
        }
    }
}

public class MovieTicketBooking {

    public static void main(String[] args) {

        Scanner S = new Scanner(System.in);
        MovieTicketSystem system = new MovieTicketSystem();

        while (true) {

            System.out.println("\n1. Show Movies");
            System.out.println("2. Show Seats");
            System.out.println("3. Book Ticket");
            System.out.println("4. Show Customers");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");

            try {

                int choice = S.nextInt();

                switch (choice) {

                    case 1:
                        system.showMovies();
                        break;

                    case 2:
                        System.out.print("Enter Show ID: ");
                        int sid = S.nextInt();
                        system.showSeats(sid);
                        break;

                    case 3:
                        System.out.print("Enter Customer Name: ");
                        String name = S.next();

                        System.out.print("Enter Show ID: ");
                        int showId = S.nextInt();

                        System.out.print("Enter Seat Number: ");
                        int seat = S.nextInt();

                        system.bookTicket(name, showId, seat);
                        break;

                    case 4:
                        system.showCustomers();
                        break;

                    case 5:
                        System.out.println("Program Ended.");
                        S.close();
                        return;

                    default:
                        System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                S.nextLine();
            }
        }
    }
}