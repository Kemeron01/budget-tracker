import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

interface Reportable {
    String generateReport();
}

abstract class Transaction {
    protected String id;
    protected double amount;
    protected String date;
    protected String category;

    public Transaction(String id, double amount, String date, String category) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String getId() { return id; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDate() { return date; }

    public abstract double calculateImpact();
}

class Income extends Transaction {
    public Income(String id, double amount, String date, String category) {
        super(id, amount, date, category);
    }

    @Override
    public double calculateImpact() {
        return amount; 
    }

    @Override
    public String toString() {
        return "Income [ID=" + id + ", Amount=$" + amount + ", Date=" + date + ", Category=" + category + "]";
    }
}

class Expense extends Transaction {
    public Expense(String id, double amount, String date, String category) {
        super(id, amount, date, category);
    }

    @Override
    public double calculateImpact() {
        return -amount; 
    }

    @Override
    public String toString() {
        return "Expense [ID=" + id + ", Amount=$" + amount + ", Date=" + date + ", Category=" + category + "]";
    }
}

class SavingsGoal {
    private String name;
    private double targetAmount;
    private double currentAmount;

    public SavingsGoal(String name, double targetAmount) {
        this.name = name;
        this.targetAmount = targetAmount;
        this.currentAmount = 0.0;
    }

    public String getName() { return name; }
    public double getCurrentAmount() { return currentAmount; }
    public double getTargetAmount() { return targetAmount; }

    public void addToGoal(double amount) {
        if (amount > 0) {
            currentAmount += amount;
        }
    }

    public String toString() {
        return "SavingsGoal [Name=" + name + ", Current=$" + currentAmount + ", Target=$" + targetAmount + "]";
    }
}

class BudgetTracker implements Reportable {
    private ArrayList<Transaction> transactions;
    private HashSet<String> categories;
    private HashMap<String, Double> budgets;
    private ArrayList<SavingsGoal> savingsGoals;
    private double balance;

    public BudgetTracker() {
        transactions = new ArrayList<>();
        categories = new HashSet<>();
        budgets = new HashMap<>();
        savingsGoals = new ArrayList<>();
        balance = 0.0;
    }

    public void addCategory(String category) {
        if (category != null && !category.trim().isEmpty()) {
            categories.add(category);
            System.out.println("Category added: " + category);
        } else {
            System.out.println("Invalid category name.");
        }
    }

    public void addTransaction(Transaction transaction) {
        if (transaction != null && categories.contains(transaction.getCategory())) {
            transactions.add(transaction);
            balance += transaction.calculateImpact();
            System.out.println("Transaction added: " + transaction);
        } else {
            System.out.println("Invalid transaction or category not found.");
        }
    }

    public void setBudget(String category, double amount) {
        if (categories.contains(category) && amount >= 0) {
            budgets.put(category, amount);
            System.out.println("Budget set for " + category + ": $" + amount);
        } else {
            System.out.println("Invalid category or budget amount.");
        }
    }

    public void addSavingsGoal(SavingsGoal goal) {
        if (goal != null && goal.getTargetAmount() > 0) {
            savingsGoals.add(goal);
            System.out.println("Savings goal added: " + goal);
        } else {
            System.out.println("Invalid savings goal.");
        }
    }

    public void contributeToGoal(String goalName, double amount) {
        for (SavingsGoal goal : savingsGoals) {
            if (goal.getName().equals(goalName)) {
                goal.addToGoal(amount);
                balance -= amount;
                System.out.println("Contributed $" + amount + " to " + goalName);
                return;
            }
        }
        System.out.println("Savings goal not found.");
    }

    public void checkBudgetAlerts() {
        HashMap<String, Double> categorySpending = new HashMap<>();
        for (Transaction t : transactions) {
            if (t instanceof Expense) {
                String category = t.getCategory();
                categorySpending.put(category, categorySpending.getOrDefault(category, 0.0) + t.getAmount());
            }
        }
        for (String category : budgets.keySet()) {
            double spent = categorySpending.getOrDefault(category, 0.0);
            double budget = budgets.get(category);
            if (spent > budget * 0.9) {
                System.out.println("Alert: Spending in " + category + " ($" + spent + ") is nearing budget ($" + budget + ")");
            }
        }
    }

    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder("=== Budget Report ===\n");
        report.append("Balance: $").append(String.format("%.2f", balance)).append("\n\n");

        TreeMap<String, Double> categorySpending = new TreeMap<>();
        for (Transaction t : transactions) {
            String category = t.getCategory();
            double amount = t instanceof Expense ? t.getAmount() : -t.getAmount();
            categorySpending.put(category, categorySpending.getOrDefault(category, 0.0) + amount);
        }

        report.append("Spending by Category:\n");
        for (String category : categorySpending.keySet()) {
            double spent = categorySpending.get(category);
            report.append(category).append(": $").append(String.format("%.2f", spent)).append("\n");
        }

        report.append("\nSavings Goals:\n");
        for (SavingsGoal goal : savingsGoals) {
            report.append(goal.toString()).append("\n");
        }

        return report.toString();
    }

    public void displayTransactions() {
        System.out.println("=== Transactions ===");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}

public class PersonalBudgetTracker {
    public static void main(String[] args) {
        BudgetTracker tracker = new BudgetTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Budget Tracker Menu ===");
            System.out.println("1. Add Category");
            System.out.println("2. Add Income");
            System.out.println("3. Add Expense");
            System.out.println("4. Set Budget");
            System.out.println("5. Add Savings Goal");
            System.out.println("6. Contribute to Savings Goal");
            System.out.println("7. Check Budget Alerts");
            System.out.println("8. Generate Report");
            System.out.println("9. Display Transactions");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter category name: ");
                    tracker.addCategory(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter income ID: ");
                    String incomeId = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double incomeAmount;
                    try {
                        incomeAmount = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.");
                        continue;
                    }
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String incomeDate = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String incomeCategory = scanner.nextLine();
                    tracker.addTransaction(new Income(incomeId, incomeAmount, incomeDate, incomeCategory));
                    break;
                case 3:
                    System.out.print("Enter expense ID: ");
                    String expenseId = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double expenseAmount;
                    try {
                        expenseAmount = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.");
                        continue;
                    }
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String expenseDate = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String expenseCategory = scanner.nextLine();
                    tracker.addTransaction(new Expense(expenseId, expenseAmount, expenseDate, expenseCategory));
                    break;
                case 4:
                    System.out.print("Enter category: ");
                    String budgetCategory = scanner.nextLine();
                    System.out.print("Enter budget amount: ");
                    double budgetAmount;
                    try {
                        budgetAmount = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.");
                        continue;
                    }
                    tracker.setBudget(budgetCategory, budgetAmount);
                    break;
                case 5:
                    System.out.print("Enter savings goal name: ");
                    String goalName = scanner.nextLine();
                    System.out.print("Enter target amount: ");
                    double targetAmount;
                    try {
                        targetAmount = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.");
                        continue;
                    }
                    tracker.addSavingsGoal(new SavingsGoal(goalName, targetAmount));
                    break;
                case 6:
                    System.out.print("Enter savings goal name: ");
                    String contributeGoal = scanner.nextLine();
                    System.out.print("Enter contribution amount: ");
                    double contributeAmount;
                    try {
                        contributeAmount = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.");
                        continue;
                    }
                    tracker.contributeToGoal(contributeGoal, contributeAmount);
                    break;
                case 7:
                    tracker.checkBudgetAlerts();
                    break;
                case 8:
                    System.out.println(tracker.generateReport());
                    break;
                case 9:
                    tracker.displayTransactions();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
