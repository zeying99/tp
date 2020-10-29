package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.quiz.Mcq;
import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.TrueFalse;

/**
 * An UI component that displays information of a {@code Flashcard}.
 */
public class QuizCard extends UiPart<Region> {

    private static final String FXML = "QuizListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Question question;

    @FXML
    private HBox cardPane;
    @FXML
    private Label prompt;
    @FXML
    private Label id;
    @FXML
    private FlowPane options;

    /**
     * Creates a {@code PersonCode} with the given {@code Flashcard} and index to display.
     */
    public QuizCard(Question question, int displayedIndex) {
        super(FXML);
        this.question = question;
        id.setText(displayedIndex + ". ");
        prompt.setText(question.getPrompt());
        if (question instanceof Mcq) {
            Mcq mcqQuestion = (Mcq) question;
            mcqQuestion.getOptions().stream()
                    .forEach(option -> options.getChildren().add(new Label(option)));
        } else {
            TrueFalse.OPTIONS.stream()
                    .forEach(option -> options.getChildren().add(new Label(option)));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof QuizCard)) {
            return false;
        }

        // state check
        QuizCard card = (QuizCard) other;
        return id.getText().equals(card.id.getText())
                && question.equals(card.question);
    }
}
