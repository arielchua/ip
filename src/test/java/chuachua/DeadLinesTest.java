package chuachua;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadLinesTest {
    @Test
    void markDone_taskIsMarked() {
        DeadLines d = new DeadLines("return book", "02/02/2026 1500");
        d.mark();
        assertTrue(d.status);
    }
    @Test
    void toString_doneDeadline_correctFormat() {
        DeadLines d = new DeadLines("return book", "02/02/2026 1500");
        d.mark();
        assertEquals("[D][X] return book (by: Feb 02 2026, 3:00pm)", d.toString());
    }


    @Test
    void unMarkDone_taskIsUnMarked() {
        DeadLines d = new DeadLines("return book", "02/02/2026 1500");
        d.mark();
        d.unmark();
        assertFalse(d.status);
    }
}
