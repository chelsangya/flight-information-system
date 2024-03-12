package org.example.console_view;

import org.example.model.Flight;

import java.util.Arrays;
import java.util.Scanner;

public class FlightTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of flights: ");
        int numFlights = scanner.nextInt();
        scanner.nextLine();
        Flight[] flights = new Flight[numFlights];

        // for loop to take user inputs
        for (int i = 0; i < numFlights; i++) {
            System.out.println("\nEnter details for Flight " + (i + 1) + ":");
            System.out.print("Airline Name: ");
            String airlineName = scanner.nextLine();

            System.out.print("Flight Number: ");
            int flightNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Flight Origin: ");
            String flightOrigin = scanner.nextLine();

            System.out.print("Destination Cities: ");
            String destinationCities = scanner.nextLine();

            System.out.print("Airfare: ");
            double airfare = scanner.nextDouble();
            scanner.nextLine(); 

            System.out.print("Departure Time: ");
            String departureTime = scanner.nextLine();

            System.out.print("Arrival Time: ");
            String arrivalTime = scanner.nextLine();

            System.out.print("Available Seats: ");
            int availableSeats = scanner.nextInt();
            scanner.nextLine(); 

            flights[i] = new Flight(airlineName, flightNumber, flightOrigin, destinationCities,
                                    airfare, departureTime, arrivalTime, availableSeats);
        }
       // Initial Flights
        System.out.println("Before sorting:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }

        Arrays.sort(flights, (a, b) -> Integer.compare(a.getFlightNumber(), b.getFlightNumber()));

        // Sorted Flights
        System.out.println("After sorting by flight number:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }

        scanner.close();
    }
}
