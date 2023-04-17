package employeeManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmployeeManagementGUI {
    private JFrame frame;
    private JTextField ageField;
    private JTextField sexField;
    private JTextField workTypeField;
    private JTextField degreeField;
    private JTextField enjoyField;
    private JTextField workDurationField;
    private JTextField contributionField;
    private JTextField mistakesField;

    private void login() {
        String username = JOptionPane.showInputDialog(frame, "Enter username:", "Login", JOptionPane.PLAIN_MESSAGE);
        String password = JOptionPane.showInputDialog(frame, "Enter password:", "Login", JOptionPane.PLAIN_MESSAGE);

        boolean loginSuccess = authenticate(username, password);
        if (loginSuccess) {
            new EmployeeManagementGUI(); // If login is successful, show EmployeeManagementGUI
        } else {
            // Show error message if login is unsuccessful
            JOptionPane.showMessageDialog(frame, "Login failed. Exiting the program.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private boolean authenticate(String username, String password) {
        // Replace this with your own authentication logic
        // For example, you can compare the username and password with a pre-defined list of valid credentials
        if ("employee".equals(username) && "password123".equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public EmployeeManagementGUI() {
        frame = new JFrame("Employee Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        panel.add(new JLabel("Sex:"));
        sexField = new JTextField();
        panel.add(sexField);

        panel.add(new JLabel("Type of Work:"));
        workTypeField = new JTextField();
        panel.add(workTypeField);

        panel.add(new JLabel("Degree of Learning:"));
        degreeField = new JTextField();
        panel.add(degreeField);

        panel.add(new JLabel("Enjoy Assigned Role:"));
        enjoyField = new JTextField();
        panel.add(enjoyField);

        panel.add(new JLabel("Work Duration:"));
        workDurationField = new JTextField();
        panel.add(workDurationField);

        panel.add(new JLabel("Contributions to Organization:"));
        contributionField = new JTextField();
        panel.add(contributionField);

        panel.add(new JLabel("Mistakes Made on the Job:"));
        mistakesField = new JTextField();
        panel.add(mistakesField);

        JButton saveButton = new JButton("Submit");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToExcel();
            }
        });
        panel.add(saveButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void saveToExcel() {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Employee Data Form");

            // Create row and cells for employee information
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Age");
            cell = row.createCell(1);
            cell.setCellValue("Sex");
            cell = row.createCell(2);
            cell.setCellValue("Type of Work");
            cell = row.createCell(3);
            cell.setCellValue("Degree of Learning");
            cell = row.createCell(4);
            cell.setCellValue("Enjoy Assigned Role");
            cell = row.createCell(5);
            cell.setCellValue("Work Duration");
            cell = row.createCell(6);
            cell.setCellValue("Contributions to Organization");
            cell = row.createCell(7);
            cell.setCellValue("Mistakes Made on the Job");

            // Get data from GUI fields
            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellValue(ageField.getText());
            cell = row.createCell(1);
            cell.setCellValue(sexField.getText());
            cell = row.createCell(2);
            cell.setCellValue(workTypeField.getText());
            cell = row.createCell(3);
            cell            .setCellValue(degreeField.getText());
            cell = row.createCell(4);
            cell.setCellValue(enjoyField.getText());
            cell = row.createCell(5);
            cell.setCellValue(workDurationField.getText());
            cell = row.createCell(6);
            cell.setCellValue(contributionField.getText());
            cell = row.createCell(7);
            cell.setCellValue(mistakesField.getText());

            // Write data to Excel file
            String fileName = "EmployeeData.xlsx";
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            JOptionPane.showMessageDialog(frame, "Data saved to Excel successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while saving data to Excel.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EmployeeLoginGUI login = new EmployeeLoginGUI();
        boolean loginSuccess = login.login("employee", "password123"); // Call login() method to get login status
        if (loginSuccess) {
            new EmployeeManagementGUI(); // If login is successful, show EmployeeManagementGUI
        } else {
            // Show error message if login is unsuccessful
            JOptionPane.showMessageDialog(null, "Login failed. Exiting the program.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}

