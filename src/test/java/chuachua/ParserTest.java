package chuachua;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    //normal cases
    @Test
    void parse_bye_returnsByeArray() throws Exception {
        assertArrayEquals(new String[]{"bye"}, Parser.parse("bye"));
    }

    @Test
    void parse_markWithNumber_returnsCommandAndArg() throws Exception {
        assertArrayEquals(new String[]{"mark", "2"}, Parser.parse("mark 2"));
    }

    @Test
    void parse_deadlineValid_returnsCommandDescBy() throws Exception {
        assertArrayEquals(
                new String[]{"deadline", "return book", "02/02/2222 1500"},
                Parser.parse("deadline return book /by 02/02/2222 1500")
        );
    }

    @Test
    void parse_eventValid_returnsCommandDescFromTo() throws Exception {
        assertArrayEquals(
                new String[]{"event", "project meeting", "02/02/2222 1500", "03/02/2222 1500"},
                Parser.parse("event project meeting /from 02/02/2222 1500 /to 03/02/2222 1500")
        );
    }

    //handling errors
    @Test
    void parse_markMissingNumber_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("mark"));
    }

    @Test
    void parse_deadlineMissingBy_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () ->
                Parser.parse("deadline return book")
        );
    }

    @Test
    void parse_eventMissingTo_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () ->
                Parser.parse("event meeting /from 02/02/2222 1500")
        );
    }

    @Test
    void parse_unknownCommand_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () ->
                Parser.parse("testing 122345")
        );
    }

    @Test
    void parseSavedTask_unknownType_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                Parser.parseSavedTask("X | 0 | ???")
        );
    }
}
