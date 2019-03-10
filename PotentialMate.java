
public class PotentialMate {

    // Current attractiveness to me: {gross, eh, maybe, mhmmm, hot damn, zayummm}
    // Objective attractiveness: {gross, eh, maybe, mhmmm, hot damn, zayummm}
    // Goal with mate: {friends with benefits, hit it and quit it, marriage}
    // Comfort level with mate: {they are awkward, I am comfortable with them, they know my deepest secrets}
    // How familiar are they to me {never seen them, i’ve seen them once or twice, i’ve talked to them a good bit, childhood friend}
    // What do my friends think of him/her {nope, meh, eh, yes}
    // Are they singling me out of a crowd {no, every once in a while, it was like no one else was there}
    // Gender {male, female}
    // Social status {loser, eh, has friends, mr./ms. popular}
    // What race are they {white, african american, asian, latino/a, etc.}
    // How much interest have they shown me in the past {don’t know who I am, they’ve said hi once, they touched my arm during conversation, they told me they love me, they’re just confident in who they are and what they want}
    // How unique are they? {blend in, noticed her in a room, one in a million}
    // How happy are they? {depressed, normal, happy, contagious}
    // Similar interests? {no, yes}
    // Similar background {no, yes}
    // Exiting professional relationship?{Yes coworkers or student/teacher dynamic, none}
    // Experienced sexually? {has kissed, a long term boyfriend, dates around, hooks up with everyone}


   private Attraction attraction;
   private String name;
   private ShownInterest interest;
   private Confidence confidence;
   private SocialStatus socialStatus;
   private Aura aura;
   //Comfortable comfortable;

    /**
    * potential mate we are assessing
    *
    * @param  attraction [level of physical and personality attraction]
    * @param  interest   [how much interest they show in me]
    * @param  name       [their name]
    */
    public PotentialMate(Attraction attraction, ShownInterest interest, Confidence confidence,
        SocialStatus socialStatus, Aura aura, String name) {
         //comfortable = new Comfortable();
         this.attraction = attraction;
         this.name = name;
         this.confidence = confidence;
         this.interest = interest;
         this.socialStatus = socialStatus;
         this.aura = aura;
    }

    public Attraction getAttraction() {
       return attraction;
    }

    public void setAttraction(Attraction attr) {
       attraction = attr;
    }

    public String getName() {
       return name;
    }

    public Confidence getConfidence() {
        return confidence;
    }

    public void setConfidence(Confidence confidence) {
        this.confidence = confidence;
    }

    public ShownInterest getInterest() {
        return interest;
    }

    public void setInterest(ShownInterest interest) {
        this.interest = interest;
    }

    public SocialStatus getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(SocialStatus socialStatus) {
        this.socialStatus = socialStatus;
    }

    public Aura getAura() {
        return aura;
    }

    public void setAura(Aura aura) {
        this.aura = aura;
    }

   /**
    * methods:
    * show interest
    * make eye contact
    * be smart
    * be stupid
    * make conversation
    */





}