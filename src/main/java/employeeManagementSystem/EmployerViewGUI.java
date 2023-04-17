package employeeManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmployerViewGUI {
    private JFrame frame;
    private JTextArea txtEmployeeData;

    public EmployerViewGUI() {
        frame = new JFrame("Employee Data");
        txtEmployeeData = new JTextArea(10, 30);
        txtEmployeeData.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtEmployeeData);
        JButton btnRefresh = new JButton("Refresh");

        // Set layout manager
        frame.setLayout(new BorderLayout());

        // Add components to frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(btnRefresh, BorderLayout.SOUTH);

        // Add action listener
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve and display employee data
                String employeeData = getEmployeeData(); // Retrieve data from Excel sheet
                txtEmployeeData.setText(employeeData);
            }
        });

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private String getEmployeeData() {
        String data = "Employee Data:\n";
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/EmployeeData.xlsx");
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0); // Assuming employee data is in the first sheet

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from row 1 to skip header row
                Row row = sheet.getRow(i);
                String age = row.getCell(0).toString();
                String sex = row.getCell(1).toString();
                String workType = row.getCell(2).toString();
                String degree = row.getCell(3).toString();
                String enjoyWork = row.getCell(4).toString();
                String workDuration = row.getCell(5).toString();
                String contributions = row.getCell(6).toString();
                String mistakes = row.getCell(7).toString();

                // Append employee data to the string
                data += "Age: " + age + "\n";
                data += "Sex: " + sex + "\n";
                data += "Type of Work: " + workType + "\n";
                data += "Degree of Learning: " + degree + "\n";
                data += "Enjoy Assigned Roles: " + enjoyWork + "\n";
                data += "Work Duration: " + workDuration + "\n";
                data += "Contributions: " + contributions + "\n";
                data += "Mistakes: " + mistakes + "\n";
                data += "\n";
            }

            workbook.close();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployerViewGUI();
            }
        });
    }
}
