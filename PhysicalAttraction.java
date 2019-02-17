public class PhysicalAttraction {
	// Style of clothing / dress {dressed well, dressed sloppily}
	// Height {short, tall etc.} (important)
	// Hair Type: {curly, straight, long, short, ...}
	// Hair Color: {blonde, brunette, ginger, ..}
	// Eye Color: {brown, blue, green} {pretty, normal}
	// Jawline: {pronounced, normal}
	// Nice smile {no, yes}



	ClothingStyle clothing_style;
	List[] height = {"shorter", "same", "taller"};
	List[] hair_color = {"blonde", " brunette", "red", "other"};
	List[] eye_color = {"brown", "blue", "green", "other"}
	boolean nice_eyes;
	boolean nice_smile;
	boolean nice_smell;
	boolean nice_jawline;

	public PhysicalAttraction() {
		clothing_style = new ClothingStyle();
	}

}