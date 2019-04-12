public class Emotion {


	private EmotionalState dominantEmotion;
	private EmotionalState secondaryEmotion;
	private EmotionalState tertiaryEmotion;

	public Emotion(EmotionalState dominantEmotion, EmotionalState secondaryEmotion, EmotionalState tertiaryEmotion) {
		this.dominantEmotion = dominantEmotion;
		this.secondaryEmotion = secondaryEmotion;
		this.tertiaryEmotion = tertiaryEmotion;
	}

	public void setEmotion(EmotionalState dominantEmotion, EmotionalState secondaryEmotion, EmotionalState tertiaryEmotion) {
		this.dominantEmotion = dominantEmotion;
		this.secondaryEmotion = secondaryEmotion;
		this.tertiaryEmotion = tertiaryEmotion;
	}

	public EmotionalState getDominantEmotion() {
		return dominantEmotion;
	}

	public EmotionalState getSecondaryEmotion() {
		return secondaryEmotion;
	}

	public EmotionalState getTertiaryEmotion() {
		return tertiaryEmotion;
	}

	public void setDominantEmotion(EmotionalState emotion) {
		dominantEmotion = emotion;
	}

	public void setSecondaryEmotion(EmotionalState emotion) {
		secondaryEmotion = emotion;
	}

	public void setTertiaryEmotion(EmotionalState emotion) {
		tertiaryEmotion = emotion;
	}
}