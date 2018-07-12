package com.example.demo.thread.bank;

/**
 * @author king
 * @version 2018-07-12 8:29 PM
 */
public class Test {
    public static void main(String[] args) {
        Bank bank = new Bank(100, 1000);
        for (int i = 0; i < 3; i++) {
            TransferRunnable transferRunnable = new TransferRunnable(bank, i, 1000);
            Thread thread = new Thread(transferRunnable);
            thread.start();
        }
    }
}