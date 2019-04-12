public class Emotion {


	private EmotionalState dominantEmotion;
	private EmotionalState secondaryEmotion;
	private EmotionalState tertiaryEmotion;

	public Emotion(EmotionalState dominantEmotion, EmotionalState secondaryEmotion, EmotionalState tertiaryEmotion) {
		this.dominantEmotion = dominantEmotion;
		this.secondaryEmotion = secondaryEmotion;
		this.tertiaryEmotion = tertiaryEmotion;
	}

<<<<<<< HEAD
	public void setAllEmotions(EmotionalState dominantEmotion, EmotionalState secondaryEmotion, EmotionalState tertiaryEmotion) {
		this.dominantEmotion = dominantEmotion;
=======
	public void setEmotion(EmotionalState dominantEmotion, EmotionalState secondaryEmotion, EmotionalState tertiaryEmotion) {
		this.domninantEmotion = dominantEmotion;
>>>>>>> 4b4cd979f71651b69a5750f655630d9969a50b27
		this.secondaryEmotion = secondaryEmotion;
		this.tertiaryEmotion = tertiaryEmotion;
	}

	public EmotionalState getDominantEmotion() {
		return dominantEmotion;
	}

<<<<<<< HEAD
	public EmotionalState getSecondaryEmotion() {
		return secondaryEmotion;
	}

	public EmotionalState getTertiaryEmotion() {
=======
	public void getSecondaryEmotion() {
		return secondaryEmotion;
	}

	public void getTertiaryEmotion() {
>>>>>>> 4b4cd979f71651b69a5750f655630d9969a50b27
		return tertiaryEmotion;
	}

	public void setDominantEmotion(EmotionalState dominantEmotion) {
		this.dominantEmotion = dominantEmotion;
	}

	public void setSecondaryEmotion(EmotionalState secondaryEmotion) {
		this.secondaryEmotion = secondaryEmotion;
	}

	public void setTertiaryEmotion(EmotionalState tertiaryEmotion) {
		this.tertiaryEmotion = tertiaryEmotion;
	}
}

