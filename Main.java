import java.util.*;

class Account {
    String accHolderName;
    int id;
    double balance;
    Date dateCreated;

    Account(int id, String accHolderName, double balance, Date dateCreated) {
        this.id = id;
        this.accHolderName = accHolderName;
        this.balance = balance;
        this.dateCreated = dateCreated;
    }

    void withdraw(double amount) {
        if (amount > balance)
            System.out.println("invalid transaction");
        else
            balance -= amount;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("deposit successful!");
    }

    void checkBalance() {
        System.out.println(balance);
    }

    void display() {
        System.out.println("---ACCOUNT DETAILS---");
        System.out.println("id - " + this.id);
        System.out.println("name - " + this.accHolderName);
        System.out.println("balance - " + this.balance);
        System.out.println("date created - " + this.dateCreated);
    }

}

class CurrentAcc extends Account {
    double limitBelowZero;

    CurrentAcc(int id, String accHolderName, double balance, Date dateCreated, double limitBelowZero) {
        super(id, accHolderName, balance, dateCreated); // call inside a constructor
        this.limitBelowZero = limitBelowZero;

    }

    @Override
    void withdraw(double amount) {
        if ((balance - amount) < limitBelowZero)
            System.out.println("invalid");
        else
            super.balance -= amount;
    }

    @Override
    void display() {
        super.display();
        System.out.println("account type - current");
    }
}

class SavingsAcc extends Account {
    double annualInterestRate;

    SavingsAcc(int id, String accHolderName, double balance, Date dateCreated, double annualInterestRate) {
        super(id, accHolderName, balance, dateCreated);
        this.annualInterestRate = annualInterestRate;
    }

    @Override
    void display() {
        super.display();
        System.out.println("account type - savings ");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // how long is the arr?
        System.out.println("how many accs? ");
        int n = sc.nextInt();
        sc.nextLine();
        int type;
        Account[] accArray = new Account[n];
        // taking input in variables
        for (int i = 0; i < n; i++) {
            System.out.println("savings(1) or Current (2)?");
            type = sc.nextInt();
            sc.nextLine();
            System.out.println("enter id : ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("acc holder name : ");
            String name = sc.nextLine();

            System.out.println("enter balance : ");
            double balance = sc.nextDouble();
            sc.nextLine();
            Date d = new Date();
            if (type == 1) {
                System.out.println("enter rate : ");
                double interest = sc.nextDouble();
                sc.nextLine();
                accArray[i] = new SavingsAcc(id, name, balance, d, interest);
            } else if (type == 2) {
                System.out.println("enter limit below zero : ");
                double limitBelowZero = sc.nextDouble();
                sc.nextLine();
                accArray[i] = new CurrentAcc(id, name, balance, d, limitBelowZero);
            }
        }
        for (Account a : accArray) {
            a.display();
        }
        while (true) {
            System.out.println("1. Deposit  \n2. Withdraw  \n 3. Check Balance  \n 4. Exit  ");
            int choice = sc.nextInt();
            System.out.println("enter account index , 0 to " + (n - 1));
            int index = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("enter amount to deposit : ");
                    double amt = sc.nextDouble();
                    accArray[index].deposit(amt);
                    accArray[index].display();
                    break;
                case 2:
                    System.out.println("enter amount to withdraw : ");
                    double ammt = sc.nextDouble();
                    accArray[index].withdraw(ammt);
                    accArray[index].display();
                    break;
                case 3:
                    System.out.println("checking balance..");
                    System.out.println("ur balance is " + accArray[index].balance);
                    break;
                case 4:
                    System.out.println("exiting...bye!");
                    return;

            }
        }

    }

}
