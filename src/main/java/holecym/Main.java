package holecym;

import holecym.gui.GuiForm;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Michal on 5. 1. 2017.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        Properties properties = System.getProperties();
        properties.setProperty("db.connection", "jdbc:sqlserver://HP-PROBOOK\\SQLEXPRESS");
        properties.setProperty("db.username", "mholecy");
        properties.setProperty("db.password", "kleopatra");

        SwingUtilities.invokeLater(() -> {
            final GuiForm guiForm = new GuiForm();
            guiForm.setVisible(true);
            guiForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            guiForm.pack();
            guiForm.setLocationRelativeTo(null);
            guiForm.setVisible(true);
        });
    }
}
