package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
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
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("friends").build();
    public static final Flashcard BENSON = new FlashcardBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTags("owesMoney", "friends").build();
    public static final Flashcard CARL =
            new FlashcardBuilder().withName("Carl Kurz").withAddress("wall street").build();
    public static final Flashcard DANIEL =
            new FlashcardBuilder().withName("Daniel Meier").withAddress("10th street").withTags("friends").build();
    public static final Flashcard ELLE = new FlashcardBuilder().withName("Elle Meyer")
            .withAddress("michegan ave").build();
    public static final Flashcard FIONA =
            new FlashcardBuilder().withName("Fiona Kunz").withAddress("little tokyo").build();
    public static final Flashcard GEORGE =
            new FlashcardBuilder().withName("George Best").withAddress("4th street").build();

    // Manually added
    public static final Flashcard HOON =
            new FlashcardBuilder().withName("Hoon Meier").withAddress("little india").build();
    public static final Flashcard IDA =
            new FlashcardBuilder().withName("Ida Mueller").withAddress("chicago ave").build();

    // Manually added - Flashcard's details found in {@code CommandTestUtil}
    public static final Flashcard AMY = new FlashcardBuilder().withName(VALID_NAME_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Flashcard BOB = new FlashcardBuilder().withName(VALID_NAME_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
