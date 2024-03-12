package org.example.view;

import org.example.model.Flight;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Form1 extends JFrame implements ActionListener {
    private JButton displayButton;
    private JButton exportButton;
    private JButton sortButton; // Added sort button
    private JTextField searchField;
    private JLabel searchLabel;
    private JTable flightTable;
    private JScrollPane scrollPane;
    private List<Flight> allFlights;
    private boolean shouldSort = true; // Flag to determine if sorting is needed

    public Form1() {
        uI();
        initComponents();
    }

    public void uI() {
        setTitle("Flight Information");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(250, 10, 1200, 750);
        setBackground(Color.decode("#EBEBEB"));
        setLayout(null); // Use null layout for absolute positioning
    }

    public void initComponents() {
        searchLabel = new JLabel("Search by airline name or flight number:");
        searchLabel.setBounds(10, 10, 300, 30);
        add(searchLabel);

        searchField = new JTextField(20);
        searchField.setBounds(10, 50, 200, 30);
        add(searchField);

        displayButton = new JButton("Display Flights");
        displayButton.setBounds(220, 50, 150, 30);
        displayButton.addActionListener(this);
        add(displayButton);

        exportButton = new JButton("Export to CSV");
        exportButton.setBounds(380, 50, 150, 30);
        exportButton.addActionListener(this);
        add(exportButton);

        // Added sort button
        sortButton = new JButton("Sort");
        sortButton.setBounds(540, 50, 100, 30);
        sortButton.addActionListener(this);
//        add(sortButton);

        // Initialize the table with column names and all flights data
        String[] columnNames = {"Airline Name", "Flight Number", "Origin", "Destination", "Airfare", "Departure Time", "Arrival Time", "Available Seats"};
        allFlights = Arrays.asList(
                new Flight("Jetstar", 1126, "Melbourne", "Gold Coast", 100.0, "10:00", "12:00", 200),
                new Flight("Virgin", 456, "Adelaide", "Sydney", 150.0, "11:00", "13:00", 150),
                new Flight("Quantas", 789, "Hobart", "Melbourne", 200.0, "12:00", "14:00", 100)
        );
        Object[][] data = new Object[allFlights.size()][columnNames.length];
        for (int i = 0; i < allFlights.size(); i++) {
            Flight flight = allFlights.get(i);
            data[i] = new Object[]{flight.getAirlineName(), flight.getFlightNumber(), flight.getFlightOrigin(),
                    flight.getDestinationCities(), flight.getAirfare(), flight.getDepartureTime(), flight.getArrivalTime(),
                    flight.getAvailableSeats()};
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        flightTable = new JTable(model);
        flightTable.setRowHeight(25);
        flightTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        flightTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
        scrollPane = new JScrollPane(flightTable);
        scrollPane.setBounds(10, 100, 1160, 600); // Set bounds for the scroll pane
        getContentPane().add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == displayButton) {
            shouldSort = false; // Reset shouldSort to false when display button is clicked
            displayFlightInfo();
        } else if (e.getSource() == exportButton) {
            exportToCSV();
        } else if (e.getSource() == sortButton) {
            shouldSort = true; // Set shouldSort to true when sort button is clicked
            displayFlightInfo(); // Sort and display flights
        }
    }
    public void displayFlightInfo() {
        // Get the search term
        String searchTerm = searchField.getText().trim().toLowerCase();

        // Filter flights based on the search criteria
        List<Flight> filteredFlights = allFlights.stream()
                .filter(flight -> flight.getAirlineName().toLowerCase().contains(searchTerm)
                        || String.valueOf(flight.getFlightNumber()).contains(searchTerm))
                .collect(Collectors.toList());

        // Sort the filtered flights in descending order by airline name
        filteredFlights.sort(Comparator.comparing(Flight::getAirlineName, Comparator.reverseOrder()));

        if (filteredFlights.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No flights found for the given search criteria.", "No Results", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String[] columnNames = {"Airline Name", "Flight Number", "Origin", "Destination", "Airfare", "Departure Time", "Arrival Time", "Available Seats"};
            Object[][] data = new Object[filteredFlights.size()][columnNames.length];
            for (int i = 0; i < filteredFlights.size(); i++) {
                Flight flight = filteredFlights.get(i);
                data[i] = new Object[]{flight.getAirlineName(), flight.getFlightNumber(), flight.getFlightOrigin(),
                        flight.getDestinationCities(), flight.getAirfare(), flight.getDepartureTime(), flight.getArrivalTime(),
                        flight.getAvailableSeats()};
            }

            DefaultTableModel model = (DefaultTableModel) flightTable.getModel();
            model.setDataVector(data, columnNames);

            if (scrollPane != null) {
                getContentPane().remove(scrollPane);
            }

            scrollPane = new JScrollPane(flightTable);
            scrollPane.setBounds(10, 100, 1160, 600); // Set bounds for the scroll pane
            getContentPane().add(scrollPane);
            validate();
            repaint();
        }
    }
    public JButton getDisplayButton() {
        return displayButton;
    }

    public JButton getExportButton() {
        return exportButton;
    }

    public JTextField getSearchField() {
        return searchField;
    }
    public boolean isShouldSorted() {
        return shouldSort;
    }
    public JScrollPane getScrollPane() {
        return scrollPane;
    }
    public List<Flight> getAllFlights() {
        return allFlights;
    }
    public void setAllFlights(List<Flight> allFlights) {
        this.allFlights = allFlights;
    }


    public void exportToCSV() {
        String csvFileName = "flight_information.csv";
        try (FileWriter writer = new FileWriter(csvFileName)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Airline Name,Flight Number,Origin,Destination,Airfare,Departure Time,Arrival Time,Available Seats\n");
            for (Flight flight : allFlights) {
                sb.append(String.format("%s,%d,%s,%s,%.2f,%s,%s,%d\n", flight.getAirlineName(), flight.getFlightNumber(),
                        flight.getFlightOrigin(), flight.getDestinationCities(), flight.getAirfare(), flight.getDepartureTime(),
                        flight.getArrivalTime(), flight.getAvailableSeats()));
            }
            writer.write(sb.toString());
            JOptionPane.showMessageDialog(this, "Flight information exported to CSV successfully.", "Export Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error occurred while exporting flight information to CSV.", "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Form1().setVisible(true);
        });
    }


}
