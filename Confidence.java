enum ConfidenceLevel {
    LOW, MEDIUM, HIGH;
}

public class Confidence {
	// Experience with dating {none, little, a lot, too much}
	// Insecurity
	// Cocky {yes (bad), no}
	// Humility {none, kind of, Tim Tebow}

    // will change this to a non-numerical variable
    private ConfidenceLevel level;

    public Confidence(ConfidenceLevel level) {
        this.level = level;
    }

    public ConfidenceLevel getLevel() {
		return level;
	}

    public void setConfidenceLevel(ConfidenceLevel level) {
        this.level = level;
    }

}