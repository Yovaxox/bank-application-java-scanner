import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Create an ArrayList to store all employees
    private static ArrayList<Employee> employees = new ArrayList<>();
    public static void main(String[] args) {
        // Creating a new scanner
        Scanner scanner = new Scanner(System.in);

        // Clear the screen and print the welcome message
        System.out.print("\033[H\033[2J");
        System.out.println("Welcome to the bank application.");

        // Initializing variables
        int firstMenuOption = getMenuOption(scanner);

        while (firstMenuOption != 6) {
            switch (firstMenuOption) {
                case 1:
                    createNewAccount(scanner);
                    break;
                case 2:
                    depositMoney(scanner);
                    break;
                case 3:
                    withdrawMoney(scanner);
                    break;
                case 4:
                    checkEmployeeAccount(scanner);
                    break;
                case 5:
                    listAllEmployees(scanner);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

            firstMenuOption = getMenuOption(scanner);
        }
        System.out.println("You have exited the bank application.");
        // Close scanner
        scanner.close();
    }

    public static int getMenuOption(Scanner scanner) {
        System.out.println("-----------------------------");
        System.out.println("| 1. Create a new account   |");
        System.out.println("| 2. Deposit money          |");
        System.out.println("| 3. Withdraw money         |");
        System.out.println("| 4. Check employee account |");
        System.out.println("| 5. List all employees     |");
        System.out.println("| 6. Exit                   |");
        System.out.println("-----------------------------");

        // Read the user's input asking for a menu option
        System.out.print("Enter an option: ");
        int menuOption = scanner.nextInt();
        
        scanner.nextLine();

        // Clear the screen
        System.out.print("\033[H\033[2J");

        return menuOption;
    }

    public static void createNewAccount(Scanner scanner) {
        try {
            // Clear the screen and print the welcome message
            System.out.print("\033[H\033[2J");
            System.out.println("Creating a new account.");

            System.out.println("-----------------------------");

            // Read the user's input asking for the employee's name
            String name;
            while (true) {
                System.out.print("Enter the employee's name: ");
                name = scanner.nextLine();
                if (name != null && !name.trim().isEmpty()) {
                    break;
                }
                System.out.print("\033[H\033[2J");
                System.out.println("Name cannot be empty. Please try again.");
            }

            System.out.print("\033[H\033[2J");

            // Read the user's input asking for the employee's last name
            String lastName;
            while (true) {
                System.out.print("Enter the employee's last name: ");
                lastName = scanner.nextLine();
                if (lastName != null && !lastName.trim().isEmpty()) {
                    break;
                }
                System.out.print("\033[H\033[2J");
                System.out.println("Last name cannot be empty. Please try again.");
            }

            System.out.print("\033[H\033[2J");

            // Read the user's input asking for the employee's account number
            int accountNumber;
            while (true) {
                boolean exists = false;
                try {
                    System.out.print("Enter the employee's account number: ");
                    accountNumber = Integer.parseInt(scanner.nextLine());

                    // Looks for another existent account with the same account number
                    for (Employee employee : employees) {
                        if (employee.getAccountNumber() == accountNumber) {
                            exists = true;
                            System.out.print("\033[H\033[2J");
                            throw new IllegalArgumentException("An account with the same account number already exists. Please try again.");
                        }
                    }

                    if (accountNumber > 0) {
                        break;  // Exit the loop if the input was valid
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.println("Account number cannot be negative or zero. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("\033[H\033[2J");
                    if (exists) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            System.out.print("\033[H\033[2J");

            int balance = 0;

            // Read the user's input asking for the employee's account type
            String accountType;
            while (true) {
                System.out.print("Enter the employee's account type (A, B, or C): ");
                accountType = scanner.nextLine().toUpperCase();
                if (accountType.equals("A") || accountType.equals("B") || accountType.equals("C")) {
                    break;
                }
                System.out.print("\033[H\033[2J");
                System.out.println("Account type must be 'A', 'B', or 'C'. Please try again.");
            }

            // Clear the screen
            System.out.print("\033[H\033[2J");

            // Create a new employee
            employees.add(new Employee(name, lastName, accountNumber, balance, accountType));
            System.out.println("Employee account created successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void depositMoney(Scanner scanner) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        boolean exists = false;
        System.out.println("Depositing money.\n");
        while (true) {
            if (employees.isEmpty()) {
                System.out.print("\033[H\033[2J");
                System.out.println("There are no employees to deposit money to. Returning to the main menu..");
                break;
            }

            try {
                System.out.print("Enter the employee's account number: ");
                int accountNumber = Integer.parseInt(scanner.nextLine());

                for (Employee employee : employees) {
                    if (employee.getAccountNumber() == accountNumber) {
                        exists = true;
                        System.out.print("\nEnter the amount to deposit: $");

                        try {
                            int amount = Integer.parseInt(scanner.nextLine());
                            employee.deposit(amount);
                            String balance = numberFormat.format(employee.getBalance());
                            System.out.println("\n$" + amount + " deposited successfully. The new balance is $" + balance + ".");
                            break;
                        } catch (NumberFormatException e) {
                            System.out.print("\033[H\033[2J");
                            System.out.println("Invalid input. Please enter a number for the deposit amount.\n");
                        } catch (Exception e) {
                            System.out.print("\033[H\033[2J");
                            System.out.println(e.getMessage() + " Please enter a valid deposit.\n");
                        }
                    }
                }

                if (!exists) {
                    System.out.println("\nEmployee not found.");
                }
                System.out.println("\nPress any key to return to the main menu.");
                scanner.nextLine();
                System.out.print("\033[H\033[2J");
                break;
            } catch (NumberFormatException e) {
                System.out.print("\033[H\033[2J");
                System.out.println("Please enter a valid account number.\n");
            }
        }
    }

    public static void withdrawMoney(Scanner scanner) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        boolean exists = false;
        System.out.println("Withdrawing money.\n");
        while (true) {
            if (employees.isEmpty()) {
                System.out.println("There are no employees to withdraw money from. Returning to the main menu..");
                break;
            }

            try {
                System.out.print("Enter the employee's account number: ");
                int accountNumber = Integer.parseInt(scanner.nextLine());

                for (Employee employee : employees) {
                    if (employee.getAccountNumber() == accountNumber) {
                        exists = true;
                        System.out.print("\nEnter the amount to withdraw: $");

                        try {
                            int amount = Integer.parseInt(scanner.nextLine());
                            employee.withdraw(amount);
                            String balance = numberFormat.format(employee.getBalance());
                            String amountFormatted = numberFormat.format(amount);
                            System.out.println("\n$" + amountFormatted + " withdrawn successfully. The new balance is $" + balance + ".");
                            break;
                        } catch (NumberFormatException e) {
                            System.out.print("\033[H\033[2J");
                            System.out.println("Invalid input. Please enter a number for the withdrawal amount.\n");
                        } catch (Exception e) {
                            System.out.print("\033[H\033[2J");
                            System.out.println(e.getMessage() + " Please enter a valid withdrawal.\n");
                        }
                    }
                }

                if (!exists) {
                    System.out.println("\nEmployee not found.");
                }
                System.out.println("\nPress any key to return to the main menu.");
                scanner.nextLine();
                System.out.print("\033[H\033[2J");
                break;
            } catch (NumberFormatException e) {
                System.out.print("\033[H\033[2J");
                System.out.println("Please enter a valid account number.\n");
            }
        }
    }

    public static void checkEmployeeAccount(Scanner scanner) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        boolean exists = false;
        System.out.println("Checking employee account.\n");
        while (true) {
            if (employees.isEmpty()) {
                System.out.println("There are no employees to check. Returning to the main menu..");
                break;
            }

            try {
                System.out.print("Enter the employee's account number: ");
                int accountNumber = Integer.parseInt(scanner.nextLine());

                for (Employee employee : employees) {
                    if (employee.getAccountNumber() == accountNumber) {
                        exists = true;
                        String balance = numberFormat.format(employee.getBalance());
                        System.out.println("\nThe account number of the employee " + employee.getName() + " " + employee.getLastName() + " is " + employee.getAccountNumber() + ", their balance is $" + balance + " and the account type is " + employee.getAccountType() + ".");
                        break;
                    }
                }

                if (!exists) {
                    System.out.println("\nEmployee not found.");
                }
                System.out.println("\nPress any key to return to the main menu.");
                scanner.nextLine();
                System.out.print("\033[H\033[2J");
                break;
            } catch (NumberFormatException e) {
                System.out.print("\033[H\033[2J");
                System.out.println("Please enter a valid account number.\n");
            }
        }
    }

    public static void listAllEmployees(Scanner scanner) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        while (true) {
            if (employees.isEmpty()) {
                System.out.println("There are no employees to list. Returning to the main menu..");
                break;
            }

            System.out.println("Listing all employees. Total: " + employees.size() + " employees.\n");

            for (Employee employee : employees) {
                int index = employees.indexOf(employee) + 1;
                String balance = numberFormat.format(employee.getBalance());
                System.out.println(index + ")" + " Name: " + employee.getName() + " " + employee.getLastName() + " | Account number: " + employee.getAccountNumber() + " | Balance: $" + balance + " | Account type: " + employee.getAccountType());
            }
            
            System.out.println("\nPress any key to return to the main menu.");
            scanner.nextLine();
            System.out.print("\033[H\033[2J");
            break;
        }
    }
}