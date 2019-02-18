import java.util.List;
import java.util.ArrayList;
// import PotentialMate;

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
    private List<PotentialMate> potential_mates;

    public MateFinder(List<PotentialMate> mates) {
        potential_mates = mates;
    }

    public void setPotentialMates(List<PotentialMate> mates) {
        potential_mates = mates;
    }

    public List<PotentialMate> getPotentialMates() {
        return potential_mates;
    }

    /**
     * methods:
     * Go through list of candidates and assess each one
     * Evaluate most important traits
     * Evaluate the most important traits as of this moment (inputs: current goal, drunkenness)
     * Make a move
     */

    public void decide(PotentialMate mate) {
        // some way to decide
    }


}