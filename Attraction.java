enum AttractionLevel {
    LOW, MEDIUM, HIGH;
}

public class Attraction {
    /**
     * physical attraction
     * personality attraction
     * confidence level
     */


    private PhysicalAttraction physical_attraction;
    private PersonalityAttraction personality_attraction;

    // confidence should be an attribute of personality attraction?
    //Confidence confidence;

    public Attraction(PhysicalAttraction physical_attraction, PersonalityAttraction personality_attraction) {
    	this.physical_attraction = physical_attraction;
    	this.personality_attraction = personality_attraction;
    }

    public PhysicalAttraction getPhysicalAttraction() {
        return physical_attraction;
    }

    public PersonalityAttraction getPersonalityAttraction() {
        return personality_attraction;
    }

    public void setPhyiscalAttraction(PhysicalAttraction p) {
        physical_attraction = p;
    }

    public void setPersonalityAttraction(PersonalityAttraction p) {
        personality_attraction = p;
    }

}