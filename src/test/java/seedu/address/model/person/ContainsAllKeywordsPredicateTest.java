package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class ContainsAllKeywordsPredicateTest {

    @Test
    public void equals() {
        ContainsAllKeywordsPredicate firstPredicate = new ContainsAllKeywordsPredicate(preparePredicateList(
                preparePredicate("name", "first")));
        ContainsAllKeywordsPredicate secondPredicate = new ContainsAllKeywordsPredicate(preparePredicateList(
                preparePredicate("name", "second")));

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        ContainsAllKeywordsPredicate firstPredicateCopy = new ContainsAllKeywordsPredicate(preparePredicateList(
                preparePredicate("name", "first")));
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different flashcard -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ContainsAllKeywordsPredicate predicate = new ContainsAllKeywordsPredicate(preparePredicateList(
                preparePredicate("name", "Alice")));
        assertTrue(predicate.test(new FlashcardBuilder().withTitle("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ContainsAllKeywordsPredicate predicate = new ContainsAllKeywordsPredicate(preparePredicateList(
                preparePredicate("name", "")));
        assertFalse(predicate.test(new FlashcardBuilder().withTitle("Alice").build()));

        // Non-matching keyword
        predicate = new ContainsAllKeywordsPredicate(preparePredicateList(
                preparePredicate("name", "Carol")));
        assertFalse(predicate.test(new FlashcardBuilder().withTitle("Alice Bob").build()));

        // Keywords match address, but does not match name
        predicate = new ContainsAllKeywordsPredicate(preparePredicateList(
                preparePredicate("name", "Main")));
        assertFalse(predicate.test(new FlashcardBuilder().withTitle("Alice")
                .withDefinition("Main Street").build()));
    }


    /**
     * Parses {@code userInput} into a {@code List<Predicate<Flashcard>>}.
     */
    @SafeVarargs
    private List<Predicate<Flashcard>> preparePredicateList(Predicate<Flashcard> ... predicates) {
        return Arrays.asList(predicates);
    }

    /**
     * Parses {@code userInput} into a {@code Predicate<Flashcard>}.
     */
    private Predicate<Flashcard> preparePredicate(String type, String userInput) {
        switch (type) {
        case "name":
            return new NameContainsKeywordsPredicate(userInput);
        case "tag":
            return new TagContainsKeywordsPredicate(userInput);
        case "priority":
            return new PriorityContainsKeywordsPredicate(userInput);
        default:
            return null;
        }
    }
}
