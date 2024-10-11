
package BMS;


public class SavingsAccount extends Account{
    
    private double interestRate;

    public SavingsAccount(String accountNumber , String name , double balance , double interestRate) {
        super(accountNumber, name, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    
    public String toString(){
        return super.toString() + "Interest rate : " + interestRate;
    }
    
    
    
}
