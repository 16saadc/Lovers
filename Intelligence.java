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

        if (education == Education.FAILING) {
            overallLevel = Level.LOW;
        } else if (education == Education.A_STUDNET || Education.INTERESTING_WORK) {
            overallLevel = Level.HIGH;
        } else if (education == Education.AVERAGE && convoTopic == ConvoTopic.IMPORTANT_ISSUES) {
            overallLevel = Level.MEDIUM;
        }
    }
}