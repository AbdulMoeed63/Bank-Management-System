
package BMS;


public class CurrentAccount extends Account {
    public CurrentAccount(String accountNumber, String name, double balance) {
        super(accountNumber, name, balance);
    }

    
    public String toString() {
        return super.toString() + " [Current Account]";
    }
}

