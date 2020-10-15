package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Definition;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.Title;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Flashcard[] getSamplePersons() {
        return new Flashcard[] {
            new Flashcard(new Title("Quicksort"),
                    new Definition("Picks a pivot element A[q] and partitions the array into two subarrays:"
                    + " A[p, ..., q - 1] in which all elements are less than A[q], and A[q + 1, ..., r]"
                    + " in which all elements are greater than or equal to A[q]. The algorithm then sorts"
                    + " the subarrays A[p, ..., q - 1] and A[q + 1, ..., r] recursively until the entire"
                    + "array is sorted."),
                getTagSet("sorting")),
            new Flashcard(new Title("Breadth first search"),
                    new Definition("Visits all vertices in graph G that are k edges away from source vertex"
                    + " s before visiting any vertex k + 1 edges away. Algorithm repeats until no more vertices"
                    + " can be reached from s."),
                 getTagSet("searching")),
            new Flashcard(new Title("Chaining"),
                    new Definition("Technique for avoiding collisions in hash tables. The hash table is an array of"
                    + " linked lists and all key-value pairs mapping to the same index will be stored in linked list"
                    + " of that index."),
                getTagSet("hashing")),
            new Flashcard(new Title("Heap"),
                    new Definition("Data structure that is usually implemented with an array, and can be thought of"
                    + " as a tree. The root of the heap is the topmost element, and a leaf is a node at the bottom"
                    + " of the tree. In a max-heap, the parent node has a value that is greater than or equal to that"
                    + " of its children, whereas in a min-heap, the parent node has a value that is less than or"
                    + " equal to that of its children."),
                getTagSet("heaps"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Flashcard sampleFlashcard : getSamplePersons()) {
            sampleAb.addFlashcard(sampleFlashcard);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
