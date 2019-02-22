enum PersonalityLevel {
    LOW, MEDIUM, HIGH;
}

public class PersonalityAttraction {
	// How easy is conversation {forced, it’s alright, we don’t stop talking, not sure}
	// Body language {good signals, standoffish, etc. }
	// Funny {Toby from the office, … , i peed my pants laughing}


    private PersonalityLevel personalityLevel;

    public PersonalityAttraction(PersonalityLevel personalityLevel) {
        this.personalityLevel = personalityLevel;
    }

    public PersonalityLevel getPersonalityLevel() {
        return personalityLevel;
    }

    public void setPersonalityLevel(PersonalityLevel personalityLevel) {
        this.personalityLevel = personalityLevel;
    }

}