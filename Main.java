
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static int confidenceImportance = 0;
    public static int physicalAttractionImportance = 0;
    public static int socialStatusImportance = 0;
    public static int auraImportance = 0;
    public static int intelligenceImportance = 0;
    public static int personalityImportance = 0;


    public static void main(String[] args) {


        //
        // create a for loop to go through all potential mates
        // Move move = mate.decide()
        // updateImportance(mate, move)
        //     maybe look at last 3 - 5 mates to update importances?
        //
        //     possibly tie emotion into importance -- if something specific makes you happy, make it more important




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
        ShownInterest highShownInterest = new ShownInterest(Level.HIGH);

        Confidence averageConfidence = new Confidence(Level.MEDIUM);
        Confidence highConfidence = new Confidence(Level.HIGH);

        Intelligence mediumIntelligence = new Intelligence(Education.AVERAGE_STUDENT, ConvoTopic.IMPORTANT_ISSUES);

        SocialStatus averageSocialStatus = new SocialStatus(Popularity.AVERAGE, Education.AVERAGE_STUDENT, singleStatus);

        PotentialMate attractiveMate = new PotentialMate(goodDate, averageShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "David");
        PotentialMate semiAttractive = new PotentialMate(goodHookup, averageShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "Katie");
        PotentialMate notAttractive = new PotentialMate(low, averageShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "Paul");

        PotentialMate attractiveConfidentMate = new PotentialMate(goodDate, averageShownInterest, highConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "Will");

        PotentialMate semiAttractiveWithHighInterest = new PotentialMate(goodHookup, highShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "Vishal");
        PotentialMate notAttractiveWithHighInterest = new PotentialMate(low, highShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "Vishal");


        MateFinder mateFinder = new MateFinder(attractiveMate, sober_longTerm);
        MateFinder mateFinder2 = new MateFinder(semiAttractive, drunk_Hookup);
        MateFinder mateFinder3 = new MateFinder(notAttractive, drunk_Uninviting);
        MateFinder mateFinder4 = new MateFinder(semiAttractiveWithHighInterest, drunk_Hookup);
        MateFinder mateFinder5 = new MateFinder(notAttractiveWithHighInterest, drunk_Uninviting);
        MateFinder mateFinder6 = new MateFinder(attractiveConfidentMate, sober_longTerm);
        MateFinder mateFinder7 = new MateFinder(notAttractive, drunk_Uninviting);


        //sober, friends with mate, inviting environment, looking for long term, attractive mate:
        System.out.println("Decision" + ": " + mateFinder.decide());

        //drunk, not looking for long term, inviting environment, attractive mate:
        System.out.println("Decision" + ": " + mateFinder2.decide());

        //drunk, not looking for long term, not a good environment, unattractive mate
        System.out.println("Decision" + ": " + mateFinder3.decide());

        System.out.println("Decision" + ": " + mateFinder4.decide());
        System.out.println("Decision" + ": " + mateFinder5.decide());
        System.out.println("Decision" + ": " + mateFinder6.decide());
        System.out.println("Decision" + ": " + mateFinder7.decide());





    }
}
