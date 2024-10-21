package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.student.Student;

import java.nio.file.Path;
import java.util.function.Predicate;

public class UndoCommandTest {
    private CommandStack commandStack;
    private Model model;

    @BeforeEach
    public void setUp() {
        commandStack = CommandStack.getInstance();
        model = new ModelStub();
    }

    @Test
    public void execute_empty_stack() {
        assertEquals("There are no commands to undo",
                new UndoCommand().execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_non_empty_stack_not_undoable_command() {
        Command command = new NotUndoableCommandStub();
        commandStack.push(command);
        assertEquals("The previous command is not undoable",
                new UndoCommand().execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_non_empty_stack_undoable_command() {
        Command command = new UndoableCommandStub();
        commandStack.push(command);
        assertEquals(UndoCommand.MESSAGE_SUCCESS,
                new UndoCommand().execute(model).getFeedbackToUser());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPersonByName(Name name) {
            return null;
        }

        @Override
        public Student getStudentByName(Name name) {
            return null;
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

    }

    private class NotUndoableCommandStub extends Command {
        @Override
        public CommandResult execute(Model model) {
            return new CommandResult("Not Undoable command executed");
        }

        @Override
        public boolean undo(Model model) {
            return false;
        }
    }

    private class UndoableCommandStub extends Command {
        @Override
        public CommandResult execute(Model model) {
            return new CommandResult("Undoable command executed");
        }

        @Override
        public boolean undo(Model model) {
            return true;
        }
    }
}
