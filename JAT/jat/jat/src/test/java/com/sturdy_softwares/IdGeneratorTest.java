package com.sturdy_softwares;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IdGeneratorTest {

    private IdGenerator idGen;

    @BeforeEach
    public void setUp() {
        idGen = new IdGenerator();
    }

    @Test
    public void generateId_returnsValueInRange() {
        int id = idGen.generateId();
        assertTrue(id >= 100_000_000 && id <= 999_999_999, "ID should be 9 digits");
    }

    @Test
    public void generateId_producesUniqueValues() {
        int id1 = idGen.generateId();
        int id2 = idGen.generateId();
        assertNotEquals(id1, id2, "Subsequent generated IDs should not be equal");
    }
}
