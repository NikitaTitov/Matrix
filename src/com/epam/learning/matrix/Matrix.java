package com.epam.learning.matrix;

import com.epam.learning.operations.Operation;
import com.epam.learning.operations.Addition;
import com.epam.learning.operations.Subtraction;

import java.util.Arrays;


public class Matrix implements Cloneable {

    int[][] martix;

    public Matrix(int rowCount, int columnCount) {
        martix = new int[rowCount][columnCount];
    }

    @Override
    protected Matrix clone() throws CloneNotSupportedException {
        return (Matrix)super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix = (Matrix) o;

        return Arrays.deepEquals(martix, matrix.martix);

    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(martix);
    }
    private void same(Matrix right) {
        if (this.getColumnCount() != right.getColumnCount()) {
            throw new IllegalArgumentException("1");
        }

    }

    private int getElement(int row, int column) {
        return martix[row][column];
    }

    public void setElement(int row, int column, int element) {
        martix[row][column] = element;
    }

    public int getRowCount() {
        return martix.length;
    }

    public int getColumnCount() {
        return martix[0].length;
    }


    public Matrix multiply(Matrix other) {
        this.same(other);
        Matrix result = new Matrix(this.getRowCount(),other.getColumnCount());
        for (int rowNumber = 0; rowNumber < this.getRowCount(); rowNumber++) {
            for (int columnNumber = 0; columnNumber < other.getColumnCount(); columnNumber++) {
                result.martix[rowNumber][columnNumber] = multiplyVector(getRow(this, rowNumber), getColumn(other, columnNumber));
            }
        }
        return result;
    }
    private static int[] getColumn(Matrix matrix, int columnNumber) {
        int[] result = new int[matrix.getRowCount()];
        for (int rowNumber = 0; rowNumber < matrix.getRowCount(); rowNumber++) {
            result[rowNumber] = matrix.getElement(rowNumber,columnNumber);
        }
        return result;
    }
    private static int[] getRow(Matrix matrix, int rowNumber) {
        int[] result = new int[matrix.getColumnCount()];
        for (int columnNumber = 0; columnNumber < matrix.getColumnCount(); columnNumber++) {
            result[columnNumber] = matrix.getElement(columnNumber,rowNumber);
        }
        return result;
    }
    private static int multiplyVector(int[] firstMatrix, int[] secondMatrix) {
        int multiply = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            multiply += firstMatrix[i] * secondMatrix[i];
        }
        return multiply;
    }

    public Matrix addiction(Matrix other) {
        this.same(other);
        return calculate(other, new Addition());
    }

    public Matrix subtraction(Matrix other) {
        this.same(other);
        return calculate(other, new Subtraction());
    }

    Matrix calculate(Matrix other, Operation operation) {
        this.same(other);
        Matrix result = new Matrix(getRowCount(), getColumnCount());
        for (int row = 0; row < getRowCount(); row++) {
            for (int column = 0; column < getColumnCount(); column++) {
                result.martix[row][column] = operation.calculate(this.martix[row][column], other.martix[row][column]);
            }
        }
        return result;
    }

    public static Matrix fill(int[][] elements) {
        Matrix result = new Matrix(elements.length, elements[0].length);
        for (int row = 0; row < elements.length; row++) {
            for (int column = 0; column < elements[0].length; column++) {
                result.setElement(row, column, elements[row][column]);
            }
        }
        return result;
    }

    public static int calcDeterminant(Matrix matrix) {
        int result = 0;
        int sign;
        if (matrix.getRowCount() == 1) {
            return 0;
        }
        for (int i = 0; i < matrix.getRowCount(); i++) {
            Matrix smaller = new Matrix(matrix.getRowCount() - 1, matrix.getColumnCount() - 1);
            for (int a = 1; a < matrix.getRowCount(); a++) {
                for (int b = 0; b < matrix.getColumnCount(); b++) {
                    if (b < i) {
                        smaller.setElement(a - 1, b, matrix.getElement(a, b));
                    } else if (b > i) {
                        smaller.setElement(a - 1, b - 1, matrix.getElement(a, b));
                    }
                }
            }
            if (i % 2 == 0) {
                sign = 1;
            } else {
                sign = -1;
            }
            result += sign * matrix.getElement(0,i) * (calcDeterminant(smaller));
        }
        return (result);
    }

}