import org.example.model.Flight;
import org.example.view.Form1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Form1Test {

    private Form1 form;

    @BeforeEach
    public void setUp() {
        form = new Form1();
    }

    @Test
    public void testUIComponents() {
        JButton displayButton = form.getDisplayButton();
        JButton exportButton = form.getExportButton();
        JTextField searchField = form.getSearchField();

        assertNotNull(displayButton);
        assertNotNull(exportButton);
        assertNotNull(searchField);

        assertEquals("Display Flights", displayButton.getText());
        assertEquals("Export to CSV", exportButton.getText());
        assertEquals(20, searchField.getColumns());
    }

    @Test
    public void testInitialState() {
        assertTrue(form.isShouldSorted());
    }

    @Test
    public void testDisplayFlightInfoNoMatches() {
        form.getSearchField().setText("Nonexistent Airline");
        form.displayFlightInfo();

        // Assert that a message dialog is shown indicating no matches
        Window[] windows = Window.getWindows();
        boolean dialogFound = false;
        for (Window window : windows) {
            if (window instanceof JDialog && ((JDialog) window).getTitle().equals("No Results")) {
                dialogFound = true;
                break;
            }
        }
        assertTrue(dialogFound);
    }

    @Test
    public void testDisplayFlightInfoWithMatches() {
        form.getSearchField().setText("Jetstar");
        form.displayFlightInfo();

        // Assert that the table with matching flights is displayed
        JScrollPane scrollPane = form.getScrollPane();
        assertNotNull(scrollPane);
        JTable table = (JTable) scrollPane.getViewport().getView();
        assertNotNull(table);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Flight> flights = form.getAllFlights();
        assertEquals(flights.get(0).getAirlineName(), model.getValueAt(0, 0));
    }

    @Test
    public void testExportToCSV() {
        // Set up some sample flights
        List<Flight> flights = List.of(
                new Flight("Jetstar", 1126, "Melbourne", "Gold Coast", 100.0, "10:00", "12:00", 200),
                new Flight("Virgin", 456, "Adelaide", "Sydney", 150.0, "11:00", "13:00", 150),
                new Flight("Quantas", 789, "Hobart", "Melbourne", 200.0, "12:00", "14:00", 100)
        );
        form.setAllFlights(flights);

        // Export to CSV
        form.exportToCSV();

        // Assert that a dialog indicating successful export is shown
        Window[] windows = Window.getWindows();
        boolean dialogFound = false;
        for (Window window : windows) {
            if (window instanceof JDialog && ((JDialog) window).getTitle().equals("Export Successful")) {
                dialogFound = true;
                break;
            }
        }
        assertTrue(dialogFound);
    }
}
