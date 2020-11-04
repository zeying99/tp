package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.Response;
import seedu.address.model.quiz.exceptions.InvalidQuestionAnswerException;


/**
 * Answers a question identified using it's displayed index from the quiz book.
 */
public class AnswerCommand extends Command{
    public static final String COMMAND_WORD = "answer";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Answers a question identified by the index number used in the displayed question list. \n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_ANSWER + "ANSWER\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ANSWER + "t";

    public static final String MESSAGE_ANSWER_SUCCESS = "Your answer has successfully been recorded.";

    public static final String MESSAGE_CURRENTLY_NOT_ATTEMPTING = "Please start an attempt before answering a " +
            "question!";

    private final Index index;
    private final String answer;

    /**
     * Creates an AnswerCommand to record the specified {@code Response}
     */
    public AnswerCommand(Index index, String answer) {
        requireAllNonNull(index, answer);
        this.index = index;
        this.answer = answer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Question> lastShownQuestionList = model.getQuizList();

        if (index.getZeroBased() >= lastShownQuestionList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
        }

        Question qn = lastShownQuestionList.get(index.getZeroBased());
        Response resp;

        try {
            resp = new Response(this.answer, qn);
            resp.markResponse();
        } catch (InvalidQuestionAnswerException e) {
            throw new CommandException(e.getMessage());
        }

        if (model.hasCurrentAttempt()) {
            model.recordResponse(resp);
            return new CommandResult(String.format(MESSAGE_ANSWER_SUCCESS, this.index.getZeroBased(), this.answer));
        } else {
            return new CommandResult(MESSAGE_CURRENTLY_NOT_ATTEMPTING);
        }
    }
}
