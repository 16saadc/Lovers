

public class PersonalityAttraction {
	// How easy is conversation {forced, it’s alright, we don’t stop talking, not sure}
	// Body language {good signals, standoffish, etc. }
	// Funny {Toby from the office, … , i peed my pants laughing}


    private Level personalityLevel;
    private Level humor;
    // low = childish/mixed signals, high = too serious and straight forward
    private Level maturity;
    private Level conversation_ease;

    public PersonalityAttraction(Level personalityLevel) {
        this.personalityLevel = personalityLevel;
    }

    public Level getPersonalityLevel() {
        return personalityLevel;
    }

    public void setPersonalityLevel(Level personalityLevel) {
        this.personalityLevel = personalityLevel;
    }

}