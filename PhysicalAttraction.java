

// Possible more detailed enum:
//
/*
enum PhysAttractionLevel {
	UGLY, CUTE, BEAUTIFUL, HOT, HANDSOME; // etc...
}
*/

public class PhysicalAttraction {
	// Style of clothing / dress {dressed well, dressed sloppily}
	// Height {short, tall etc.} (important)
	// Hair Type: {curly, straight, long, short, ...}
	// Hair Color: {blonde, brunette, ginger, ..}
	// Eye Color: {brown, blue, green} {pretty, normal}
	// Jawline: {pronounced, normal}
	// Nice smile {no, yes}



	private ClothingStyle clothing_style; // make this an enum?
	private Level level;
	private Level fitness;
	// change to use non-numeric variables
	public PhysicalAttraction(Level level) {
		this.level = level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Level getLevel() {
		return level;
	}

}