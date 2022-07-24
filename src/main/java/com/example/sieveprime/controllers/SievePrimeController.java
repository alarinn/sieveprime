package com.example.sieveprime.controllers;

import com.example.sieveprime.services.SievePrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class SievePrimeController {

    @Autowired
    SievePrimeService sievePrimeService;

    @GetMapping("/primes/{value}")
    public int[] primes(@PathVariable Integer value) {
        return sievePrimeService.getPrimeNumbers(value);
    }
}
