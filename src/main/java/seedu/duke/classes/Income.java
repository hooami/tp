package seedu.duke.classes;

public class Income {
    private Transaction transaction;
    private Goal goal;

    public Income(Transaction transaction, Goal goal) {
        this.transaction = transaction;
        this.goal = goal;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
}