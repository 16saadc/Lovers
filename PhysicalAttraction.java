

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


	// THIS IS AN ENUM NOW....
	private ClothingStyle clothing_style; // make this an enum?
	private FaceAttraction faceAttraction;
	private BodyAttraction bodyAttraction;
	private Level overallLevel;
	private Level body;
	private Level face;




	// change to use non-numeric variables
	public PhysicalAttraction(Level overallLevel) {
		this.overallLevel = overallLevel;
	}

	public void setLevel(Level level) {
		this.overallLevel = level;
	}

	public Level getLevel() {
		return overallLevel;
	}

	public void calculateFaceAttraction() {
		Level nice_eyes = faceAttraction.getEyes();
		Level nice_smile = faceAttraction.getSmile();
		Level nice_jawline = faceAttraction.getJawline();

		if (nice_eyes == Level.HIGH && nice_smile == Level.HIGH) {
			face = Level.HIGH;
		} else if (nice_eyes == Level.HIGH && nice_jawline == Level.HIGH) {
			face = Level.HIGH;
		} else if (nice_jawline == Level.HIGH && nice_smile == Level.HIGH) {
			face = Level.HIGH;
		} else if (nice_eyes == Level.LOW && nice_smile == Level.LOW) {
			face = Level.LOW;
		} else if (nice_eyes == Level.LOW && nice_jawline == Level.LOW) {
			face = Level.LOW;
		} else if (nice_jawline == Level.LOW && nice_smile == Level.LOW) {
			face = Level.LOW;
		} else {
			face = Level.MEDIUM;
		}
	}

	public void calculateBodyAttraction() {
		Level muscle = bodyAttraction.getMuscle();
		Level hair_attraction = bodyAttraction.getHair();
		Level fat = bodyAttraction.getFat();

		if (muscle == Level.HIGH && fat == Level.LOW) {
			body = Level.HIGH;
		} else if (muscle == Level.HIGH && hair_attraction == Level.HIGH) {
			body = Level.HIGH;
		} else if (hair_attraction == Level.HIGH && fat == Level.LOW) {
			body = Level.HIGH;
		} else if (muscle == Level.LOW && fat == Level.HIGH) {
			body = Level.LOW;
		} else if (muscle == Level.LOW && hair_attraction == Level.LOW) {
			body = Level.LOW;
		} else if (hair_attraction == Level.LOW && fat == Level.HIGH) {
			body = Level.LOW;
		} else {
			body = Level.MEDIUM;
		}
	}

	public void calculatePhysicalAttraction() {
		if (face == Level.HIGH && body == Level.HIGH) {
			overallLevel = Level.HIGH;
		} else if (face == Level.LOW && body == Level.LOW) {
			overallLevel = Level.LOW;
		} else if (face == Level.HIGH && clothing_style == ClothingStyle.CLASSY) {
			overallLevel = Level.HIGH;
		} else if (clothing_style == ClothingStyle.PUNK) {
			overallLevel = Level.LOW;
		} else {
			overallLevel = Level.MEDIUM;
		}
	}

}