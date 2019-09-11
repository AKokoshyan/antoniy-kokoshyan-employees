package employee.view;

import employee.model.Person;
import employee.util.EmployeeHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

import javax.swing.*;

public class EmployeeMainComponent extends JFrame {
    private static final String[] COLUMN = {"Employee ID #1","Employee ID #2","Project ID","Days worked"};
    private static String data[][]={ {"101","Amit","670000"}};
    private JButton exitButton = new JButton("Exit");
    private JButton textToBeRead = new JButton("Open text");
    private JTable employeePairResult = new JTable(data,COLUMN);
    private JScrollPane scrollPaneResult;
    protected FileChooser fileChooser;
    protected JTextField choosenFileField = new JTextField();;
    public static String chosenFilePath;
    public static File choosenFile;
    public static List<Person> employeesList = new ArrayList<>();


    public EmployeeMainComponent() {
        super("Employees pair works for longest period in a project");
        initFrame();
        initExitButton();
        initTextToBeRead();
        initChoosenFileField();
        initPairResultTable();
        chosenFilePath = "";
        this.scrollPaneResult = new JScrollPane(this.employeePairResult);
        this.add(choosenFileField);
        this.add(this.exitButton);
        this.add(this.textToBeRead);
        this.add(this.scrollPaneResult);
    }

    private void initPairResultTable() {
        this.employeePairResult.setBounds(100,40,100,60);

    }

    private void initExitButton() {
        this.exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.exitButton.setBounds(800, 400, 150, 20);
    }

    private void initChoosenFileField() {
        this.choosenFileField.setBounds(100, 100, 300, 20);
        this.choosenFileField.setEditable(false);
    }

    private void initFrame() {
        this.setSize(1000, 500);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocation(500, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initTextToBeRead() {
        this.textToBeRead.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                fileChooser = new FileChooser();
                fileChooser.actionPerformed(e);

                choosenFileField.setText(chosenFilePath);

                Map<Integer, Map<Integer,Integer>> personsWorkedToghether = EmployeeHelper.mapPersonColleagues(employeesList);
                AbstractMap.SimpleEntry<Integer, Map.Entry<Integer, Integer>> result = EmployeeHelper.getResult((HashMap<Integer, Map<Integer, Integer>>)personsWorkedToghether);
                System.out.println("Person with id " + result.getKey());
                System.out.print(" works with person " + result.getValue().getKey());
                System.out.print(" total " + result.getValue().getValue());

            }
        });

        this.textToBeRead.setBounds(800, 100, 150, 20);
    }

}
