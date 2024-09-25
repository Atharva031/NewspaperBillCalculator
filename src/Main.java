import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean inputOver = false;
        double grandTotal = 0; // To store the total bill for all months

        while (!inputOver) {
            System.out.print("Enter the month and year (format: MMM yyyy): ");
            String input = scanner.nextLine();

            // Parsing the entered month and year
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
            YearMonth yearMonth = YearMonth.parse(input, formatter);
            int daysInMonth = yearMonth.lengthOfMonth();
            double totalBillForMonth = 0;

            // Calculate the bill for the given month
            for (int day = 1; day <= daysInMonth; day++) {
                LocalDate date = yearMonth.atDay(day); // Get the date for each day
                DayOfWeek dayOfWeek = date.getDayOfWeek();

                // If weekend, add Rs. 7 * 2; if weekday, add Rs. 6 * 2
                if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                    totalBillForMonth += 7 * 2;
                } else {
                    totalBillForMonth += 6 * 2;
                }
            }

            // Add the bill for this month to the grand total
            grandTotal += totalBillForMonth;
            System.out.println("Total newspaper bill for " + input + " is Rs. " + totalBillForMonth);

            // Ask if the user wants to enter another month
            System.out.print("Do you want to add another month? (yes/no): ");
            String answer = scanner.next().toLowerCase();

            // Clear the scanner buffer for next input
            scanner.nextLine();

            // If the user says no, exit the loop
            if (answer.equals("no")) {
                inputOver = true;
            }
        }

        // Display the grand total at the end
        System.out.println("Total newspaper bill for all months is Rs. " + grandTotal);
        scanner.close();
    }
}
