import java.util.List;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {

        UserState sober_longTerm = new UserState(RelationshipGoal.LONG_TERM, Sobriety.SOBER, MateState.FRIENDS);

        PhysicalAttraction highPhysicalAttraction = new PhysicalAttraction(Level.HIGH);
        PhysicalAttraction lowPhysicalAttraction = new PhysicalAttraction(Level.LOW);


        // Confidence highConfidence = new Confidence(Level.HIGH);
        // Confidence lowConfidence = new Confidence(Level.LOW);

        PersonalityAttraction highPersonality = new PersonalityAttraction(Level.HIGH);
        PersonalityAttraction lowPersonality = new PersonalityAttraction(Level.LOW);

        Attraction goodDate = new Attraction(lowPhysicalAttraction, highPersonality);
        Attraction goodHookup = new Attraction(highPhysicalAttraction, lowPersonality);
        Attraction low = new Attraction(lowPhysicalAttraction, lowPersonality);
        Attraction goodEverything = new Attraction(highPhysicalAttraction, highPersonality);


        PotentialMate attractiveMate = new PotentialMate(goodDate, "David");
        PotentialMate semiAttractive = new PotentialMate(goodHookup, "Katie");
        PotentialMate notAttractive = new PotentialMate(low, "Vish");


        MateFinder mateFinder = new MateFinder(attractiveMate, sober_longTerm);

        //input current sobriety(int: 1-10) and goals for potential mate(boolean: long term relationship?)

        //very drunk and not looking for long term
        System.out.println(mateFinder.getPotentialMate().getName() + ": " + mateFinder.decide());
        //sober and looking for long term relationship
        //System.out.println(mateFinder.decide(10, true).getName());

    }
}