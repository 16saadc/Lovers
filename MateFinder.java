import java.util.List;
import java.util.ArrayList;
// import PotentialMate;
import Attraction;
import PersonalityAttraction;
import PhysicalAttraction;

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
     * returns the best potential mate to make a move on
     */

    public PotentialMate decide(int sobriety, boolean longtermDate) {
        List<PotentialMate> potentialMates = getPotentialMates();

        PotentialMate bestOption;

        int bestScore = -100;

        for(PotentialMate mate: potentialMates) {
            Attraction curAttraction = mate.getAttraction();
            int personality = curAttraction.getPersonalityAttraction().getConfidence().getLevel();
            int physical = curAttraction.getPhysicalAttraction().getLevel();
    
            int score = 0;

            if(longTermDate) {
                //weight personality high if going for long term relationship
                score = score + personality * 2;
            } else {
                score = score + personality;
            }

            if(sobriety < 5) {
                //if you aren't in the right state of mind everyone becomes more attractive and personality is not as important
                score = score + physical*2 - personality/2;
            } else {
                score = score + physical;
            }

            if(score > bestScore) {
                bestScore = score;
                bestOption = mate;
            }

        }
        return bestOption;

    }


}