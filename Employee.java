public class Employee {
    // Properties
    private String name;
    private String lastName;
    private int accountNumber;
    private int balance;
    private String accountType;

    // Constructor
    public Employee(String name, String lastName, int accountNumber, int balance, String accountType) {
        this.name = name;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        this.lastName = lastName;
    }

    public void setAccountNumber(int accountNumber) {
        if (accountNumber <= 0) {
            throw new IllegalArgumentException("Account number cannot be negative or zero");
        }
        this.accountNumber = accountNumber;
    }

    public void setBalance(int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    public void setAccountType(String accountType) {
        if (accountType != "A" && accountType != "B" && accountType != "C") {
            throw new IllegalArgumentException("Account type must be 'A', 'B', or 'C'");
        }
        this.accountType = accountType;
    }

    // Methods
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        // If account type is A, it can only have a maximum of $50,000
        if (accountType.equals("A") && balance + amount > 50_000) {
            throw new IllegalArgumentException("Account type A cannot have more than $50,000.");
        }
        // If account type is B, it can only have a maximum of $100,000
        if (accountType.equals("B") && balance + amount > 100_000) {
            throw new IllegalArgumentException("Account type B cannot have more than $100,000.");
        }
        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (accountType.equals("A") && amount < 1_000) {
            throw new IllegalArgumentException("Minimum withdrawal for account type A is $1,000.");
        }
        if (accountType.equals("B") && amount < 5_000) {
            throw new IllegalArgumentException("Minimum withdrawal for account type B is $5,000.");
        }
        if (accountType.equals("C") && amount < 10_000) {
            throw new IllegalArgumentException("Minimum withdrawal for account type C is $10,000.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance for withdrawal.");
        }
        balance -= amount;
    }
}
