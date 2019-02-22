

public class Confidence {
	// Experience with dating {none, little, a lot, too much}
	// Insecurity
	// Cocky {yes (bad), no}
	// Humility {none, kind of, Tim Tebow}

    // will change this to a non-numerical variable
    private Level level;

    public Confidence(Level level) {
        this.level = level;
    }

    public Level getLevel() {
		return level;
	}

    public void setConfidenceLevel(Level level) {
        this.level = level;
    }

}