package turtle.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import turtle.commands.ByeCommand;
import turtle.commands.Command;
import turtle.commands.DeadlineCommand;
import turtle.commands.DeleteCommand;
import turtle.commands.EventCommand;
import turtle.commands.FindCommand;
import turtle.commands.ListCommand;
import turtle.commands.MarkCommand;
import turtle.commands.TodoCommand;
import turtle.commands.UnmarkCommand;
import turtle.exceptions.TurtleException;

public class ParserTest {

    @Test
    public void parseCommand_byeValid_success() throws TurtleException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("bye");
        assertInstanceOf(ByeCommand.class, command);
        assertEquals("ByeCommand{}", command.toString());
    }

    @Test
    public void parseCommand_listValidNoSort_success() throws TurtleException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("list");
        assertInstanceOf(ListCommand.class, command);
        assertEquals("ListCommand{shouldSort=false}", command.toString());
    }

    @Test
    public void parseCommand_listValidSort_success() throws TurtleException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("list sort");
        assertInstanceOf(ListCommand.class, command);
        assertEquals("ListCommand{shouldSort=true}", command.toString());
    }

    @Test
    public void parseCommand_listInvalidOption_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("list abc");
            fail();
        } catch (TurtleException e) {
            assertEquals("Unknown list option 'abc'\nCorrect Syntax: list [sort]", e.getMessage());
        }
    }

    @Test
    public void parseCommand_listInvalidSyntax_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("list sort abc");
            fail();
        } catch (TurtleException e) {
            assertEquals("Invalid syntax\nCorrect Syntax: list [sort]", e.getMessage());
        }
    }

    @Test
    public void parseCommand_markValid_success() throws TurtleException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("mark 2");
        assertInstanceOf(MarkCommand.class, command);
        assertEquals("MarkCommand{index=2}", command.toString());
    }

    @Test
    public void parseCommand_markInvalidSyntax_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("mark");
            fail();
        } catch (TurtleException e) {
            assertEquals("Invalid syntax\nCorrect Syntax: mark <index>", e.getMessage());
        }
    }

    @Test
    public void parseCommand_markInvalidIndex_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("mark a");
            fail();
        } catch (TurtleException e) {
            assertEquals("Unable to parse <index> 'a'\nCorrect Syntax: mark <index>", e.getMessage());
        }
    }

    @Test
    public void parseCommand_unmarkValid_success() throws TurtleException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("unmark 2");
        assertInstanceOf(UnmarkCommand.class, command);
        assertEquals("UnmarkCommand{index=2}", command.toString());
    }

    @Test
    public void parseCommand_unmarkInvalidSyntax_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("unmark");
            fail();
        } catch (TurtleException e) {
            assertEquals("Invalid syntax\nCorrect Syntax: unmark <index>", e.getMessage());
        }
    }

    @Test
    public void parseCommand_unmarkInvalidIndex_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("unmark a");
            fail();
        } catch (TurtleException e) {
            assertEquals("Unable to parse <index> 'a'\nCorrect Syntax: unmark <index>", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deleteValid_success() throws TurtleException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("delete 2");
        assertInstanceOf(DeleteCommand.class, command);
        assertEquals("DeleteCommand{index=2}", command.toString());
    }

    @Test
    public void parseCommand_deleteInvalidSyntax_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("delete");
            fail();
        } catch (TurtleException e) {
            assertEquals("Invalid syntax\nCorrect Syntax: delete <index>", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deleteInvalidIndex_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("delete a");
            fail();
        } catch (TurtleException e) {
            assertEquals("Unable to parse <index> 'a'\nCorrect Syntax: delete <index>", e.getMessage());
        }
    }

    @Test
    public void parseCommand_todoValid_success() throws TurtleException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("todo read book");
        assertInstanceOf(TodoCommand.class, command);
        assertEquals("TodoCommand{task=[T][ ] read book}", command.toString());
    }

    @Test
    public void parseCommand_todoInvalidSyntax_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("todo");
            fail();
        } catch (TurtleException e) {
            assertEquals("Invalid syntax\nCorrect Syntax: todo <task_name>", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineValid_success() throws TurtleException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("deadline submit report /by 2026-02-14");
        assertInstanceOf(DeadlineCommand.class, command);
        assertEquals("DeadlineCommand{task=[D][ ] submit report (by: Feb 14 2026)}", command.toString());
    }

    @Test
    public void parseCommand_deadlineMissingBy_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("deadline submit report");
            fail();
        } catch (TurtleException e) {
            assertEquals("Unable to find '/by' section\nCorrect Syntax: deadline <task_name> /by <deadline>",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineMissingTaskName_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("deadline /by 2026-02-14");
            fail();
        } catch (TurtleException e) {
            assertEquals("Unable to find <task_name>\nCorrect Syntax: deadline <task_name> /by <deadline>",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineMissingDeadline_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("deadline submit report /by");
            fail();
        } catch (TurtleException e) {
            assertEquals("Unable to find <deadline>\nCorrect Syntax: deadline <task_name> /by <deadline>",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineInvalidDeadline_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("deadline submit report /by tomorrow");
            fail();
        } catch (TurtleException e) {
            assertEquals("Unable to parse <deadline> 'tomorrow'\nCorrect Syntax: deadline <task_name> /by <deadline>",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventValid_success() throws TurtleException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("event team retreat /from 2026-03-01 /to 2026-03-03");
        assertInstanceOf(EventCommand.class, command);
        assertEquals("EventCommand{task=[E][ ] team retreat (from: Mar 1 2026, to: Mar 3 2026)}", command.toString());
    }

    @Test
    public void parseCommand_eventMissingFrom_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("event team retreat /to 2026-03-03");
            fail();
        } catch (TurtleException e) {
            assertEquals(
                    "Unable to find '/from' section\nCorrect Syntax: event <task_name> /from <from_date> /to <to_date>",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventMissingTo_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("event team retreat /from 2026-03-01");
            fail();
        } catch (TurtleException e) {
            assertEquals(
                    "Unable to find '/to' section\nCorrect Syntax: event <task_name> /from <from_date> /to <to_date>",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventInvalidSectionOrder_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("event team retreat /to 2026-03-03 /from 2026-03-01");
            fail();
        } catch (TurtleException e) {
            assertEquals(
                    "Expected '/from' before '/to'\nCorrect Syntax: event <task_name> /from <from_date> /to <to_date>",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventMissingTaskName_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("event /from 2026-03-01 /to 2026-03-03");
            fail();
        } catch (TurtleException e) {
            assertEquals(
                    "Unable to find <task_name>\nCorrect Syntax: event <task_name> /from <from_date> /to <to_date>",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventMissingFromDate_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("event team retreat /from /to 2026-03-03");
            fail();
        } catch (TurtleException e) {
            assertEquals(
                    "Unable to find <from_date>\nCorrect Syntax: event <task_name> /from <from_date> /to <to_date>",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventMissingToDate_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("event team retreat /from 2026-03-01 /to");
            fail();
        } catch (TurtleException e) {
            assertEquals("Unable to find <to_date>\nCorrect Syntax: event <task_name> /from <from_date> /to <to_date>",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventInvalidFromDate_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("event team retreat /from start /to 2026-03-03");
            fail();
        } catch (TurtleException e) {
            assertEquals(
                    "Unable to parse <from_date> 'start'\nCorrect Syntax: "
                            + "event <task_name> /from <from_date> /to <to_date>",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventInvalidToDate_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("event team retreat /from 2026-03-01 /to end");
            fail();
        } catch (TurtleException e) {
            assertEquals(
                    "Unable to parse <to_date> 'end'\nCorrect Syntax: "
                            + "event <task_name> /from <from_date> /to <to_date>",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_findValid_success() throws TurtleException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("find report");
        assertInstanceOf(FindCommand.class, command);
        assertEquals("FindCommand{searchStr='report'}", command.toString());
    }

    @Test
    public void parseCommand_findInvalidSyntax_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("find");
            fail();
        } catch (TurtleException e) {
            assertEquals("Invalid syntax\nCorrect Syntax: find <search_str>", e.getMessage());
        }
    }

    @Test
    public void parseCommand_unknownCommand_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("unknown");
            fail();
        } catch (TurtleException e) {
            assertEquals("Unknown command 'unknown'\nCorrect Syntax: help", e.getMessage());
        }
    }
}
