package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate("first");
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate("second");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate("first");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different flashcard -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new FlashcardBuilder().withTitle("Alice Bob").build()));

        // Multiple keywords
        //        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        //        assertTrue(predicate.test(new FlashcardBuilder().withTitle("Alice Bob").build()));

        // Only one matching keyword
        //        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        //        assertTrue(predicate.test(new FlashcardBuilder().withTitle("Alice Carol").build()));

        // Mixed-case keywords
        //        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        //        assertTrue(predicate.test(new FlashcardBuilder().withTitle("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("");
        assertFalse(predicate.test(new FlashcardBuilder().withTitle("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate("Carol");
        assertFalse(predicate.test(new FlashcardBuilder().withTitle("Alice Bob").build()));

        // Keywords match address, but does not match name
        //        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "Main", "Street"));
        //        assertFalse(predicate.test(new FlashcardBuilder().withTitle("Alice")
        //                .withDefinition("Main Street").build()));
    }
}
