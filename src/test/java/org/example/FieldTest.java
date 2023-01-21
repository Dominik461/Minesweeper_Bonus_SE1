package org.example;

import org.junit.Assert;
import org.junit.Test;

public class FieldTest {
    Field field1 = new Field(Field.FieldStatus.COVERED);
    Field field2 = new Field(Field.FieldStatus.BOMB);
    Field field3 = new Field(Field.FieldStatus.UNCOVERED);
    Field field4 = new Field(Field.FieldStatus.FLAG);

    @Test
    public void testGetStatus(){
        Assert.assertEquals(Field.FieldStatus.COVERED, field1.getStatus());
        Assert.assertEquals(Field.FieldStatus.BOMB, field2.getStatus());
        Assert.assertEquals(Field.FieldStatus.UNCOVERED, field3.getStatus());
        Assert.assertEquals(Field.FieldStatus.FLAG, field4.getStatus());
    }

    @Test
    public void testSetStatus(){
        field1.setStatus(Field.FieldStatus.BOMB);
        field2.setStatus((Field.FieldStatus.UNCOVERED));
        field3.setStatus(Field.FieldStatus.FLAG);
        field4.setStatus(Field.FieldStatus.COVERED);

        Assert.assertEquals(Field.FieldStatus.BOMB, field1.getStatus());
        Assert.assertEquals(Field.FieldStatus.UNCOVERED, field2.getStatus());
        Assert.assertEquals(Field.FieldStatus.FLAG, field3.getStatus());
        Assert.assertEquals(Field.FieldStatus.COVERED, field4.getStatus());
    }

}
