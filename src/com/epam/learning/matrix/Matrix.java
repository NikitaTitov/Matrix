package com.epam.learning.matrix;

import com.epam.learning.operations.Operation;
import com.epam.learning.operations.Addition;
import com.epam.learning.operations.Subtraction;

import java.util.Arrays;


public class Matrix implements Cloneable {

    int[][] array;

    public Matrix(int rowCount, int columnCount) {
        array = new int[rowCount][columnCount];
    }

    @Override
    protected Matrix clone() {
        Matrix ret = new Matrix(getRowCount(),getColumnCount());
        ret.array = array.clone();
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix = (Matrix) o;

        return Arrays.deepEquals(array, matrix.array);

    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(array);
    }

    private void isMultiplyMatrix(Matrix right) {
        if (this.getColumnCount() != right.getColumnCount()) {
            throw new IllegalArgumentException("The number of rows of the left matrix does not match the number of columns of the right matrix. Multiplication is impossible");
        }

    }

    private int getElement(int row, int column) {
        return array[row][column];
    }

    public void setElement(int row, int column, int element) {
        array[row][column] = element;
    }

    public int getRowCount() {
        return array.length;
    }

    public int getColumnCount() {
        return array[0].length;
    }

    public static Matrix multiply(Matrix left, Matrix right) {
        Matrix result = new Matrix(left.getRowCount(), right.getColumnCount());
        for (int i = 0; i < left.getRowCount(); i++) {
            for (int j = 0; j < right.getColumnCount(); j++) {
                for (int k = 0; k < left.getColumnCount(); k++) {
                    result.array[i][j] += left.array[i][k] * right.array[k][j];
                }
            }
        }
        return result;
    }

    public Matrix addition(Matrix other) {
        this.isMultiplyMatrix(other);
        return calculate(other, new Addition());
    }

    public Matrix subtraction(Matrix other) {
        this.isMultiplyMatrix(other);
        return calculate(other, new Subtraction());
    }

    Matrix calculate(Matrix other, Operation operation) {
        this.isMultiplyMatrix(other);
        Matrix result = new Matrix(getRowCount(), getColumnCount());
        for (int row = 0; row < getRowCount(); row++) {
            for (int column = 0; column < getColumnCount(); column++) {
                result.array[row][column] = operation.calculate(this.array[row][column], other.array[row][column]);
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
            result += sign * matrix.getElement(0, i) * (calcDeterminant(smaller));
        }
        return (result);
    }

}