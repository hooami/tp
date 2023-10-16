package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.ui.Ui;
import seedu.duke.classes.StateManager;

import java.util.HashMap;

public class DeleteCommand extends Command {
    private static final String INVALID_TYPE_FORMAT = "I'm sorry, you need to specify a type in the format " +
            "'/type in' or '/type out'";
    private static final String INVALID_VALUE = "Check that the transaction that you wish to delete actually exists " +
            "or that you have entered a valid number";
    private StateManager list = StateManager.getStateManager();

    private Ui ui;

    public DeleteCommand(String description, HashMap<String, String> args) {
        super(description, args);
    }

    @Override
    public void execute(Ui ui) throws DukeException {
        this.ui = ui;
        int deleteNumber = Parser.parseInt(getDescription());
        if (getDescription() == null || deleteNumber == -1) {
            throw new DukeException(INVALID_VALUE);
        }
        delete(deleteNumber);
    }

    private void delete(int deleteNumber) throws DukeException {
        if (!getArgs().containsKey("type")) {
            throw new DukeException(INVALID_TYPE_FORMAT);
        }
        boolean txType = Parser.checkType(getArg("type"));
        if (txType) {
            deleteIncome(deleteNumber);
        } else {
            deleteExpense(deleteNumber);
        }
    }

    private void deleteIncome(int deleteNumber) throws DukeException {
        if (list.getIncome(deleteNumber) == null) {
            throw new DukeException(INVALID_VALUE);
        }
        String description = list.getIncome(deleteNumber).getTransaction().getDescription();
        boolean success = list.removeIncome(deleteNumber);
        if (success) {
            ui.printDelete(true, deleteNumber, description);
        } else {
            throw new DukeException(INVALID_VALUE);
        }
    }

    private void deleteExpense(int deleteNumber) throws DukeException {
        if (list.getExpense(deleteNumber) == null) {
            throw new DukeException(INVALID_VALUE);
        }
        String description = list.getExpense(deleteNumber).getTransaction().getDescription();
        boolean success = list.removeExpense(deleteNumber);
        if (success) {
            ui.printDelete(false, deleteNumber, description);
        } else {
            throw new DukeException(INVALID_VALUE);
        }
    }
}
