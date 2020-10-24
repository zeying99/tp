package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Flashcard;

/**
 * Flips a flashcard to show the definition.
 */
public class FlipCommand extends Command {

    public static final String COMMAND_WORD = "flip";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Flips the identified flashcard "
            + "by the index number used in the last flashcard listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_FLIP_FLASHCARD_SUCCESS = "Flipped Flashcard: %1$s";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";

    private final Index index;

    /**
     * Creates a FlipCommand to flip the flashcard with the specified {@code index}.
     * @param index
     */
    public FlipCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Flashcard flashcardToFlip = lastShownList.get(index.getZeroBased());
        model.flipFlashcard(flashcardToFlip);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_FLIP_FLASHCARD_SUCCESS, flashcardToFlip));
    }

    private String generateSuccessMessage(Flashcard flashcardToFlip) {
        String message = MESSAGE_FLIP_FLASHCARD_SUCCESS;
        return String.format(message, flashcardToFlip);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FlipCommand)) {
            return false;
        }

        // state check
        FlipCommand e = (FlipCommand) other;
        return index.equals(e.index);
    }

}
