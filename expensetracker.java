```java
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ExpenseTracker {

    private static final String FILE_NAME = "expenses.csv";
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {

        initializeFile();

        while (true) {
            System.out.println("\n========================================");
            System.out.println("           EXPENSE TRACKER");
            System.out.println("========================================");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Calculate Total Expenses");
            System.out.println("4. Category-wise Summary");
            System.out.println("5. Search Expenses");
            System.out.println("6. Exit");
            System.out.println("========================================");

            System.out.print("Enter your choice (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addExpense();
                    break;

                case "2":
                    viewExpenses();
                    break;

                case "3":
                    calculateTotal();
                    break;

                case "4":
                    categorySummary();
                    break;

                case "5":
                    searchExpenses();
                    break;

                case "6":
                    System.out.println("Thank you for using Expense Tracker!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }

    private static void initializeFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("Date,Category,Description,Amount");
            } catch (IOException e) {
                System.out.println("Error creating expense file.");
            }
        }
    }

    private static void addExpense() {

        String date;

        while (true) {
            System.out.print("Enter date (YYYY-MM-DD) or press Enter for today's date: ");
            date = scanner.nextLine().trim();

            if (date.isEmpty()) {
                date = LocalDate.now().format(DATE_FORMAT);
                break;
            }

            try {
                LocalDate.parse(date, DATE_FORMAT);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please use YYYY-MM-DD.");
            }
        }

        System.out.print("Enter category: ");
        String category = scanner.nextLine().trim();

        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();

        double amount;

        while (true) {
            System.out.print("Enter amount: ");

            try {
                amount = Double.parseDouble(scanner.nextLine());

                if (amount <= 0) {
                    System.out.println("Amount must be greater than 0.");
                } else {
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(FILE_NAME, true))) {

            writer.printf("%s,%s,%s,%.2f%n",
                    date, category, description, amount);

            System.out.println("Expense added successfully!");

        } catch (IOException e) {
            System.out.println("Error saving expense.");
        }
    }

    private static List<Expense> readExpenses() {

        List<Expense> expenses = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",", -1);

                if (data.length == 4) {
                    try {
                        double amount = Double.parseDouble(data[3]);

                        expenses.add(new Expense(
                                data[0],
                                data[1],
                                data[2],
                                amount
                        ));

                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid expense record.");
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading expense file.");
        }

        return expenses;
    }

    private static void viewExpenses() {

        List<Expense> expenses = readExpenses();

        if (expenses.isEmpty()) {
            System.out.println("\nNo expenses recorded.");
            return;
        }

        System.out.println("\n" + "=".repeat(75));
        System.out.printf("%-15s %-15s %-25s %15s%n",
                "Date", "Category", "Description", "Amount");
        System.out.println("=".repeat(75));

        for (Expense expense : expenses) {
            System.out.printf("%-15s %-15s %-25s ₹%13.2f%n",
                    expense.date,
                    expense.category,
                    expense.description,
                    expense.amount);
        }

        System.out.println("=".repeat(75));
    }

    private static void calculateTotal() {

        List<Expense> expenses = readExpenses();

        double total = 0;

        for (Expense expense : expenses) {
            total += expense.amount;
        }

        System.out.printf("%nTotal Expenses: ₹%.2f%n", total);
    }

    private static void categorySummary() {

        List<Expense> expenses = readExpenses();

        if (expenses.isEmpty()) {
            System.out.println("\nNo expenses recorded.");
            return;
        }

        Map<String, Double> summary = new LinkedHashMap<>();

        for (Expense expense : expenses) {
            summary.put(
                    expense.category,
                    summary.getOrDefault(expense.category, 0.0)
                            + expense.amount
            );
        }

        System.out.println("\nCategory-wise Expenses");
        System.out.println("-".repeat(35));

        for (Map.Entry<String, Double> entry : summary.entrySet()) {
            System.out.printf("%-20s ₹%.2f%n",
                    entry.getKey(),
                    entry.getValue());
        }
    }

    private static void searchExpenses() {

        System.out.print("\nEnter category or description to search: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        List<Expense> expenses = readExpenses();
        List<Expense> results = new ArrayList<>();

        for (Expense expense : expenses) {

            if (expense.category.toLowerCase().contains(keyword)
                    || expense.description.toLowerCase().contains(keyword)) {

                results.add(expense);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No matching expenses found.");
            return;
        }

        System.out.println("\nMatching Expenses");
        System.out.println("=".repeat(75));

        System.out.printf("%-15s %-15s %-25s %15s%n",
                "Date", "Category", "Description", "Amount");

        System.out.println("=".repeat(75));

        for (Expense expense : results) {
            System.out.printf("%-15s %-15s %-25s ₹%13.2f%n",
                    expense.date,
                    expense.category,
                    expense.description,
                    expense.amount);
        }

        System.out.println("=".repeat(75));
    }

    static class Expense {

        String date;
        String category;
        String description;
        double amount;

        Expense(String date, String category,
                String description, double amount) {

            this.date = date;
            this.category = category;
            this.description = description;
            this.amount = amount;
        }
    }
}
```
