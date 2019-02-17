public class Attraction {
    /**
     * physical attraction
     * personality attraction
     * confidence level
     */

    PhysicalAttraction physical_attraction;
    PersonalityAttraction personality_attraction;
    Confidence confidence;

    public Attraction() {
    	physical_attraction = new PhysicalAttraction();
    	personality_attraction = new PersonalityAttraction();
 		confidence = new Confidence();
    }

}