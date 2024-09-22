package com.yaidel.vacations.UI;

import static org.junit.Assert.*;

import com.yaidel.vacations.entity.Vacation;

import org.junit.Test;

public class VacationTest {

    @Test
    public void testVacationCreation() {
        // Create a vacation object
        Vacation vacation = new Vacation(1, "Beach Vacation", "Seaside Hotel", "2024-09-01", "2024-09-10");

        // Test getters
        assertEquals("Beach Vacation", vacation.getTitle());
        assertEquals("2024-09-01", vacation.getStartDate());
        assertEquals("2024-09-10", vacation.getEndDate());
    }

    @Test
    public void testVacationDates() {
        // Create a vacation object with different dates
        Vacation vacation = new Vacation(2, "Mountain Trip", "Hilltop Resort", "2024-12-15", "2024-12-25");

        // Test date logic
        assertEquals("2024-12-15", vacation.getStartDate());
        assertEquals("2024-12-25", vacation.getEndDate());
    }
}
