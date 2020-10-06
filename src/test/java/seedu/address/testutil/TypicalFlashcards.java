package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEFINITION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEFINITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Flashcard;

/**
 * A utility class containing a list of {@code Flashcard} objects to be used in tests.
 */
public class TypicalFlashcards {

    public static final Flashcard ALICE = new FlashcardBuilder().withName("Alice Pauline")
            .withDefinition("<DEFINITION PLACEHOLDER> 1")
            .withTags("friends").build();
    public static final Flashcard BENSON = new FlashcardBuilder().withName("Benson Meier")
            .withDefinition("<DEFINITION PLACEHOLDER> 2")
            .withTags("owesMoney", "friends").build();
    public static final Flashcard CARL =
            new FlashcardBuilder().withName("Carl Kurz").withDefinition("<DEFINITION PLACEHOLDER> 3").build();
    public static final Flashcard DANIEL =
            new FlashcardBuilder().withName("Daniel Meier")
                    .withDefinition("<DEFINITION PLACEHOLDER> 4").withTags("friends").build();
    public static final Flashcard ELLE = new FlashcardBuilder().withName("Elle Meyer")
            .withDefinition("<DEFINITION PLACEHOLDER> 5").build();
    public static final Flashcard FIONA =
            new FlashcardBuilder().withName("Fiona Kunz").withDefinition("<DEFINITION PLACEHOLDER> 6").build();
    public static final Flashcard GEORGE =
            new FlashcardBuilder().withName("George Best").withDefinition("<DEFINITION PLACEHOLDER> 7").build();

    // Manually added
    public static final Flashcard HOON =
            new FlashcardBuilder().withName("Hoon Meier").withDefinition("<DEFINITION PLACEHOLDER> 8").build();
    public static final Flashcard IDA =
            new FlashcardBuilder().withName("Ida Mueller").withDefinition("<DEFINITION PLACEHOLDER> 9").build();

    // Manually added - Flashcard's details found in {@code CommandTestUtil}
    public static final Flashcard AMY = new FlashcardBuilder().withName(VALID_NAME_AMY)
            .withDefinition(VALID_DEFINITION_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Flashcard BOB = new FlashcardBuilder().withName(VALID_NAME_BOB)
            .withDefinition(VALID_DEFINITION_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFlashcards() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical flashcards.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Flashcard flashcard : getTypicalPersons()) {
            ab.addFlashcard(flashcard);
        }
        return ab;
    }

    public static List<Flashcard> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
