package com.example.demo.thread.bank;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

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
        Thread thread = Thread.currentThread();
        Unsafe unsafe = sdgfh();
        long accountsOffset = sdfh(unsafe);
        boolean flag = unsafe.compareAndSwapObject(this, accountsOffset, accounts[from], -amount);
        System.out.println(flag);
        accounts[from] -= amount;
        System.out.println(thread.getName() + "\t" + amount + "\tfrom " + from + " to " + to);
        accounts[to] += amount;
        System.out.println(thread.getName() + "\ttotal:" + getTotalBalance());
    }

    private long sdfh(Unsafe unsafe) {
        long accountsOffset = 0;
        try {
            Field accountsField = Bank.class.getDeclaredField("accounts");
            accountsOffset = unsafe.objectFieldOffset(accountsField);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountsOffset;
    }

    private Unsafe sdgfh() {
        Unsafe unsafe = null;
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }

    public double getTotalBalance() {
//        return Arrays.stream(accounts).sum();
        double sum = 0d;
        for (double account : accounts) {
            sum += account;
        }
        return sum;
    }

    public int getAccountSize() {
        return accounts.length;
    }
}