package com.epam.learning.operations;

public class Subtraction implements Operation {
    @Override
    public int calculate(int first, int second) {
        return first - second;
    }
}