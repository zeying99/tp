package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Flashcard;
import seedu.address.model.quiz.Question;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.PerformanceBook;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final ReadOnlyQuizBook readOnlyQuizBook = new SampleDataUtil().getSampleQuizBook();
    private final QuizBook quizBook = new QuizBook(readOnlyQuizBook);
    private final ObservableList<Question> filteredQuizList = this.quizBook.getQuestionList();
    private final UserPrefs userPrefs;
    private final FilteredList<Flashcard> filteredFlashcards;
    private boolean isQuizMode = false;
    private boolean hasCurrentAttempt = false;
    private PerformanceBook performanceBook;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFlashcards = new FilteredList<>(this.addressBook.getFlashcardList());

        try {
            performanceBook = new PerformanceBook();
        } catch (IOException e) {
            performanceBook = PerformanceBook.createDefaultPerformanceBook();
        }
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return addressBook.hasFlashcard(flashcard);
    }

    @Override
    public void deleteFlashcard(Flashcard target) {
        addressBook.removeFlashcard(target);
    }

    @Override
    public void flipFlashcard(Flashcard target) {
        addressBook.flipFlashcard(target);
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        addressBook.addFlashcard(flashcard);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_FLASHCARD);
    }

    @Override
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireAllNonNull(target, editedFlashcard);
        addressBook.setFlashcard(target, editedFlashcard);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Flashcard} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Flashcard> getFilteredPersonList() {
        return filteredFlashcards;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Flashcard> predicate) {
        requireNonNull(predicate);
        filteredFlashcards.setPredicate(predicate);
    }

    @Override
    public void sortFilteredPersonList(String sortOrder) {
        addressBook.sortFlashcard(sortOrder);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredFlashcards.equals(other.filteredFlashcards);
    }

    //=========== Filtered Question List Accessors =============================================================

    @Override
    public boolean getIsQuizMode() {
        return this.isQuizMode;
    }

    @Override
    public void flipQuizMode() {
        this.isQuizMode = !isQuizMode;
    }

    @Override
    public boolean hasCurrentAttempt() {
        return this.hasCurrentAttempt;
    }

    @Override
    public void startAttempt() {
        this.hasCurrentAttempt = true;
        quizBook.startAttempt();
    }

    public ObservableList<Question> getQuizList() {
        return this.filteredQuizList;
    }

    /**
     * Saves performance in performance book.
     */
    public void savePerformance() throws IOException {
        performanceBook.savePerformance();
    }
}
