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
    private final JTextArea txtEmployeeData;

    public EmployerViewGUI() {
        JFrame frame = new JFrame("Employee Data");
        txtEmployeeData = new JTextArea(10, 30);
        txtEmployeeData.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtEmployeeData);
        JButton btnRefresh = new JButton("Refresh Data");

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
        StringBuilder data = new StringBuilder("Employee Data:\n");
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
                data.append("Age: ").append(age).append("\n");
                data.append("Sex: ").append(sex).append("\n");
                data.append("Type of Work: ").append(workType).append("\n");
                data.append("Degree of Learning: ").append(degree).append("\n");
                data.append("Enjoy Assigned Roles: ").append(enjoyWork).append("\n");
                data.append("Work Duration: ").append(workDuration).append("\n");
                data.append("Contributions: ").append(contributions).append("\n");
                data.append("Mistakes: ").append(mistakes).append("\n");
                data.append("\n");
            }

            workbook.close();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return data.toString();
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
