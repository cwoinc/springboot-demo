package com.example.demo.thread.bank;

/**
 * @author king
 * @version 2018-07-12 8:29 PM
 */
public class TransferRunnable implements Runnable {
    
    private Bank bank;
    private int fromAccount;
    private double maxAmount;
    
    public TransferRunnable(Bank b, int fromAccount, double maxAmount) {
        this.bank = b;
        this.fromAccount = fromAccount;
        this.maxAmount = maxAmount;
    }
    
    @Override
    public void run() {
        double amount = maxAmount * Math.random();
        int toAccount = (int) (bank.getAccountSize() * Math.random());
        bank.transfer(fromAccount, toAccount, amount);
        try {
            Thread.sleep((long) (100L * Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
