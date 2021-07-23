package raschet;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class RaschetTest {

    @Test
    public void schema11() {
        Raschet schema = new Raschet();
        schema.num = 11;
        schema.z0 = 500;
        schema.z3 = 500;
        schema.calc();
        double expected = -0.44;
        double fact = schema.k;
        Assert.assertEquals(expected, fact, 0.01);
    }

    @Test
    public void schema12() {
        Raschet schema = new Raschet();
        schema.num = 12;
        schema.z0 = 500;
        schema.z3 = 500;
        schema.calc();
        double expected = -0.5;
        double fact = schema.k;
        Assert.assertEquals(expected, fact, 0.01);
    }

    @Test
    public void schema21() {
        Raschet schema = new Raschet();
        schema.num = 21;
        schema.z0 = 500;
        schema.z3 = 500;
        schema.l = 10;
        schema.a1 = 0.1;
        schema.calc();
        double expected = -0.5;
        double fact = schema.k;
        Assert.assertEquals(expected, fact, 0.011);
    }

    @Test
    public void schema22() {
        Raschet schema = new Raschet();
        schema.num = 22;
        schema.z0 = 500;
        schema.z3 = 500;
        schema.l = 10;
        schema.a1 = 0.1;
        schema.calc();
        double expected = -0.53;
        double fact = schema.k;
        Assert.assertEquals(expected, fact, 0.01);
    }

    @Test
    public void schema3() {
        Raschet schema = new Raschet();
        schema.num = 3;
        schema.z0 = 500;
        schema.z2 = 700;
        schema.z3 = 500;
        schema.calc();
        double expected = -0.35;
        double fact = schema.k;
        Assert.assertEquals(expected, fact, 0.01);
    }

    @Test
    public void schema4() {
        Raschet schema = new Raschet();
        schema.num = 4;
        schema.z0 = 500;
        schema.z3 = 500;
        schema.calc();
        double expected = -0.6;
        double fact = schema.k;
        Assert.assertEquals(expected, fact, 0.01);
    }

    @Test
    public void schema5() {
        Raschet schema = new Raschet();
        schema.num = 5;
        schema.z3 = 500;
        schema.calc();
        double expected = -0.47;
        double fact = schema.k;
        Assert.assertEquals(expected, fact, 0.01);
    }

    @Test
    public void schema6() {
        Raschet schema = new Raschet();
        schema.num = 6;
        schema.z3 = 500;
        schema.calc();
        double expected = -0.38;
        double fact = schema.k;
        Assert.assertEquals(expected, fact, 0.01);
    }

    @Test
    public void schema7() {
        Raschet schema = new Raschet();
        schema.num = 7;
        schema.z3 = 500;
        schema.calc();
        double expected = -0.27;
        double fact = schema.k;
        Assert.assertEquals(expected, fact, 0.01);
    }

    @Test
    public void schema8() {
        Raschet schema = new Raschet();
        schema.num = 8;
        schema.z3 = 500;
        schema.calc();
        double expected = -0.27;
        double fact = schema.k;
        Assert.assertEquals(expected, fact, 0.01);
    }

    @Test
    public void schema9() {
        Raschet schema = new Raschet();
        schema.num = 9;
        schema.l = 10;
        schema.a1 = 0.1;
        schema.calc();
        double expected = -0.79;
        double fact = schema.k;
        Assert.assertEquals(expected, fact, 0.01);
    }
}