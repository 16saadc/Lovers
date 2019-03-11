import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        UserState sober_longTerm = new UserState(RelationshipGoal.LONG_TERM, Sobriety.SOBER, MateState.FRIENDS, Environment.INVITING);
        UserState drunk_Hookup = new UserState(RelationshipGoal.ONE_NIGHT, Sobriety.DRUNK, MateState.FAMILIAR, Environment.INVITING);
        UserState drunk_Uninviting = new UserState(RelationshipGoal.ONE_NIGHT, Sobriety.DRUNK, MateState.FAMILIAR, Environment.NO_GO);

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

        RelationshipStatus singleStatus = new RelationshipStatus(Status.SINGLE, Loyalty.NEVER_CHEAT);

        ShownInterest averageShownInterest = new ShownInterest(Level.MEDIUM);
        Confidence averageConfidence = new Confidence(Level.MEDIUM);

        Intelligence mediumIntelligence = new Intelligence(Education.AVERAGE_STUDENT, ConvoTopic.IMPORTANT_ISSUES);

        SocialStatus averageSocialStatus = new SocialStatus(Popularity.AVERAGE, Education.AVERAGE_STUDENT, singleStatus);

        PotentialMate attractiveMate = new PotentialMate(goodDate, averageShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "David");
        PotentialMate semiAttractive = new PotentialMate(goodHookup, averageShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "Katie");
        PotentialMate notAttractive = new PotentialMate(low, averageShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "Paul");


        MateFinder mateFinder = new MateFinder(attractiveMate, sober_longTerm);
        MateFinder mateFinder2 = new MateFinder(semiAttractive, drunk_Hookup);
        MateFinder mateFinder3 = new MateFinder(notAttractive, drunk_Uninviting);


        //sober, friends with mate, inviting environment, looking for long term, attractive mate:
        System.out.println(mateFinder.getPotentialMate().getName() + ": " + mateFinder.decide());

        //drunk, not looking for long term, inviting environment, attractive mate:
        System.out.println(mateFinder2.getPotentialMate().getName() + ": " + mateFinder2.decide());

        //drunk, not looking for long term, not a good environment, unattractive mate
        System.out.println(mateFinder3.getPotentialMate().getName() + ": " + mateFinder3.decide());

    }
}
