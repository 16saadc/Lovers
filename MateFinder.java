import java.util.List;
import java.util.ArrayList;
// import PotentialMate;

public class MateFinder {

    /**
     * variables:
     *
     * Current goal: {hookup, emotional connection, long-term, no real intention (just accepting what happens)}
     * Drunkenness: {sober, buzzed, drunk, lit, tanked, sloshed, housed}
     * What race(s) am I generally attracted to {White, African American, Asian, latino/a, etc.}
     * Are we in an environment where making a move is encouraged? {don’t even think about it, be cautious, yeah sure, it would be weird if you didn’t}
     * Potential Mates: [mate A, mate B, …]
     */
    List<PotentialMate> potential_mates;

    public MateFinder() {
        potential_mates = new ArrayList<PotentialMate>();
    }


    /**
     * methods:
     * Go through list of candidates and assess each one
     * Evaluate most important traits
     * Evaluate the most important traits as of this moment (inputs: current goal, drunkenness)
     * Make a move
     */

}