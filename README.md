# Personal Budget Tracker

## Overview
The **Personal Budget Tracker** is a console-based Java application designed to help users manage their personal finances. It addresses the basic human need for financial security by allowing users to track income, expenses, budgets, and savings goals. The application leverages Object-Oriented Programming (OOP) principles (encapsulation, inheritance, polymorphism, abstraction) and data structures (ArrayList, HashMap, HashSet, TreeMap) to provide a robust and extensible solution.

### Features
1. **Category Management**: Add expense/income categories (e.g., "Food", "Salary").
2. **Transaction Tracking**: Record income and expenses with details like ID, amount, date, and category.
3. **Budget Setting**: Set monthly budgets for categories and receive alerts when spending approaches limits.
4. **Savings Goals**: Create and track progress toward savings goals (e.g., saving for a vacation).
5. **Summary Reports**: Generate reports showing balance, spending by category, and savings goal progress.
6. **Transaction Display**: View all recorded transactions.

### Technologies
- **Language**: Java (JDK 11 or later)
- **Data Structures**: ArrayList (transactions), HashSet (categories), HashMap (budgets), TreeMap (reports)
- **OOP Principles**: Encapsulation, inheritance, polymorphism, abstraction
- **Interface**: Console-based with text input/output

## Prerequisites
- **Java Development Kit (JDK)**: Version 11 or later
- **Terminal/Console**: Any terminal supporting Java (e.g., Command Prompt, Bash, PowerShell)
- No external dependencies are required; the project uses only standard Java libraries.

## Setup
1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd personal-budget-tracker
   ```

2. **Ensure Files**:
   - The project consists of a single Java file: `PersonalBudgetTracker.java`, which includes all necessary classes (`Transaction`, `Income`, `Expense`, `SavingsGoal`, `BudgetTracker`).

3. **Compile the Code**:
   ```bash
   javac PersonalBudgetTracker.java
   ```

4. **Run the Application**:
   ```bash
   java PersonalBudgetTracker
   ```

## Usage
The application presents a console menu with the following options:
```
=== Budget Tracker Menu ===
1. Add Category
2. Add Income
3. Add Expense
4. Set Budget
5. Add Savings Goal
6. Contribute to Savings Goal
7. Check Budget Alerts
8. Generate Report
9. Display Transactions
0. Exit
Choose an option:
```

### Instructions
- **Add Category**: Enter a category name (e.g., "Food", "Salary") to organize transactions.
- **Add Income/Expense**: Provide an ID, amount, date (YYYY-MM-DD), and category. Categories must exist before adding transactions.
- **Set Budget**: Specify a category and budget amount to set spending limits.
- **Add Savings Goal**: Enter a goal name and target amount.
- **Contribute to Savings Goal**: Specify a goal name and contribution amount.
- **Check Budget Alerts**: Displays alerts if spending in any category exceeds 90% of the budget.
- **Generate Report**: Shows current balance, spending by category, and savings goals.
- **Display Transactions**: Lists all transactions with details.
- **Exit**: Terminates the program.

### Example Run
```bash
=== Budget Tracker Menu ===
Choose an option: 1
Enter category name: Food
Category added: Food
Choose an option: 1
Enter category name: Salary
Category added: Salary
Choose an option: 2
Enter income ID: I001
Enter amount: 1000
Enter date (YYYY-MM-DD): 2025-06-01
Enter category: Salary
Transaction added: Income [ID=I001, Amount=$1000.0, Date=2025-06-01, Category=Salary]
Choose an option: 3
Enter expense ID: E001
Enter amount: 200
Enter date (YYYY-MM-DD): 2025-06-02
Enter category: Food
Transaction added: Expense [ID=E001, Amount=$200.0, Date=2025-06-02, Category=Food]
Choose an option: 8
=== Budget Report ===
Balance: $800.00
Spending by Category:
Food: $200.00
Salary: $-1000.00
Savings Goals:
Choose an option: 0
Exiting...
```

## Testing
All functionalities can be tested directly in the terminal:
1. **Compile and Run**: Follow the setup instructions.
2. **Test Cases**:
   - **Category**: Add "Food" and "Salary", then try adding a duplicate category (should not allow duplicates due to HashSet).
   - **Transactions**: Add an income (e.g., $1000, "Salary") and an expense (e.g., $200, "Food"). Verify balance updates correctly ($800) via "Generate Report".
   - **Budget Alerts**: Set a budget for "Food" ($300), add expenses nearing/exceeding it, and check alerts.
   - **Savings Goals**: Add a goal ("Vacation", $1000), contribute $100, and verify via report.
   - **Error Handling**: Test invalid inputs (e.g., non-numeric amount, non-existent category) to ensure proper error messages.
3. **Verify Outputs**: Use "Generate Report" and "Display Transactions" to confirm data consistency.
4. **Console Debugging**: All outputs (success messages, errors, reports) are printed to the terminal for easy verification.

## Project Structure
```
personal-budget-tracker/
├── PersonalBudgetTracker.java  # Main file with all classes and console interface
└── README.md                   # This file
```

## Future Enhancements
- **Data Persistence**: Save data to a file (e.g., JSON, CSV) or database (e.g., SQLite).
- **GUI**: Add a graphical interface using JavaFX or Swing.
- **Advanced Date Handling**: Use `java.time.LocalDate` for robust date management.
- **Recurring Transactions**: Support automatic recurring income/expenses (e.g., monthly rent).
- **Unit Tests AMC Testing**: Add JUnit tests for automated validation.

## License
This project is licensed under the MIT License.

## Contact
For issues or contributions, please open an issue or pull request on the repository.