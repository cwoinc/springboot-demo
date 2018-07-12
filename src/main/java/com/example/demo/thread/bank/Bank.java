package com.example.demo.thread.bank;

/**
 * @author king
 * @version 2018-07-12 8:28 PM
 */
public class Bank {
    
    private final double[] accounts;
    
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = initialBalance;
        }
    }
    
    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) {
            return;
        }
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf("%f from %d to %d ", amount, from, to);
        accounts[to] += amount;
        System.out.println("total:" + getTotalBalance());
    }
    
    public double getTotalBalance() {
        double sum = 0d;
        for (int i = 0; i < accounts.length; i++) {
            sum += accounts[i];
        }
        return sum;
    }
    
    public int getAccountSize() {
        return accounts.length;
    }
}