package employee.util;

import employee.exceptions.EmployeeValidationException;
import employee.model.Person;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.Map.Entry;


public class EmployeeHelper {
    //-? – this part identifies if the given number is negative, the dash “–” searches for dash literally and the question mark “?” marks its presence as an optional one
    //\d+ – this searches for one or more digits
    //(\.\d+)? – this part of regex is to identify float numbers. Here we're searching for one or more digits followed by a period. The question mark, in the end, signifies that this complete group is optional
    public static final String DIGIT_REGEX = "-?\\d+(\\.\\d+)?";
    public static final String[] FORMATS = {
            "dd.MM.yyyy", "d/MM/yyyy",
            "yyyy-MM-dd", "yyyy-MM-dd",
            "MM/dd/yyyy", "yyyy:MM:dd",
            "yyyyMMdd",};



    private static Integer getDaysWorkedToghether(Person firstPerson, Person secondPerson) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (firstPerson.getFrom().isBefore(secondPerson.getFrom())) {
            startDate = secondPerson.getFrom();
        }else {
            startDate = firstPerson.getFrom();
        }
        if (firstPerson.getTo().isBefore(secondPerson.getTo())) {
            endDate = secondPerson.getTo();
        }else {
            endDate = firstPerson.getTo();
        }

        Period periodWorkedToghether = Period.between(startDate, endDate);
        Integer daysWorkedToghether = periodWorkedToghether.getDays();

        return daysWorkedToghether;
    }

    public static AbstractMap.SimpleEntry<Integer, Map.Entry<Integer, Integer>> getResult(HashMap<Integer,Map<Integer,Integer>> personsToDays) {

       AbstractMap.SimpleEntry<Integer, Map.Entry<Integer, Integer>> result = personsToDays.entrySet().stream()
                .map(p -> {
                    return new AbstractMap.SimpleEntry<Integer, Map.Entry<Integer, Integer>>(p.getKey(), p.getValue().entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get());
                })
                .max(Comparator.comparing(p -> p.getValue().getValue())).get();
        return result;
    }
    public static Map<Integer, Map<Integer,Integer>> mapPersonColleagues(List<Person> persons) {
        Map<Integer, Map<Integer, Integer>> result = new HashMap<>();
        for (Person firstPerson : persons) {
            for (Person secondPerson : persons) {
                if (!firstPerson.getId().equals(secondPerson.getId()) && firstPerson.getProjectId().equals(secondPerson.getProjectId())) {
                    Integer daysWorkedToghether = getDaysWorkedToghether(firstPerson, secondPerson);
                    result.put(firstPerson.getId(), new HashMap<>(secondPerson.getId(), daysWorkedToghether));
                }
            }
        }
        return result;
    }
    public static void validate(File inputFile) throws IOException {
        if (inputFile == null || !inputFile.exists()) {
            throw new IOException();
        }
        String fileExtention = inputFile.getName();
        if (!fileExtention.endsWith(".txt")) {
            throw new IOException("The file must be with '.txt' extention. " );
        }
    }

    public static void validate(String[] currentEntry) throws EmployeeValidationException {
        if (currentEntry.length != 4) {
            throw new EmployeeValidationException("The entry must have 4 values !");
        }

        if (!currentEntry[0].matches(DIGIT_REGEX)) {
            throw new EmployeeValidationException("The employeeId must be a digit !");
        }

        if (!currentEntry[1].matches(DIGIT_REGEX)) {
            throw new EmployeeValidationException("The projectId must be a digit !");
        }
        if (!isValidDateFormat(currentEntry[2])) {
            throw new EmployeeValidationException("The supplied String is not valid Date format !");
        }
        if (currentEntry[3].toLowerCase().equals("null")) {
            return;
        }
        if (!isValidDateFormat(currentEntry[3])) {
            throw new EmployeeValidationException("The supplied String is not valid Date format !");
        }
    }

    private static boolean isValidDateFormat(String date) {
        boolean isValid = true;
        if (date != null) {

            for (String format : FORMATS) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                try {
                    LocalDate.parse(date, formatter);
                    isValid = true;
                    break;
                } catch (DateTimeParseException e) {
                    isValid = false;
                }
            }
        }

        return isValid;
    }

    public static LocalDate getDateFromFormat(String inputDate) {
        LocalDate dateOne = null;
        if (inputDate != null) {
            if (inputDate.toLowerCase().equals("null")) {
                return dateOne = LocalDate.now();
            }
            for (String format : FORMATS) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                try {
                    dateOne = LocalDate.parse(inputDate, formatter);
                    System.out.println("Printing the value of " + format);
                } catch (DateTimeParseException e) {

                }
                if (dateOne != null) {
                    break;
                }
            }
        }
        return dateOne;
    }
}
