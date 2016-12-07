package com.epam.learning.utils;


import com.epam.learning.matrix.Matrix;

public final class UtilsForMatrix {

    public static Matrix fill(int[][] elements) {
        Matrix result = new Matrix(elements.length, elements[0].length);
        for (int row = 0; row < elements.length; row++) {
            for (int column = 0; column < elements[0].length; column++) {
                result.setElement(row, column, elements[row][column]);
            }
        }
        return result;
    }
}
