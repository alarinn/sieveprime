package com.example.sieveprime.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SievePrimeService {
    public int[] getPrimeNumbers(int value) {
        boolean[] isPrime = new boolean[value+1];
        for (int i = 2; i < isPrime.length; i++) {
            isPrime[i] = true;
        }

        for (int j = 2; j*j <= value; j++) {
            if (isPrime[j]) {
                for (int k = j; j*k <= value; k++) {
                    isPrime[j*k] = false;
                }
            }
        }

        int[] primes = new int[value+1];
        int primeCount = 0;
        for (int l = 0; l <= value; l++) {
            if (isPrime[l]) {
                primes[primeCount] = l;
                primeCount++;
            }
        }

        return Arrays.stream(primes)
                .filter(num -> num != 0)
                .toArray();
    }
}
