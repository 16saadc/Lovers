public class Intelligence {
    private Level overallLevel;
    private Education education;
    private ConvoTopic convoTopic;


    public Intelligence(Level level) {
        overallLevel = level;
    }

    public Intelligence(Education education, ConvoTopic convoTopic) {
        this.education = education;
        this.convoTopic = convoTopic;

        calculateIntelligence();

    }

    public void calculateIntelligence() {
        if (education == Education.FAILING) {
            overallLevel = Level.LOW;
        } else if (education == Education.A_STUDENT || education == Education.INTERESTING_WORK) {
            overallLevel = Level.HIGH;
        } else if (education == Education.AVERAGE_STUDENT && convoTopic == ConvoTopic.IMPORTANT_ISSUES) {
            overallLevel = Level.MEDIUM;
        } else if (convoTopic == ConvoTopic.NOT_IMPORTANT && (education == Education.FAILING || education == Education.AVERAGE_STUDENT)) {
            overallLevel = Level.LOW;
        }
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Education getEducation() {
        return education;
    }

    public void setConvoTopic(ConvoTopic topic) {
        this.convoTopic = topic;
    }

    public ConvoTopic getConvoTopic() {
        return convoTopic;
    }

    public Level getOverallLevel() {
        return overallLevel;
    }
}