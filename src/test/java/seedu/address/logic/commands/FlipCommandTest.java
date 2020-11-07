package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalFlashcards.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Flashcard;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code FlipCommand}.
 */
public class FlipCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Flashcard flashcardToFlip = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FlipCommand flipCommand = new FlipCommand(INDEX_FIRST_PERSON);

        String expectedMessage = flipCommand.generateSuccessMessage(flashcardToFlip);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.flipFlashcard(flashcardToFlip);

        assertCommandSuccess(flipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        FlipCommand flipCommand = new FlipCommand(outOfBoundIndex);

        assertCommandFailure(flipCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Flashcard flashcardToFlip = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FlipCommand flipCommand = new FlipCommand(INDEX_FIRST_PERSON);

        String expectedMessage = flipCommand.generateSuccessMessage(flashcardToFlip);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.flipFlashcard(flashcardToFlip);

        assertCommandSuccess(flipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getFlashcardList().size());

        FlipCommand flipCommand = new FlipCommand(outOfBoundIndex);

        assertCommandFailure(flipCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        FlipCommand flipFirstCommand = new FlipCommand(INDEX_FIRST_PERSON);
        FlipCommand flipSecondCommand = new FlipCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(flipFirstCommand.equals(flipFirstCommand));

        // same values -> returns true
        FlipCommand flipFirstCommandCopy = new FlipCommand(INDEX_FIRST_PERSON);
        assertTrue(flipFirstCommand.equals(flipFirstCommandCopy));

        // different types -> returns false
        assertFalse(flipFirstCommand.equals(1));

        // null -> returns false
        assertFalse(flipFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(flipFirstCommand.equals(flipSecondCommand));
    }

}
