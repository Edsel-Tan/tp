package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an assignment's status
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Status should either be Y or N (case insensitive)";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final LocalDate deadline;

    /**
     * Constructs a {@code Deadline}
     *
     * @param deadline A valid deadline
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.deadline = LocalDate.parse(deadline, DATE_TIME_FORMATTER);
    }

    /**
     * Returns if given string is a valid deadline
     */
    public static boolean isValidDeadline(String deadline) {
        try {
            DATE_TIME_FORMATTER.parse(deadline);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}