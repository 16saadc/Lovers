import java.util.List;

// possible more detailed move results


public class MateFinder {

    /**
     * variables:
     *
     * Current goal: {hookup, emotional connection, long-term, o real intention (just accepting what happens)}
     * Drunkenness: {sober, buzzed, drunk, lit, tanked, sloshed, housed}
     * What race(s) am I generally attracted to {White, African American, Asian, latino/a, etc.}
     * Are we in an environment where making a move is encouraged? {don’t even think about it, be cautious, yeah sure, it would be weird if you didn’t}
     * Potential Mates: [mate A, mate B, …]
     */
    private PotentialMate potentialMate;
    private UserState currentState;

    public MateFinder(PotentialMate mate, UserState currentState) {
        potentialMate = mate;
        this.currentState = currentState;
    }

    public void setPotentialMate(PotentialMate mate) {
        potentialMate = mate;
    }

    public PotentialMate getPotentialMate() {
        return potentialMate;
    }

    public void setCurrentState(UserState state) {
        currentState = state;
    }

    public UserState getCurrentState() {
        return currentState;
    }

    /**
     * methods:
     * assess the candidate's state
     * Evaluate most important traits based on state
     *
     * returns the move to make
     */

    public Move decide() {

        if (currentState.getSobriety().equals(Sobriety.SOBER)) {
            return Move.ASK_OUT;
        }
        return Move.DO_NOT_APPROACH;


    }


}