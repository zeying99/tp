package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEFINITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.ALICE;
import static seedu.address.testutil.TypicalFlashcards.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateFlashcardException;
import seedu.address.model.person.exceptions.FlashcardNotFoundException;
import seedu.address.testutil.FlashcardBuilder;

public class UniqueFlashcardListTest {

    private final UniqueFlashcardList uniqueFlashcardList = new UniqueFlashcardList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueFlashcardList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueFlashcardList.add(ALICE);
        assertTrue(uniqueFlashcardList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFlashcardList.add(ALICE);
        Flashcard editedAlice = new FlashcardBuilder(ALICE)
                .withDefinition(VALID_DEFINITION_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(uniqueFlashcardList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueFlashcardList.add(ALICE);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.setFlashcard(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueFlashcardList.add(ALICE);
        uniqueFlashcardList.setFlashcard(ALICE, ALICE);
        UniqueFlashcardList expectedUniquePersonList = new UniqueFlashcardList();
        expectedUniquePersonList.add(ALICE);
        assertEquals(expectedUniquePersonList, uniqueFlashcardList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueFlashcardList.add(ALICE);
        Flashcard editedAlice = new FlashcardBuilder(ALICE)
                .withDefinition(VALID_DEFINITION_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueFlashcardList.setFlashcard(ALICE, editedAlice);
        UniqueFlashcardList expectedUniquePersonList = new UniqueFlashcardList();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniqueFlashcardList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueFlashcardList.add(ALICE);
        uniqueFlashcardList.setFlashcard(ALICE, BOB);
        UniqueFlashcardList expectedUniquePersonList = new UniqueFlashcardList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniqueFlashcardList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueFlashcardList.add(ALICE);
        uniqueFlashcardList.add(BOB);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.setFlashcard(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueFlashcardList.add(ALICE);
        uniqueFlashcardList.remove(ALICE);
        UniqueFlashcardList expectedUniquePersonList = new UniqueFlashcardList();
        assertEquals(expectedUniquePersonList, uniqueFlashcardList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((UniqueFlashcardList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueFlashcardList.add(ALICE);
        UniqueFlashcardList expectedUniquePersonList = new UniqueFlashcardList();
        expectedUniquePersonList.add(BOB);
        uniqueFlashcardList.setFlashcards(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniqueFlashcardList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((List<Flashcard>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueFlashcardList.add(ALICE);
        List<Flashcard> flashcardList = Collections.singletonList(BOB);
        uniqueFlashcardList.setFlashcards(flashcardList);
        UniqueFlashcardList expectedUniquePersonList = new UniqueFlashcardList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniqueFlashcardList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Flashcard> listWithDuplicateFlashcards = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateFlashcardException.class, () ->
                uniqueFlashcardList.setFlashcards(listWithDuplicateFlashcards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFlashcardList.asUnmodifiableObservableList().remove(0));
    }
}
