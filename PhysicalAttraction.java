enum PhysicalAttractionLevel {
	LOW, MEDIUM, HIGH;
}

// Possible more detailed enum:
//

enum PhysAttractionLevel {
	UGLY, CUTE, BEAUTIFUL, HOT, HANDSOME; // etc...
}

public class PhysicalAttraction {
	// Style of clothing / dress {dressed well, dressed sloppily}
	// Height {short, tall etc.} (important)
	// Hair Type: {curly, straight, long, short, ...}
	// Hair Color: {blonde, brunette, ginger, ..}
	// Eye Color: {brown, blue, green} {pretty, normal}
	// Jawline: {pronounced, normal}
	// Nice smile {no, yes}



	private ClothingStyle clothing_style; // make this an enum?
	private PhysicalAttractionLevel level;
	// change to use non-numeric variables
	public PhysicalAttraction(PhysAttractionLevel level) {
		this.level = level;
	}

	public void setLevel(PhysicalAttractionLevel level) {
		this.level = level;
	}

	public PhysicalAttractionLevel getLevel() {
		return level;
	}

}