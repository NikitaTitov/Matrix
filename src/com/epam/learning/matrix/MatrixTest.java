package com.epam.learning.matrix;

import com.epam.learning.utils.UtilsForMatrix;
import org.junit.Test;

import static org.junit.Assert.*;


public class MatrixTest {

    @Test
    public void testMultiply() throws Exception {
        Matrix left = UtilsForMatrix.fill(new int[][]
                {{1, 2}, {3, 4}});
        Matrix right = UtilsForMatrix.fill(new int[][]
                {{1, 2}, {3, 4}});
        Matrix expect = UtilsForMatrix.fill(new int[][]
                {{7, 10}, {15, 22}});
        assertTrue(Matrix.multiply(left,right).equals(expect));
    }

    @Test
    public void testAddiction() throws Exception {
        Matrix left = UtilsForMatrix.fill(new int[][]
                {{1, 2}, {3, 4}});
        Matrix right = UtilsForMatrix.fill(new int[][]
                {{2, 3}, {4, 5}});
        Matrix expect = UtilsForMatrix.fill(new int[][]
                {{3, 5}, {7, 9}});
        assertTrue(left.addition(right).equals(expect));
    }

    @Test
    public void testSubtraction() throws Exception {
        Matrix left = UtilsForMatrix.fill(new int[][]
                {{1, 0}, {3, 55}});
        Matrix right = UtilsForMatrix.fill(new int[][]
                {{2, 3}, {4, 5}});
        Matrix expect = UtilsForMatrix.fill(new int[][]
                {{-1, -3}, {-1, 50}});
        assertTrue(left.subtraction(right).equals(expect));
    }

    @Test
    public void testCalcDeterminant() throws Exception {
        Matrix test = UtilsForMatrix.fill(new int[][]{{1, 2, 3}, {4, 5, 6}});
        assertTrue(Matrix.calcDeterminant(test) == 0);
    }

    @Test
    public void testClone() throws Exception {
        Matrix left = UtilsForMatrix.fill(new int[][]{{1, 2, 3}, {4, 5, 6}});
        Matrix right = left.clone();
        assertTrue(right instanceof Matrix);
    }
}