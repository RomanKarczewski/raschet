package raschet;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class RaschetTest {

    @Test
    public void calc() {
        Raschet schema = new Raschet();
        schema.num = 11;
        schema.z0 = 500;
        schema.z3 = 500;
        schema.calc();
        double expected = -0.44;
        double fact = schema.k;
        Assert.assertEquals(expected, fact, 0.01);
    }
}