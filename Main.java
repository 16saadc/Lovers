import java.util.List;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {

        PhysicalAttraction highPhysicalAttraction = new PhysicalAttraction(10);
        PhysicalAttraction medPhysicalAttraction = new PhysicalAttraction(5);


        Confidence highConfidence = new Confidence(8);
        Confidence lowConfidence = new Confidence(2);

        PersonalityAttraction highPersonality = new PersonalityAttraction(highConfidence);
        PersonalityAttraction lowPersonality = new PersonalityAttraction(lowConfidence);

        Attraction goodDate = new Attraction(medPhysicalAttraction, highPersonality);
        Attraction goodHookup = new Attraction(highPhysicalAttraction, lowPersonality);
        Attraction low = new Attraction(medPhysicalAttraction, lowPersonality);


        PotentialMate attractiveMate = new PotentialMate(allHigh);
        PotentialMate semiAttractive = new PotentialMate(medium);
        PotentialMate notAttractive = new PotentialMate(low);

        List<PotentialMate> mateList = new ArrayList<>();
        mateList.add(goodDate);
        mateList.add(goodHookup);
        mateList.add(low);

        MateFinder mateFinder = new MateFinder(mateList);

        //input current sobriety(int: 1-10) and goals for potential mate(boolean: long term relationship?)
        
        //very drunk and not looking for long term
        mateFinder.decide(3, false);
        //sober and looking for long term relationship
        mateFinder.decide(10, true);

    }
}