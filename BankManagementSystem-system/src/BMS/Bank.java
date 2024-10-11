
package BMS;

import java.util.*;
import java.io.*;
public class Bank {
    
    private Map <String , Account> accounts = new HashMap<>();
    private final String FILE_PATH = "data/accounts.ser";
    
    public Bank (){
        
    }
    
    public void createAccount (Account account){
        accounts.put(account.getAccountNumber(), account);
        saveAccounts();
        
    }
    
    public void deleteAccount (String accountNumber) throws AccountNotFoundException{
        if (accounts.containsKey(accountNumber)) {
            accounts.remove(accountNumber);
            saveAccounts();
        } else {
            throw new AccountNotFoundException("Account number : " + accountNumber + " not found.");
        }
    }
    
    public Account searchAccount (String accountNumber) throws AccountNotFoundException{
        if (accounts.containsKey(accountNumber)) {
            return accounts.get(accountNumber);
        } else {
            throw new AccountNotFoundException("Account number : " + accountNumber + " not found.");
        }
    }
    
     public List<Account> searchAccountByName(String name) {
        List<Account> results = new ArrayList<>();
        for (Account account : accounts.values()) {
            if (account.getName().equalsIgnoreCase(name)) {
                results.add(account);
            }
        }
        return results;
    }
     
     public void updateAccount (String accountNumber , String newName , double newBalance , Double interestRate)throws AccountNotFoundException{
         Account account = searchAccount(accountNumber);
         account.setName(newName);
         account.setBalance(newBalance);
         
         if (account instanceof SavingsAccount && interestRate!= null) {
             ((SavingsAccount)account).setInterestRate(interestRate);
         }
         saveAccounts();
     }
     
     public void deposit (String accountNumber , double amount) throws AccountNotFoundException{
         Account account = searchAccount(accountNumber);
         account.deposit(amount);
         saveAccounts();
     }
     
     public void withdraw (String accountNumber , double amount) throws AccountNotFoundException{
         Account account = searchAccount(accountNumber);
         account.withdraw(amount);
         saveAccounts();
     }
     
     public void transfer (String fromAccountNumber , String toAccountNumber , double amount) throws AccountNotFoundException{
         Account fromAccount = searchAccount(fromAccountNumber);
         Account toAccount = searchAccount(toAccountNumber);
         fromAccount.withdraw(amount);
         toAccount.deposit(amount);
         saveAccounts();
     }
     
     public double checkBalance (String accountNumber) throws AccountNotFoundException{
         Account account = searchAccount(accountNumber);
         return account.getBalance();
     }
    
    public void saveAccounts(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(accounts);
            
        } catch (IOException e ){
            System.out.println("Error saving accounts : " + e.getMessage());
        }
    }
    
    
    
    public void loadAccounts(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            accounts = (Map<String, Account>) in.readObject();
        } catch (FileNotFoundException e){
            System.out.println("No previous account found, Starting fresh.");
        } catch (IOException  | ClassNotFoundException e){
            System.out.println("Error loading accounts : " + e.getMessage());
        }
    }
    
    
}
