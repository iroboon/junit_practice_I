package junit.practice.I;

import java.time.LocalDate;
import java.time.Period;

class ValidationMethods {

    public boolean isBirthdateWithinAgeRange(LocalDate givenDate, int minYears, int maxYears) {
        boolean notLessThanMinYears = (Period.between(givenDate, LocalDate.now()).getYears()) >= minYears;
        boolean notLessThanMaxYears = (Period.between(givenDate, LocalDate.now()).getYears()) < maxYears;
        // System.out.println(Period.between(givenDate, LocalDate.now()).getYears());
        return (notLessThanMinYears && notLessThanMaxYears);
    }

    public boolean isBirthdateWithinAgeRange(String dateString, int minYears, int maxYears) {
        boolean isDateWithinAcceptableYears = false;
        if (isValidDateString(dateString) == true) {
            LocalDate theDate = LocalDate.parse(dateString);
            boolean notLessThanMinYears = (Period.between(theDate, LocalDate.now()).getYears()) >= minYears;
            boolean notLessThanMaxYears = (Period.between(theDate, LocalDate.now()).getYears()) < maxYears;
            isDateWithinAcceptableYears = (notLessThanMinYears && notLessThanMaxYears);
        }
        return isDateWithinAcceptableYears;
    }

    public boolean isValidSpecialString(String str, String regex) {
        return str.matches(regex);
    }

    public boolean isValidIntegerInput(Integer input) {
        return (input instanceof Integer);
    }

    public static boolean isValidFloatingPointInput(Double input) {
        return (input instanceof Double);
    }

    public boolean isLettersOnly(String str) {
        return ((str != null) && (!str.isEmpty()) && (str.matches("[a-zA-Z]*$")));
    }

    public boolean isPositiveNumeric(String numStr) {
        return numStr.matches("\\d+(\\.\\d+)?");
    }

    // validates both positive & negative numbers
    public boolean isNumeric(String numStr) {
        return numStr.matches("-?\\d+(\\.\\d+)?");
    }

    // expected date format yyyy-mm-dd e.g. '1970-01-01
    public boolean isValidDateString(String dateString) {
        String[] dateStringToArray = dateString.trim().split("-");
        // date format yyyy-mm-dd means 3 params are expected -> yyyy, mm & dd
        boolean isValidParamsCount = dateStringToArray.length == 3;
        // conditions for year(yyyy) param:
        // (i) yyyy must not start with 0 (ii) yyyy must be 4 digits
        // regex := {year starts with 1 or 2 && following digits do not exceed 3}
        boolean isValidYear = dateStringToArray[0].matches("^[1-2][\\d*]{3}$");
        // conditions for month(mm) param:
        // (i) mm must be 2 digits (ii) following (i), prepend mm(s) 1-9 with 0
        // regex := {months 1-9 with 0 prepended | months 10-12}
        boolean isValidMonth = dateStringToArray[1].matches("^0{1}[1-9]$|^1{1}[0-2]$");
        // conditions for day(dd) param:
        // (i) dd must be 2 digits (ii) following (i), prepend dd(s) 1-9 with 0
        // regex := {days 1-9 with 0 prepended | days 10-19 | days 20-29 | days 30-31
        boolean isValidDay = dateStringToArray[2].matches("^0{1}[1-9]$|^1{1}[0-9]$|^2{1}[0-9]$|^3{1}[0-1]$");
        return (isValidParamsCount && isValidYear && isValidMonth && isValidDay);
    }
}
