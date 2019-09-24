package junit.practice.I;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Validation Methods tests:")
class ValidationMethodsTest {
    ValidationMethods validate;

    @BeforeEach
    void init() {
        validate = new ValidationMethods();
    }

    @Test
    @DisplayName("Acceptable date (Localdate arg) check e.g. birthdate within specified age limits")
    void test_isBirthdateWithinAgeRange1() {
        // Specified age limits: 18 - 69;
        // Given current year & specified age limits, accepted earliest and latest birthdate are:;
        // 2001 & 1950 respectively...;
        int minYears = 18;
        int maxYears = 70;
        // boundary test cases for lower & upper age limits
        assertAll(
                () -> assertFalse(validate.isBirthdateWithinAgeRange(LocalDate.parse("1949-01-01"), minYears, maxYears)),
                () -> assertTrue(validate.isBirthdateWithinAgeRange(LocalDate.parse("1950-01-01"), minYears, maxYears)),
                () -> assertFalse(validate.isBirthdateWithinAgeRange(LocalDate.parse("2002-01-01"), minYears, maxYears)),
                () -> assertTrue(validate.isBirthdateWithinAgeRange(LocalDate.parse("2001-01-01"), minYears, maxYears))
        );

    }


    @Test
    @DisplayName("Acceptable date (dateString arg) check e.g. birth-date within specified age limits")
    void test_isBirthdateWithinAgeRange2() {
        // comment from test_isBirthdateWithinAgeRange1() above applies
        int minYears = 18;
        int maxYears = 70;
        // boundary test cases for lower & upper age limits
        assertAll(
                () -> assertFalse(validate.isBirthdateWithinAgeRange("1949-01-01", minYears, maxYears)),
                () -> assertTrue(validate.isBirthdateWithinAgeRange("1950-01-01", minYears, maxYears)),
                () -> assertFalse(validate.isBirthdateWithinAgeRange("2002-01-01", minYears, maxYears)),
                () -> assertTrue(validate.isBirthdateWithinAgeRange("2001-01-01", minYears, maxYears))
        );
    }

    @Test
    @DisplayName("Test to validation argument is acceptable char sequence (subset of specified regex)")
    void test_isValidSpecialString() {
        String regex = "\\\\[a-h,q]";
        // boundary test cases
        assertAll(
            () -> assertTrue(validate.isValidSpecialString("\\a", regex)),
            () -> assertTrue(validate.isValidSpecialString("\\h", regex)),
            () -> assertFalse(validate.isValidSpecialString("\\i", regex)),
            () -> assertFalse(validate.isValidSpecialString("\\p", regex)),
            () -> assertTrue(validate.isValidSpecialString("\\q", regex)),
            () -> assertFalse(validate.isValidSpecialString("\\r", regex))
        );
    }

    @Test
    @DisplayName("Test to validation argument is char sequence [A-Za-z] only")
    void test_isLettersOnly() {
        assertAll(
                () -> assertTrue(validate.isLettersOnly("amsmsosnfhsbbekslo")),
                () -> assertFalse(validate.isLettersOnly("amsms0snfhsbbeksl0")),
                () -> assertFalse(validate.isLettersOnly("am\\osnfhsbbekslo")),
                () -> assertFalse(validate.isLettersOnly("amsmsosnfhsb*ekslo"))
        );
    }

    @Test
    @DisplayName("Test to validation argument consists of positive integers only")
    void test_isPositiveNumeric() {
        assertAll(
            () -> assertTrue(validate.isPositiveNumeric("349")),
            () -> assertFalse(validate.isPositiveNumeric("-879"))
        );
    }

    @Test
    @DisplayName("Test to validation argument is an integer (+ve/ -ve)")
    void test_isNumeric() {
        assertAll(
                () -> assertTrue(validate.isNumeric("-781")),
                () -> assertTrue(validate.isNumeric("87")),
                () -> assertFalse(validate.isNumeric("8o9"))
        );
    }

    @Test
    @DisplayName("Test to validation argument is valid date-string of format yyyy-mm-dd")
    void test_isValidDateString() {
        assertAll(
            () -> assertTrue(validate.isValidDateString("1996-12-27")),
            () -> assertFalse(validate.isValidDateString("1996-2-27")),
            () -> assertFalse(validate.isValidDateString("1996-12-7")),
            () -> assertFalse(validate.isValidDateString("96-12-27")),
            () -> assertFalse(validate.isValidDateString("1996-13-08")),
            () -> assertFalse(validate.isValidDateString("1996-13-32"))
        );
    }

}