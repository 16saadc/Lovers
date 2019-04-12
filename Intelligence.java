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
        this.overallLevel = calculateIntelligence();

    }

    public Level calculateIntelligence() {
        if (education == Education.FAILING) {
            return Level.LOW;
        } else if (education == Education.A_STUDENT || education == Education.INTERESTING_WORK) {
            return Level.HIGH;
        } else if (education == Education.AVERAGE_STUDENT && convoTopic == ConvoTopic.IMPORTANT_ISSUES) {
            return Level.MEDIUM;
        } else if (convoTopic == ConvoTopic.NOT_IMPORTANT && (education == Education.FAILING || education == Education.AVERAGE_STUDENT)) {
            return Level.LOW;
        }
        return Level.MEDIUM;
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

    public void setOverallLevel(Level level) {
        this.overallLevel = level;
    }
}