public class PersonalityAttraction {
	// How easy is conversation {forced, it’s alright, we don’t stop talking, not sure}
	// Body language {good signals, standoffish, etc. }
	// Funny {Toby from the office, … , i peed my pants laughing}


    private Confidence confidence;

    public PersonalityAttraction(Confidence confidence) {
        this.confidence = confidence;
    }

    public Confidence getConfidence() {
        return confidence;
    }

    public void setConfidence(Confidence confidence) {
        this.confidence = confidence;
    }

}