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

	public EmotionalState getDominatEmotion() {
		return dominantEmotion;
	}

	public EmotionalState getSecondaryEmotion() {
		return secondaryEmotion;
	}

	public EmotionalState getTertiaryEmotion() {
		return tertiaryEmotion;
	}
}