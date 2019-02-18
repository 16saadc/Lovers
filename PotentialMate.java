
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

   private Attraction attraction;
   private String name;
   //Comfortable comfortable;

   // just goign to use attraction for now
   public PotentialMate(Attraction attraction, String name) {
         //comfortable = new Comfortable();
         this.attraction = attraction;
         this.name = name;
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

   /**
    * methods:
    * show interest
    * make eye contact
    * be smart
    * be stupid
    * make conversation
    */
}