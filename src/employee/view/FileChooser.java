package employee.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import employee.controller.FileHandler;
import employee.exceptions.EmployeeValidationException;

public class FileChooser extends JFileChooser implements ActionListener {

    public FileChooser() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        int i = fc.showOpenDialog(this);
        if (i == JFileChooser.APPROVE_OPTION) {
            EmployeeMainComponent.choosenFile = fc.getSelectedFile();
            EmployeeMainComponent.chosenFilePath = new String(EmployeeMainComponent.choosenFile.getAbsolutePath());
            try {
                System.out.println(EmployeeMainComponent.choosenFile.getAbsolutePath());
                FileHandler.extractEntriesInList(EmployeeMainComponent.choosenFile);
            } catch (IOException ei) {
                String message = ei.getMessage();
                JOptionPane.showMessageDialog(new JFrame(), message);
            } catch (EmployeeValidationException ei) {
                String message = ei.getMessage();
                JOptionPane.showMessageDialog(new JFrame(), message);
            }
        }
    }

}
