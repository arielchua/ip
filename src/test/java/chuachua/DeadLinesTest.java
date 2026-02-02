package chuachua;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadLinesTest {
    @Test
    void markDone_taskIsMarked() {
        DeadLines d = new DeadLines("return book", "02/02/2026 1500");
        d.mark();
        assertTrue(d.status);
    }

    @Test
    void unMarkDone_taskIsUnMarked() {
        DeadLines d = new DeadLines("return book", "02/02/2026 1500");
        d.mark();
        d.unmark();
        assertFalse(d.status);
    }
}
