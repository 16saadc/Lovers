public class Emotion {
	

	private EmotionalState dominantEmotion
	private EmotionalState secondaryEmotion

	public Emotion(EmotionalState dominantEmotion, EmotionalState secondaryEmotion, EmotionalState tertiaryEmotion) {
		this.dominantEmotion = dominantEmotion;
		this.secondaryEmotion = secondaryEmotion;
		this.tertiaryEmotion = tertiaryEmotion;
	}

	public void setEmotion(EmotionalState dominantEmotion, EmotionalState secondaryEmotion, EmotionalState tertiaryEmotion) {
		emotion = dominantEmotion;
		secondEmotion = null;
		thirdEmotion = null;
	}

	public void getDominatEmotion() {
		return dominantEmotion;
	}

	public void secondaryEmotion() {
		return secondaryEmotion;
	}

	public void secondaryEmotion() {
		return tertiaryEmotion;
	}
}