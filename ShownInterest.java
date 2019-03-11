public class ShownInterest {
    // the level of interest the potential mate is showing you

    private Level shownInterest;
    private Level physicalTouch;
    private Level eyeContact;
    private ConvoLevel conversation;

    // constructor to calculate total level of interest based on interactions
    public ShownInterest(Level physicalTouch, Level eyeContact, ConvoLevel conversation) {
        this.physicalTouch = physicalTouch;
        this.eyeContact = eyeContact;
        this.conversation = conversation;

        this.shownInterest = calculateShownInterest();
        // calculate level
    }

    public ShownInterest(Level shownInterest) {
        this.shownInterest = shownInterest;
    }




    // method to calculate level of interest based on interactions
    private Level calculateShownInterest() {
        if (physicalTouch == Level.HIGH) {
            return Level.HIGH;
        } else if ((eyeContact == Level.HIGH || eyeContact == Level.MEDIUM)
            && (conversation == ConvoLevel.ASKING_LOTS_OF_QUESTIONS || conversation == ConvoLevel.PERSONAL)) {
            return Level.HIGH;
        } else if (conversation == ConvoLevel.SELF_TALK && eyeContact == Level.HIGH) {
            return Level.HIGH;
        } else if (eyeContact == Level.LOW || physicalTouch == Level.LOW) {
            return Level.LOW;
        } else {
            return Level.MEDIUM;
        }
    }


    public void setPhysicalTouch(Level touch) {
        physicalTouch = touch;
    }

    public Level getPhysicalTouch() {
        return physicalTouch;
    }

    public void setConvoTopic(ConvoLevel convoLevel) {
        this.conversation = convoLevel;
    }


}