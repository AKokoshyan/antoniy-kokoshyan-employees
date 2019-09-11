package employee.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;

import employee.exceptions.EmployeeValidationException;
import employee.model.Person;
import employee.util.EmployeeHelper;
import employee.view.EmployeeMainComponent;

public class FileHandler {


    public static void extractEntriesInList(File inputFile) throws EmployeeValidationException, IOException {
        EmployeeHelper.validate(inputFile);

        try (Scanner sc = new Scanner(inputFile)) {

            while (sc.hasNextLine()) {
                String[] currentEntry = sc.nextLine().split(", ");
                System.out.println(currentEntry[0] + " " + currentEntry[1] + " " + currentEntry[2] + " " + currentEntry[3]);
                EmployeeHelper.validate(currentEntry);

                String currentEmployeeId = currentEntry[0];
                String currentEmployeeProjectId = currentEntry[1];
                LocalDate currentEmployeeStartDate = EmployeeHelper.getDateFromFormat(currentEntry[2]);
                LocalDate currentEmployeeEndDate = EmployeeHelper.getDateFromFormat(currentEntry[3]);

                Person currentEmployee = new Person(currentEmployeeId, currentEmployeeProjectId, currentEmployeeStartDate, currentEmployeeEndDate);

                EmployeeMainComponent.employeesList.add(currentEmployee);
                System.out.println(currentEmployee);

            }
            System.out.println(EmployeeMainComponent.employeesList);
        }

    }
}
