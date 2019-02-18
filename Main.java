import java.util.List;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {

        PhysicalAttraction highPhysicalAttraction = new PhysicalAttraction(10);

        Confidence highConfidence = new Confidence(10);
        Confidence lowConfidence = new Confidence(2);

        PersonalityAttraction highPersonality = new PersonalityAttraction(highConfidence);
        PersonalityAttraction lowPersonality = new PersonalityAttraction(lowConfidence);

        Attraction allHigh = new Attraction(highPhysicalAttraction, highPersonality);
        Attraction medium = new Attraction(highPhysicalAttraction, lowPersonality);

        PotentialMate make_move_mate = new PotentialMate(allHigh);
        PotentialMate no_move_mate = new PotentialMate(medium);

        List<PotentialMate> mateList = new ArrayList<>();
        mateList.add(make_move_mate);
        mateList.add(no_move_mate);

        MateFinder mateFinder = new MateFinder(mateList);




    }
}