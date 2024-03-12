package org.example.model;


public class Flight{
    // Instance variables
    private String airlineName;
    private int flightNumber;
    private String flightOrigin;
    private String destinationCities;
    private double airfare;

    // Extra variables
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;

    // Constructor to initialize the instance variables
    public Flight(String airlineName, int flightNumber, String flightOrigin, String destinationCities,
                  double airfare, String departureTime, String arrivalTime, int availableSeats) {
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        this.flightOrigin = flightOrigin;
        this.destinationCities = destinationCities;
        this.airfare = airfare;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
    }

    // Getters and Setters for all instance variables
    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightOrigin() {
        return flightOrigin;
    }

    public void setFlightOrigin(String flightOrigin) {
        this.flightOrigin = flightOrigin;
    }

    public String getDestinationCities() {
        return destinationCities;
    }

    public void setDestinationCities(String destinationCities) {
        this.destinationCities = destinationCities;
    }

    public double getAirfare() {
        return airfare;
    }

    public void setAirfare(double airfare) {
        this.airfare = airfare;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    // toString method to return a one-line description of the flight
    @Override
    public String toString() {
        return "Flight [airlineName=" + airlineName + ", flightNumber=" + flightNumber + ", flightOrigin=" + flightOrigin
                + ", destinationCities=" + destinationCities + ", airfare=" + airfare + ", departureTime="
                + departureTime + ", arrivalTime=" + arrivalTime + ", availableSeats=" + availableSeats + "]";
    }
}
