import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class BankAccount {
    private String accountNumber;
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add(new Transaction("Transfer to " + recipient.getAccountNumber(), amount));
            System.out.println("Transfer successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History for Account " + accountNumber + ":");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction.getType() + ": " + transaction.getAmount());
        }
    }
}

public class OnlineBankSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create two bank accounts for testing
        BankAccount account1 = new BankAccount("123456");
        BankAccount account2 = new BankAccount("789012");

        // Simulate operations
        account1.deposit(1000);
        account1.withdraw(500);
        account1.transfer(account2, 200);

        account2.deposit(500);
        account2.transfer(account1, 100);

        // Display transaction history
        account1.displayTransactionHistory();
        account2.displayTransactionHistory();

        scanner.close();
    }
}

