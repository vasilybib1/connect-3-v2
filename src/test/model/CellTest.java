package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellTest {

    @Test
    public void testCell(){
        Cell c = new Cell(0.0, 0.0, 0.0);
        assertEquals(c.getRed(), 0.0);
        assertEquals(c.getBlue(), 0.0);
        assertEquals(c.getGreen(), 0.0);
        assertEquals(c.toString(), "0.0 0.0 0.0");
    }
}
