
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static int confidenceImportance = 0;
    public static int physicalAttractionImportance = 0;
    public static int socialStatusImportance = 0;
    public static int auraImportance = 0;
    public static int intelligenceImportance = 0;
    public static int personalityImportance = 0;
    public static int relationshipGoalImportance = 0;

    public static Emotion emotion;

    public static void main(String[] args) {

        // START WITH INITIAL NEUTRAL EMOTION
        emotion = new Emotion(EmotionalState.NEUTRAL, EmotionalState.NEUTRAL, EmotionalState.NEUTRAL);



        //
        // create a for loop to go through all potential mates
        // Move move = mate.decide()
        // updateImportance(mate, move)
        //     maybe look at last 3 - 5 mates to update importances?
        //
        //     possibly tie emotion into importance -- if something specific makes you happy, make it more important




        UserState sober_longTerm = new UserState(RelationshipGoal.LONG_TERM, Sobriety.SOBER, MateState.FRIENDS, Environment.INVITING);
        UserState drunk_Hookup = new UserState(RelationshipGoal.ONE_NIGHT, Sobriety.DRUNK, MateState.FAMILIAR, Environment.INVITING);
        UserState drunk_Uninviting = new UserState(RelationshipGoal.ONE_NIGHT, Sobriety.DRUNK, MateState.FAMILIAR, Environment.NO_GO);

        PhysicalAttraction highPhysicalAttraction = new PhysicalAttraction(Level.HIGH);
        PhysicalAttraction lowPhysicalAttraction = new PhysicalAttraction(Level.LOW);




        // Confidence highConfidence = new Confidence(Level.HIGH);
        // Confidence lowConfidence = new Confidence(Level.LOW);

        PersonalityAttraction highPersonality = new PersonalityAttraction(Level.HIGH);
        PersonalityAttraction lowPersonality = new PersonalityAttraction(Level.LOW);

        Attraction goodDate = new Attraction(lowPhysicalAttraction, highPersonality);
        Attraction goodHookup = new Attraction(highPhysicalAttraction, lowPersonality);
        Attraction low = new Attraction(lowPhysicalAttraction, lowPersonality);
        Attraction goodEverything = new Attraction(highPhysicalAttraction, highPersonality);

        RelationshipStatus singleStatus = new RelationshipStatus(Status.SINGLE, Loyalty.NEVER_CHEAT);

        ShownInterest averageShownInterest = new ShownInterest(Level.MEDIUM);
        ShownInterest highShownInterest = new ShownInterest(Level.HIGH);

        Confidence averageConfidence = new Confidence(Level.MEDIUM);
        Confidence highConfidence = new Confidence(Level.HIGH);

        Intelligence mediumIntelligence = new Intelligence(Education.AVERAGE_STUDENT, ConvoTopic.IMPORTANT_ISSUES);

        SocialStatus averageSocialStatus = new SocialStatus(Popularity.AVERAGE, Education.AVERAGE_STUDENT, singleStatus);

        PotentialMate attractiveMate = new PotentialMate(goodDate, averageShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "David");
        PotentialMate semiAttractive = new PotentialMate(goodHookup, averageShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "Katie");
        PotentialMate notAttractive = new PotentialMate(low, averageShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "Paul");

        PotentialMate attractiveConfidentMate = new PotentialMate(goodDate, averageShownInterest, highConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "Will");

        PotentialMate semiAttractiveWithHighInterest = new PotentialMate(goodHookup, highShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "Vishal");
        PotentialMate notAttractiveWithHighInterest = new PotentialMate(low, highShownInterest, averageConfidence, averageSocialStatus, Aura.BOOSTS_MY_EGO, mediumIntelligence, "Vishal");


        MateFinder mateFinder = new MateFinder(attractiveMate, sober_longTerm);
        MateFinder mateFinder2 = new MateFinder(semiAttractive, drunk_Hookup);
        MateFinder mateFinder3 = new MateFinder(notAttractive, drunk_Uninviting);
        MateFinder mateFinder4 = new MateFinder(semiAttractiveWithHighInterest, drunk_Hookup);
        MateFinder mateFinder5 = new MateFinder(notAttractiveWithHighInterest, drunk_Uninviting);
        MateFinder mateFinder6 = new MateFinder(attractiveConfidentMate, sober_longTerm);
        MateFinder mateFinder7 = new MateFinder(notAttractive, drunk_Uninviting);


        //sober, friends with mate, inviting environment, looking for long term, attractive mate:
        System.out.println("Decision" + ": " + mateFinder.decide());

        //drunk, not looking for long term, inviting environment, attractive mate:
        System.out.println("Decision" + ": " + mateFinder2.decide());

        //drunk, not looking for long term, not a good environment, unattractive mate
        System.out.println("Decision" + ": " + mateFinder3.decide());

        System.out.println("Decision" + ": " + mateFinder4.decide());
        System.out.println("Decision" + ": " + mateFinder5.decide());
        System.out.println("Decision" + ": " + mateFinder6.decide());
        System.out.println("Decision" + ": " + mateFinder7.decide());

    }


    public static void adjustImportances(Move move, MateFinder matefinder, UserState userState) {
        // curr personality
        // curr physical
        // curr intelligence
        // curr relationshipGoal
        // curr confidence
        // curr social statu
        // curr aura

        Level currPersonality = matefinder.getPotentialMatePersonality();
        Level currPhysical = matefinder.getPotentialMatePhysicalAttractiveness();
        Level currIntelligence = matefinder.getPotentialMate().getIntelligence().getOverallLevel();
        RelationshipGoal currRelGoal = userState.getRelationshipGoal();
        Level currConfidence = matefinder.getPotentialMate().getConfidence().getLevel();
        SocialStatus currSocialStatus = matefinder.getPotentialMate().getSocialStatus();
        Aura currAura = matefinder.getPotentialMate().getAura();


        if (move == Move.ASK_OUT) {
            if (currPersonality == Level.HIGH) {
                System.out.println("LEARNING: high personality and made move --> increase personality importance");
                personalityImportance += 1;
            } else if (currPersonality == Level.LOW) {
                System.out.println("LEARNING: low personality and made move --> decrease personality importance");
                personalityImportance -= 1;
            }
            if (currPhysical == Level.HIGH) {
                System.out.println("LEARNING: high physical and made move --> increase physical importance");
                physicalAttractionImportance += 1;
            } else if (currPhysical == Level.LOW) {
                System.out.println("LEARNING: low physical and made move --> decrease physical importance");
                physicalAttractionImportance -= 1;
            }
            if (currIntelligence == Level.HIGH) {
                System.out.println("LEARNING: high intelligence and made move --> increase intelligence importance");
                intelligenceImportance += 1;
            } else if (currIntelligence == Level.LOW) {
                System.out.println("LEARNING: low intelligence but made move --> decrease intelligence importance");
                intelligenceImportance -= 1;
            }

            if (currRelGoal == RelationshipGoal.ONE_NIGHT) {
                System.out.println("LEARNING: my relationship goal contradicts my move --> decrease relationship goal importance");
                relationshipGoalImportance -= 1;
            } else if (currRelGoal == RelationshipGoal.LONG_TERM) {
                System.out.println("LEARNING: my relationship goal supports my move --> increase relationship goal importance");
                relationshipGoalImportance += 1;
            }


            if (currConfidence == Level.HIGH) {
                System.out.println("LEARNING: high confidence and made move --> increase confidence importance");
                confidenceImportance += 1;
            } else if (currConfidence == Level.LOW) {
                System.out.println("LEARNING: low confidence and made move --> decrease confidence importance");
                confidenceImportance -= 1;
            }


            //if (currSocialStatus = )
            //


            if (currAura == Aura.TALKS_DOWN || currAura == Aura.AWKWARD || currAura == Aura.IGNORANT) {
                System.out.println("LEARNING: bad aura and made move --> decrease aura importance");
                auraImportance -= 1;
            } else if (currAura == Aura.BOOSTS_MY_EGO) {
                System.out.println("LEARNING: good aura and made move --> increase aura importance");
                auraImportance += 1;
            }
        } else if (move == Move.DO_NOT_APPROACH) {
            if (currPersonality == Level.HIGH) {
                System.out.println("LEARNING: high personality and no move --> decrease personality importance");
                personalityImportance -= 1;
            } else if (currPersonality == Level.LOW) {
                System.out.println("LEARNING: low personality and no move --> increase personality importance");
                personalityImportance += 1;
            }
            if (currPhysical == Level.HIGH) {
                System.out.println("LEARNING: high physical and no move --> decrease physical importance");
                physicalAttractionImportance -= 1;
            } else if (currPhysical == Level.LOW) {
                System.out.println("LEARNING: low physical and no move --> increase physical importance");
                physicalAttractionImportance += 1;
            }
            if (currIntelligence == Level.HIGH) {
                System.out.println("LEARNING: high intelligence and no move --> decrease intelligence importance");
                intelligenceImportance -= 1;
            } else if (currIntelligence == Level.LOW) {
                System.out.println("LEARNING: low intelligence but no move --> increase intelligence importance");
                intelligenceImportance += 1;
            }

            if (currRelGoal == RelationshipGoal.ONE_NIGHT) {
                System.out.println("LEARNING: my relationship goal supports my move --> increase relationship goal importance");
                relationshipGoalImportance += 1;
            } else if (currRelGoal == RelationshipGoal.LONG_TERM || currRelGoal == RelationshipGoal.ONE_NIGHT) {
                System.out.println("LEARNING: my relationship goal contradicts my move --> decrease relationship goal importance");
                relationshipGoalImportance -= 1;
            }


            if (currConfidence == Level.HIGH) {
                System.out.println("LEARNING: high confidence and no move --> decrease confidence importance");
                confidenceImportance -= 1;
            } else if (currConfidence == Level.LOW) {
                System.out.println("LEARNING: low confidence and no move --> increase confidence importance");
                confidenceImportance += 1;
            }


            //if (currSocialStatus = )
            //


            if (currAura == Aura.TALKS_DOWN || currAura == Aura.AWKWARD || currAura == Aura.IGNORANT) {
                System.out.println("LEARNING: bad aura and no move --> increase aura importance");
                auraImportance += 1;
            } else if (currAura == Aura.BOOSTS_MY_EGO) {
                System.out.println("LEARNING: good aura and no move --> decrease aura importance");
                auraImportance -= 1;
            }
        }
    }



    public static void updateEmotion(EmotionalState newEmotion, Level level) {
        EmotionalState currDominant = Main.emotion.getDominantEmotion();
        EmotionalState currSecondary = Main.emotion.getSecondaryEmotion();
        EmotionalState currTertiary = Main.emotion.getTertiaryEmotion();

        if (currDominant == newEmotion) {
                System.out.println("EMOTION: this emotion is already dominant, so nothing changes");
                return;
        }
        if (level == Level.HIGH) {
            if (currSecondary == newEmotion) {
                System.out.println("EMOTION: this emotion is my new dominant emotion. It will switch with my old primary emotion");
                Main.emotion.setSecondaryEmotion(currDominant);
                Main.emotion.setDominantEmotion(newEmotion);
            } else {
                System.out.println("EMOTION: this emotion is my new dominant emotion. My current primary and secondary emotions will get bumped back");

                Main.emotion.setSecondaryEmotion(currDominant);
                Main.emotion.setDominantEmotion(newEmotion);
                Main.emotion.setTertiaryEmotion(currSecondary);
            }
        } else if (level == Level.MEDIUM) {
            // bump to first if its dominant, secondary, or tertiary
            if (currTertiary == newEmotion || currSecondary == newEmotion || currDominant == newEmotion) {
                System.out.println("EMOTION: this emotion is already felt --> set it to dominant");
                Main.emotion.setSecondaryEmotion(currDominant);
                Main.emotion.setDominantEmotion(newEmotion);
                Main.emotion.setTertiaryEmotion(currSecondary);
            } else {
                System.out.println("EMOTION: this emotion is not yet felt --> medium influence so it is now a secondary emotion");
                Main.emotion.setTertiaryEmotion(currSecondary);
                Main.emotion.setSecondaryEmotion(newEmotion);
            }
        } else if (level == Level.LOW) {
            // Move up one place if already felt. If not, make it tertiary
            if (currDominant == newEmotion) {
                System.out.println("EMOTION: this emotion is already dominant --> leave it as is");
                return;
            }
            if (currTertiary == newEmotion) {
                System.out.println("EMOTION: this emotion is already tertiary --> set it to secondary, bump up old secondary emotion to tertiary");
                Main.emotion.setTertiaryEmotion(currSecondary);
                Main.emotion.setSecondaryEmotion(newEmotion);
            } else if (currSecondary == newEmotion) {
                System.out.println("EMOTION: this emotion is already secondary --> set it to dominant, and bump the old dominant and secondary emotions down");
                Main.emotion.setSecondaryEmotion(currDominant);
                Main.emotion.setTertiaryEmotion(currSecondary);
                Main.emotion.setDominantEmotion(newEmotion);
            } else {
                System.out.println("EMOTION: this emotion is not yet felt --> low influence so it is now a tertiary emotion");
                Main.emotion.setTertiaryEmotion(newEmotion);
            }
        }
    }

}
