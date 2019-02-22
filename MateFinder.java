import java.util.List;

// possible more detailed move results
enum Move {
    FLIRT, DO_NOT_APPROACH, TALK_MORE, ASK_OUT
}

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

    public MateFinder(PotentialMate mate) {
        potentialMate = mate;
    }

    public void setPotentialMate(PotentialMate mate) {
        potentialMate = mates;
    }

    public PotentialMate getPotentialMates() {
        return potentialMate;
    }

    /**
     * methods:
     * Go through list of candidates and assess each one
     * Evaluate most important traits
     * Evaluate the most important traits as of this moment (inputs: current goal, drunkenness)
     *
     * returns the best potential mate to make a move on
     */

    public Move decide(int sobriety, boolean longtermDate) {
        List<PotentialMate> potentialMates = getPotentialMates();

        PotentialMate bestOption = potentialMates.get(0);

        int bestScore = -100;

        for (PotentialMate mate: potentialMates) {
            int personality = mate.getAttraction().getPersonalityAttraction().getConfidence().getLevel();
            int physical = mate.getAttraction().getPhysicalAttraction().getLevel();

            int score = 0;

            if (longtermDate) {
                //weight personality high if going for long term relationship
                score = score + personality * 2;
            } else {
                score = score + personality;
            }

            if (sobriety < 5) {
                //if you aren't in the right state of mind everyone becomes more attractive and personality is not as important
                score = score + physical*2 - personality/2;
            } else {
                score = score + physical;
            }

            if (score > bestScore) {
                bestScore = score;
                bestOption = mate;
            }

        }
        return bestOption;

    }


}