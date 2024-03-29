import java.util.List;

// possible more detailed move results


public class MateFinder {

    /**
     * variables:
     *
     * Current goal: {hookup, emotional connection, long-term, o real intention (just accepting what happens)}
     * Drunkenness: {sober, buzzed, drunk, lit, tanked, sloshed, housed}
     * What race(s) am I generally attracted to {White, African American, Asian, latino/a, etc.}
     * Are we in an environment where making a move is encouraged? {don’t even think about it, be cautious, yeah sure, it would be weird if you didn’t}
     * Potential Mates: [mate A, mate B, …]
     */
    private PotentialMate potentialMate;
    private UserState currentState;
    private Gender gender;

    public MateFinder(PotentialMate mate, UserState currentState, Gender gender) {
        potentialMate = mate;
        this.currentState = currentState;
        this.gender = gender;
    }

    public void setPotentialMate(PotentialMate mate) {
        potentialMate = mate;
    }

    public PotentialMate getPotentialMate() {
        return potentialMate;
    }

    public void setCurrentState(UserState state) {
        currentState = state;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public UserState getCurrentState() {
        return currentState;
    }

    public Level getPotentialMatePersonality() {
        return potentialMate.getAttraction().getPersonalityAttraction().getPersonalityLevel();
    }

    public Level getPotentialMatePhysicalAttractiveness() {
        return potentialMate.getAttraction().getPhysicalAttraction().getLevel();
    }

    public Level getPotentialMateInterest() {
        return potentialMate.getInterest().getInterestLevel();
    }

    public void drink(int drinks) {
        if (drinks >= 4) {
            currentState.setSobriety(Sobriety.DRUNK);
        } else if (drinks >= 8) {
            currentState.setSobriety(Sobriety.BLACKOUT);
        } else if (drinks >= 2) {
            currentState.setSobriety(Sobriety.TIPSY);
        } else {
            currentState.setSobriety(Sobriety.SOBER);
        }
    }


    public void adjustEmotionsBasedOnLearning() {
        Level potentialMatePersonality = getPotentialMatePersonality();
        Level potentialMatePhysical = getPotentialMatePhysicalAttractiveness();

        Level shownInterestLevel = getPotentialMateInterest();
        Level mateSocialStatus = getPotentialMate().getSocialStatus().calculateLevel();
        Status relationshipStatus = getPotentialMate().getSocialStatus().getRelationshipStatus().getStatus();
        Environment currEnvironment = this.currentState.getEnvironment();
        Level mateConfidence = potentialMate.getConfidence().getLevel();
        Level mateIntelligence = potentialMate.getIntelligence().getOverallLevel();



        /*

        HAPPY, SAD, ANGRY, AROUSED, BORED, CONFUSED, IMPRESSED, EXCITED, NEUTRAL;

        positive
            intelligence --> impressed
            shown interest --> aroused / happy
            aura --> excited
            personality --> happy / excited
            physical --> excited / aroused
            social status --> impressed
            rel goal --> happy
            environment --> excited
            confidence --> aroused

        negative
            intelligence --> bored
            shown interest --> sad
            aura --> angry
            personality --> bored
            physical --> bored
            social status --> not impressed
            rel goal --> sad
            environment --> bored / angry
            confidence --> confused


         */

        //CONFIDENCE
        System.out.println("=== CALCULATING AGENT EMOTION BASED ON CURRENT SITUATION ===");
        if (Main.confidenceImportance >= 4) {
            System.out.println("Agent finds confidence VERY important --> ");
            if (mateConfidence == Level.HIGH) {
                System.out.println("high confidence in mate --> agent is much more aroused");
                Main.updateEmotion(EmotionalState.AROUSED, Level.HIGH);
            } else if (mateConfidence == Level.LOW) {
                System.out.println("low confidence in mate --> agent is very confused");
                Main.updateEmotion(EmotionalState.CONFUSED, Level.HIGH);
            }
        } else if (Main.confidenceImportance >= 2) {
            System.out.println("Agent finds confidence pretty important --> ");
            if (mateConfidence == Level.HIGH) {
                System.out.println("high confidence in mate--> agent is slightly more aroused");
                Main.updateEmotion(EmotionalState.AROUSED, Level.MEDIUM);
            } else if (mateConfidence == Level.LOW) {
                System.out.println("low confidence in mate --> agent is pretty confused");
                Main.updateEmotion(EmotionalState.CONFUSED, Level.MEDIUM);
            }
        } else if (Main.confidenceImportance == 1) {
            System.out.println("Agent finds confidence somewhat important --> ");
            if (mateConfidence == Level.HIGH) {
                System.out.println("high confidence in mate --> agent is a little more aroused");
                Main.updateEmotion(EmotionalState.AROUSED, Level.LOW);
            } else if (mateConfidence == Level.LOW) {
                System.out.println("low confidence in mate --> agent is a little confused");
                Main.updateEmotion(EmotionalState.CONFUSED, Level.LOW);
            }
        }


        //ENVIRONMENT
        if (Main.environmentImportance >= 4) {
            System.out.println("Agent finds environment VERY important --> ");
            if (currEnvironment == Environment.DEFINITELY || currEnvironment == Environment.BE_CONFIDENT || currEnvironment == Environment.INVITING) {
                Main.updateEmotion(EmotionalState.EXCITED, Level.HIGH);
                System.out.println("good environment --> agent is a lot more excited");
            } else if (currEnvironment == Environment.NO_GO) {
                System.out.println("bad environment --> agent is a lot more bored and angry");
                Main.updateEmotion(EmotionalState.BORED, Level.HIGH);
                Main.updateEmotion(EmotionalState.ANGRY, Level.MEDIUM);
            }
        } else if (Main.environmentImportance >= 2) {
            System.out.println("Agent finds environment pretty important --> ");
            if (currEnvironment == Environment.DEFINITELY || currEnvironment == Environment.BE_CONFIDENT || currEnvironment == Environment.INVITING) {
                Main.updateEmotion(EmotionalState.EXCITED, Level.MEDIUM);
                System.out.println("good environment --> agent is slightly more excited");
            } else if (currEnvironment == Environment.NO_GO) {
                System.out.println("bad environment --> agent is slightly more bored and angry");
                Main.updateEmotion(EmotionalState.BORED, Level.MEDIUM);
                Main.updateEmotion(EmotionalState.ANGRY, Level.LOW);
            }
        } else if (Main.environmentImportance == 1) {
            System.out.println("Agent finds environment somewhat important --> ");
            if (currEnvironment == Environment.DEFINITELY || currEnvironment == Environment.BE_CONFIDENT || currEnvironment == Environment.INVITING) {
                Main.updateEmotion(EmotionalState.EXCITED, Level.LOW);
                System.out.println("good environment --> agent is a little more excited");
            } else if (currEnvironment == Environment.NO_GO) {
                System.out.println("bad environment --> agent is a little more bored and angry");
                Main.updateEmotion(EmotionalState.BORED, Level.LOW);
            }
        }



        //RELATIONSHIP GOAL
        if (Main.relationshipGoalImportance >= 4) {
            System.out.println("Agent finds relationship goal VERY important --> ");
            if (relationshipStatus == Status.SINGLE) {
                System.out.println("fitting relationship status / goal --> agent is very happy");
                Main.updateEmotion(EmotionalState.HAPPY, Level.HIGH);
            } else if (relationshipStatus != Status.SINGLE) {
                System.out.println("bad relationship status / goal --> agent is very sad");
                Main.updateEmotion(EmotionalState.SAD, Level.HIGH);
            }
        } else if (Main.relationshipGoalImportance >= 2) {
            System.out.println("Agent finds relationship goal pretty important --> ");
            if (relationshipStatus == Status.SINGLE) {
                System.out.println("fitting relationship status / goal --> agent is pretty happy");
                Main.updateEmotion(EmotionalState.HAPPY, Level.MEDIUM);
            } else if (relationshipStatus == Status.SINGLE) {
                System.out.println("bad relationship status / goal --> agent pretty sadder");
                Main.updateEmotion(EmotionalState.SAD, Level.MEDIUM);
            }
        } else if (Main.relationshipGoalImportance == 1) {
            System.out.println("Agent finds relationship goal somewhat important --> ");
            if (relationshipStatus == Status.SINGLE) {
                Main.updateEmotion(EmotionalState.HAPPY, Level.LOW);
                System.out.println("fitting relationship status / goal --> agent is a little happy");
            } else if (relationshipStatus == Status.SINGLE) {
                System.out.println("bad relationship status / goal --> agent is a little sad");
                Main.updateEmotion(EmotionalState.SAD, Level.LOW);
            }
        }

        //SOCIAL STATUS
        if (Main.socialStatusImportance >= 4) {
            System.out.println("Agent finds social status VERY important --> ");
            if (mateSocialStatus == Level.HIGH) {
                System.out.println("high social status in mate--> agent is very impressed");
                Main.updateEmotion(EmotionalState.IMPRESSED, Level.HIGH);
            } else if (mateSocialStatus == Level.LOW) {
                System.out.println("low social status in mate--> agent is very confused");
                Main.updateEmotion(EmotionalState.CONFUSED, Level.HIGH);
            }
        } else if (Main.socialStatusImportance >= 2) {
            System.out.println("Agent finds social status pretty important --> ");
            if (mateSocialStatus == Level.HIGH) {
                System.out.println("high social status in mate--> agent is slightly more impressed");
                Main.updateEmotion(EmotionalState.IMPRESSED, Level.MEDIUM);
            } else if (mateSocialStatus == Level.LOW) {
                System.out.println("low social status in mate--> agent is a pretty confused");
                Main.updateEmotion(EmotionalState.CONFUSED, Level.MEDIUM);
            }
        } else if (Main.socialStatusImportance == 1) {
            System.out.println("Agent finds social status somewhat important --> ");
            if (mateSocialStatus == Level.HIGH) {
                System.out.println("high social status in mate--> agent is a little impressed");
                Main.updateEmotion(EmotionalState.IMPRESSED, Level.LOW);
            } else if (mateSocialStatus == Level.LOW) {
                System.out.println("low social status in mate--> agent is a little confused");
                Main.updateEmotion(EmotionalState.CONFUSED, Level.LOW);
            }
        }


        //PHYSICAL
        if (Main.physicalAttractionImportance >= 4) {
            System.out.println("Agent finds physical attraction VERY important --> ");
            if (potentialMatePhysical == Level.HIGH) {
                System.out.println("high physical attraction in mate--> agent is very aroused and excited");
                Main.updateEmotion(EmotionalState.AROUSED, Level.HIGH);
                Main.updateEmotion(EmotionalState.EXCITED, Level.MEDIUM);
            } else if (potentialMatePhysical == Level.LOW) {
                System.out.println("low physical attraction in mate--> agent is very bored");
                Main.updateEmotion(EmotionalState.BORED, Level.HIGH);
            }
        } else if (Main.physicalAttractionImportance >= 2) {
            System.out.println("Agent finds physical attraction slightly important --> ");
            if (potentialMatePhysical == Level.HIGH) {
                System.out.println("high physical attraction in mate--> agent is slightly aroused and excited");
                Main.updateEmotion(EmotionalState.AROUSED, Level.MEDIUM);
                Main.updateEmotion(EmotionalState.EXCITED, Level.LOW);
            } else if (potentialMatePhysical == Level.LOW) {
                System.out.println("low physical attraction in mate--> agent is pretty bored");
                Main.updateEmotion(EmotionalState.BORED, Level.MEDIUM);
            }
        } else if (Main.physicalAttractionImportance == 1) {
            System.out.println("Agent finds physical attraction somewhat important --> ");
            if (potentialMatePhysical == Level.HIGH) {
                System.out.println("high physical attraction in mate--> agent is a little aroused");
                Main.updateEmotion(EmotionalState.AROUSED, Level.LOW);
            } else if (potentialMatePhysical == Level.LOW) {
                System.out.println("low physical attraction in mate--> agent is a little bored");
                Main.updateEmotion(EmotionalState.BORED, Level.LOW);
            }
        }

        //PERSONALITY
        if (Main.personalityImportance >= 4) {
            System.out.println("Agent finds personality VERY important --> ");
            if (potentialMatePersonality == Level.HIGH) {
                Main.updateEmotion(EmotionalState.HAPPY, Level.HIGH);
                System.out.println("high personality in mate--> agent is a very happy and excited");
                Main.updateEmotion(EmotionalState.EXCITED, Level.MEDIUM);
            } else if (potentialMatePersonality == Level.LOW) {
                System.out.println("low personality in mate--> agent is very bored");
                Main.updateEmotion(EmotionalState.BORED, Level.HIGH);
            }
        } else if (Main.personalityImportance >= 2) {
            System.out.println("Agent finds personality pretty important --> ");
            if (potentialMatePersonality == Level.HIGH) {
                System.out.println("high personality in mate--> agent is pretty happy and excited");
                Main.updateEmotion(EmotionalState.HAPPY, Level.MEDIUM);
                Main.updateEmotion(EmotionalState.EXCITED, Level.LOW);
            } else if (potentialMatePersonality == Level.LOW) {
                System.out.println("low personality in mate--> agent is pretty bored");
                Main.updateEmotion(EmotionalState.BORED, Level.MEDIUM);
            }
        } else if (Main.personalityImportance == 1) {
            System.out.println("Agent finds personality somewhat important --> ");
            if (potentialMatePersonality == Level.HIGH) {
                System.out.println("high personality in mate--> agent is a little happy");
                Main.updateEmotion(EmotionalState.HAPPY, Level.LOW);
            } else if (potentialMatePersonality == Level.LOW) {
                System.out.println("low personality in mate--> agent is a little bored");
                Main.updateEmotion(EmotionalState.BORED, Level.LOW);
            }
        }


        //INTELLIGENCE
        if (Main.intelligenceImportance >= 4) {
            System.out.println("Agent finds intelligence VERY important --> ");
            if (mateIntelligence == Level.HIGH) {
                System.out.println("high intelligence in mate --> agent is very impressed");
                Main.updateEmotion(EmotionalState.IMPRESSED, Level.HIGH);
            } else if (mateIntelligence == Level.LOW) {
                System.out.println("low intelligence in mate --> agent is very bored");
                Main.updateEmotion(EmotionalState.BORED, Level.HIGH);
            }
        } else if (Main.intelligenceImportance >= 2) {
            System.out.println("Agent finds intelligence pretty important --> ");
            if (mateIntelligence == Level.HIGH) {
                System.out.println("high intelligence in mate --> agent is pretty impressed");
                Main.updateEmotion(EmotionalState.IMPRESSED, Level.MEDIUM);
            } else if (mateIntelligence == Level.LOW) {
                System.out.println("low intelligence in mate --> agent is pretty bored");
                Main.updateEmotion(EmotionalState.BORED, Level.MEDIUM);
            }
        } else if (Main.intelligenceImportance == 1) {
            System.out.println("Agent finds intelligence somewhat important --> ");
            if (mateIntelligence == Level.HIGH) {
                System.out.println("high intelligence in mate --> agent is a little impressed");
                Main.updateEmotion(EmotionalState.IMPRESSED, Level.LOW);
            } else if (mateIntelligence == Level.LOW) {
                System.out.println("low intelligence in mate --> agent is a little bored");
                Main.updateEmotion(EmotionalState.BORED, Level.LOW);
            }
        }

        // SHOWN INTEREST
        if (Main.shownInterestImportance >= 4) {
            System.out.println("Agent finds mate's shown interest VERY important --> ");
            if (potentialMate.getInterest().getInterestLevel() == Level.HIGH) {
                System.out.println("high interest from mate --> agent is very aroused and happy");
                Main.updateEmotion(EmotionalState.AROUSED, Level.HIGH);
                Main.updateEmotion(EmotionalState.HAPPY, Level.MEDIUM);
            } else if (potentialMate.getInterest().getInterestLevel() == Level.LOW) {
                System.out.println("low interest from mate --> agent is very sad");
                Main.updateEmotion(EmotionalState.SAD, Level.HIGH);
            }
        } else if (Main.shownInterestImportance >= 2) {
            System.out.println("Agent finds mate's shown interest pretty important --> ");
            if (potentialMate.getInterest().getInterestLevel() == Level.HIGH) {
                System.out.println("high interest from mate --> agent is pretty aroused and happy");
                Main.updateEmotion(EmotionalState.AROUSED, Level.MEDIUM);
                Main.updateEmotion(EmotionalState.HAPPY, Level.LOW);
            } else if (potentialMate.getInterest().getInterestLevel() == Level.LOW) {
                System.out.println("low interest from mate --> agent is pretty sad");
                Main.updateEmotion(EmotionalState.SAD, Level.MEDIUM);
            }
        } else if (Main.shownInterestImportance == 1) {
            System.out.println("Agent finds mate's shown interest somewhat important --> ");
            if (potentialMate.getInterest().getInterestLevel() == Level.HIGH) {
                System.out.println("high interest from mate --> agent is a little aroused and happy");
                Main.updateEmotion(EmotionalState.AROUSED, Level.LOW);
            } else if (potentialMate.getInterest().getInterestLevel() == Level.LOW) {
                System.out.println("low interest from mate --> agent is a little sad");
                Main.updateEmotion(EmotionalState.SAD, Level.LOW);
            }
        }

        // AURA
        if (Main.auraImportance >= 4) {
            System.out.println("Agent finds mate's aura VERY important --> ");
            if (potentialMate.getAura() == Aura.BOOSTS_MY_EGO) {
                System.out.println("good aura from mate --> agent is very excited and aroused");
                Main.updateEmotion(EmotionalState.AROUSED, Level.LOW);
                Main.updateEmotion(EmotionalState.EXCITED, Level.HIGH);
            } else if (potentialMate.getAura() == Aura.TALKS_DOWN || potentialMate.getAura() == Aura.IGNORANT || potentialMate.getAura() == Aura.AWKWARD) {
                System.out.println("bad aura from mate --> agent is very angry");
                Main.updateEmotion(EmotionalState.ANGRY, Level.HIGH);
            }
        } else if (Main.auraImportance >= 2) {
            System.out.println("Agent finds mate's aura pretty important --> ");
            if (potentialMate.getAura() == Aura.BOOSTS_MY_EGO) {
                System.out.println("good aura from mate --> agent is pretty excited");
                Main.updateEmotion(EmotionalState.EXCITED, Level.MEDIUM);
            } else if (potentialMate.getAura() == Aura.TALKS_DOWN || potentialMate.getAura() == Aura.IGNORANT || potentialMate.getAura() == Aura.AWKWARD) {
                System.out.println("bad aura from mate --> agent is pretty angry");
                Main.updateEmotion(EmotionalState.ANGRY, Level.MEDIUM);
            }
        } else if (Main.auraImportance == 1) {
            System.out.println("Agent finds mate's aura somewhat important --> ");
            if (potentialMate.getAura() == Aura.BOOSTS_MY_EGO) {
                System.out.println("good aura from mate --> agent is a little excited");
                Main.updateEmotion(EmotionalState.EXCITED, Level.LOW);
            } else if (potentialMate.getAura() == Aura.TALKS_DOWN || potentialMate.getAura() == Aura.IGNORANT || potentialMate.getAura() == Aura.AWKWARD) {
                System.out.println("bad aura from mate --> agent is a little angry");
                Main.updateEmotion(EmotionalState.ANGRY, Level.LOW);
            }
        }

        /*

        //adjust emotion based on important traits
        int highTraitHighImportanceCount = 0;
        int highTraitMediumImportanceCount = 0;
        int lowTrait_highImportanceCount = 0;

        //count high important traits
        if (Main.confidenceImportance > 3 && potentialMate.getConfidence().getLevel() == Level.HIGH) highTraitHighImportanceCount++;
        if (Main.intelligenceImportance > 3 && potentialMate.getIntelligence().getOverallLevel() == Level.HIGH) highTraitHighImportanceCount++;
        if (Main.auraImportance > 3 && potentialMate.getAura() == Aura.BOOSTS_MY_EGO) highTraitHighImportanceCount++;
        if (Main.physicalAttractionImportance > 3 && potentialMatePhysical == Level.HIGH) highTraitHighImportanceCount++;
        if (Main.personalityImportance > 3 && potentialMatePersonality == Level.HIGH) highTraitHighImportanceCount++;
        if (Main.socialStatusImportance > 3 && socialStatus == Level.HIGH) highTraitHighImportanceCount++;
        if (Main.relationshipGoalImportance > 3 && relationshipStatus == Status.SINGLE) highTraitHighImportanceCount++;
        if (Main.environmentImportance > 3 && (currEnvironment == Environment.DEFINITELY || currEnvironment == Environment.BE_CONFIDENT || currEnvironment == Environment.INVITING)) highTraitHighImportanceCount++;
        if (Main.shownInterestImportance > 3 && potentialMate.getInterest().getInterestLevel() == Level.HIGH) highTraitHighImportanceCount++;



        if (Main.confidenceImportance > 1 && potentialMate.getConfidence().getLevel() == Level.HIGH) highTraitMediumImportanceCount++;
        if (Main.intelligenceImportance > 1 && potentialMate.getIntelligence().getOverallLevel() == Level.HIGH) highTraitMediumImportanceCount++;
        if (Main.auraImportance > 1 && potentialMate.getAura() == Aura.BOOSTS_MY_EGO) highTraitMediumImportanceCount++;
        if (Main.physicalAttractionImportance > 1 && potentialMatePhysical == Level.HIGH) highTraitMediumImportanceCount++;
        if (Main.personalityImportance > 1 && potentialMatePersonality == Level.HIGH) highTraitMediumImportanceCount++;
        if (Main.socialStatusImportance > 1 && socialStatus == Level.HIGH) highTraitMediumImportanceCount++;
        if (Main.relationshipGoalImportance > 1 && relationshipStatus == Status.SINGLE) highTraitMediumImportanceCount++;
        if (Main.environmentImportance > 1 && (currEnvironment == Environment.DEFINITELY || currEnvironment == Environment.BE_CONFIDENT || currEnvironment == Environment.INVITING)) highTraitMediumImportanceCount++;
        if (Main.shownInterestImportance > 1 && potentialMate.getInterest().getInterestLevel() == Level.HIGH) highTraitMediumImportanceCount++;


        if (Main.confidenceImportance > 1 && potentialMate.getConfidence().getLevel() == Level.LOW) lowTrait_highImportanceCount++;
        if (Main.intelligenceImportance > 1 && potentialMate.getIntelligence().getOverallLevel() == Level.LOW) lowTrait_highImportanceCount++;
        if (Main.auraImportance > 1 && (potentialMate.getAura() == Aura.BOOSTS_MY_EGO
            || potentialMate.getAura() == Aura.TALKS_DOWN
            || potentialMate.getAura() == Aura.IGNORANT)) lowTrait_highImportanceCount++;
        if (Main.physicalAttractionImportance > 1 && potentialMatePhysical == Level.LOW) lowTrait_highImportanceCount++;
        if (Main.personalityImportance > 1 && potentialMatePersonality == Level.LOW) lowTrait_highImportanceCount++;
        if (Main.socialStatusImportance > 1 && socialStatus == Level.LOW) lowTrait_highImportanceCount++;
        if (Main.relationshipGoalImportance > 1 && relationshipStatus != Status.SINGLE) lowTrait_highImportanceCount++;
        if (Main.environmentImportance > 1 && currEnvironment == Environment.NO_GO) lowTrait_highImportanceCount++;
        if (Main.shownInterestImportance > 1 && potentialMate.getInterest().getInterestLevel() == Level.LOW) lowTrait_highImportanceCount++;


        if (highTraitHighImportanceCount >= 3) {
            System.out.println("EMOTION: This mate has 4 or more traits that I REALLY like, so I am more impressed and happier");
            Main.updateEmotion(EmotionalState.IMPRESSED, Level.HIGH);
            Main.updateEmotion(EmotionalState.HAPPY, Level.MEDIUM);
            Main.updateEmotion(EmotionalState.EXCITED, Level.LOW);
            return;
        } else if (highTraitHighImportanceCount >= 2) {
            System.out.println("EMOTION: This mate has 2-3 traits that I REALLY like, so I am more impressed and happier");
            Main.updateEmotion(EmotionalState.IMPRESSED, Level.HIGH);
            Main.updateEmotion(EmotionalState.HAPPY, Level.MEDIUM);
            return;
        } else if (highTraitHighImportanceCount > 0) {
            System.out.println("EMOTION: This mate has some traits that I REALLY like, so I am more impressed");
            Main.updateEmotion(EmotionalState.IMPRESSED, Level.MEDIUM);
            return;
        } else if (highTraitMediumImportanceCount >= 3) {
            System.out.println("EMOTION: This mate has 4 or more traits that I SORT OF like, so I am more impressed and happier");
            Main.updateEmotion(EmotionalState.IMPRESSED, Level.MEDIUM);
            Main.updateEmotion(EmotionalState.HAPPY, Level.MEDIUM);
            return;
        } else if (highTraitMediumImportanceCount >= 2) {
            System.out.println("EMOTION: This mate has 2-3 traits that I SORT OF like, so I am more impressed");
            Main.updateEmotion(EmotionalState.IMPRESSED, Level.MEDIUM);
            Main.updateEmotion(EmotionalState.HAPPY, Level.LOW);
            return;
        } else if (highTraitMediumImportanceCount > 0) {
            System.out.println("EMOTION: This mate has some traits that I SORT OF like, so I am slightly more impressed");
            Main.updateEmotion(EmotionalState.IMPRESSED, Level.LOW);
            return;
        } else if (lowTrait_highImportanceCount >= 3) {
            System.out.println("EMOTION: This mate has 4 or more traits that I DO NOT like, so I am a lot more sad and bored");
            Main.updateEmotion(EmotionalState.SAD, Level.HIGH);
            Main.updateEmotion(EmotionalState.BORED, Level.MEDIUM);
            return;
        } else if (lowTrait_highImportanceCount >= 2) {
            System.out.println("EMOTION: This mate has 2-3 traits that I DO NOT like, so I am slightly more sad and bored");
            Main.updateEmotion(EmotionalState.SAD, Level.MEDIUM);
            Main.updateEmotion(EmotionalState.BORED, Level.LOW);
            return;
        } else if (lowTrait_highImportanceCount > 0) {
            System.out.println("EMOTION: This mate has some traits that I DO NOT like, so I am slightly more sad");
            Main.updateEmotion(EmotionalState.SAD, Level.LOW);
            return;
        }
        */
    }



    /**
     * methods:
     * assess the candidate's state
     * Evaluate most important traits based on state
     *
     * returns the move to make
     */
    public Move decide() {

        Level potentialMatePersonality = getPotentialMatePersonality();
        Level potentialMatePhysical = getPotentialMatePhysicalAttractiveness();

        Level shownInterestLevel = getPotentialMateInterest();

        Level mateSocialStatus = getPotentialMate().getSocialStatus().calculateLevel();
        Status relationshipStatus = getPotentialMate().getSocialStatus().getRelationshipStatus().getStatus();
        Environment currEnvironment = this.currentState.getEnvironment();
        Level mateConfidence = potentialMate.getConfidence().getLevel();
        Level mateIntelligence = potentialMate.getIntelligence().getOverallLevel();


        adjustEmotionsBasedOnLearning();
        Emotion emotionalState = Main.emotion;


        EmotionalState dominantEmotion = emotionalState.getDominantEmotion();
        EmotionalState secondaryEmotion = emotionalState.getSecondaryEmotion();
        EmotionalState tertiaryEmotion = emotionalState.getTertiaryEmotion();

        System.out.println("CURRENT EMOTIONS: \n    primary:" + dominantEmotion + ", secondary:" + secondaryEmotion + ", tertiary:" + tertiaryEmotion);
        System.out.println("=========== MATE INFO ==========");
        System.out.println("Potential mate: " + potentialMate.getName());
        System.out.println("Mate attractiveness level: " + potentialMatePhysical);
        System.out.println("Mate personality level: " + potentialMatePersonality);
        System.out.println("Environment: " + currEnvironment);
        System.out.println("Mate interest level: " + shownInterestLevel);
        System.out.println("Mate intelligence level: " + mateIntelligence);
        System.out.println("Mate social status level: " + mateSocialStatus);
        System.out.println("Mate relationship status: " + relationshipStatus);
        System.out.println("Mate confidence: " + mateConfidence);
        System.out.println("================================");



        System.out.println("============= DECISION PROCESS ============");


        int positive = 0;
        if (dominantEmotion == EmotionalState.IMPRESSED || dominantEmotion == EmotionalState.HAPPY
            || dominantEmotion == EmotionalState.AROUSED || dominantEmotion == EmotionalState.EXCITED) positive += 3;
        if (secondaryEmotion == EmotionalState.IMPRESSED || secondaryEmotion == EmotionalState.HAPPY
            || secondaryEmotion == EmotionalState.AROUSED || secondaryEmotion == EmotionalState.EXCITED) positive += 2;
        if (tertiaryEmotion == EmotionalState.IMPRESSED || tertiaryEmotion == EmotionalState.HAPPY
            || tertiaryEmotion == EmotionalState.AROUSED || tertiaryEmotion == EmotionalState.EXCITED) positive++;

        int negative = 0;
        if (dominantEmotion == EmotionalState.SAD || dominantEmotion == EmotionalState.ANGRY) negative += 3;
        if (secondaryEmotion == EmotionalState.SAD || secondaryEmotion == EmotionalState.ANGRY) negative += 2;
        if (tertiaryEmotion == EmotionalState.SAD || tertiaryEmotion == EmotionalState.ANGRY) negative++;
        //System.out.println(negative);

        int isArousedLevel = 0;

        if(dominantEmotion == EmotionalState.AROUSED) isArousedLevel = 3;
        if (secondaryEmotion == EmotionalState.AROUSED) isArousedLevel = 2;
        if (tertiaryEmotion == EmotionalState.AROUSED) isArousedLevel = 1;

        //System.out.println(isArousedLevel);
        int isConfusedLevel = 0;

        if(dominantEmotion == EmotionalState.CONFUSED) isConfusedLevel = 3;
        if (secondaryEmotion == EmotionalState.CONFUSED) isConfusedLevel = 2;
        if (tertiaryEmotion == EmotionalState.CONFUSED) isConfusedLevel = 1;

        int isBoredLevel = 0;

        if(dominantEmotion == EmotionalState.BORED) isBoredLevel = 3;
        if (secondaryEmotion == EmotionalState.BORED) isBoredLevel = 2;
        if (tertiaryEmotion == EmotionalState.BORED) isBoredLevel = 1;

        if(negative >= 5) {
            System.out.println("If emotional state is extremely negative, agent is not in state to make a move or pursue, do not approach");
            return Move.DO_NOT_APPROACH;
        }

        if (positive >= 5) {
            System.out.println("EMOTION: Feeling extremely positive --> ");
            if (isArousedLevel == 1) {
                System.out.println("Slightly aroused as well --> Make a move");
                return Move.ASK_OUT;
            } else if (isBoredLevel == 1) {
                System.out.println("Slightly bored --> check environment");
                if (currEnvironment == Environment.DEFINITELY || currEnvironment == Environment.INVITING) {
                    System.out.println("Environment is very suitable --> make a move");
                    return Move.ASK_OUT;
                } else if (currEnvironment == Environment.NO_GO) {
                    System.out.println("Environment is bad despite positive emotions --> don't approach");
                    return Move.DO_NOT_APPROACH;
                }
            }
            System.out.println("All positives --> Make a move");
            return Move.ASK_OUT;
        }

        // else if (negative >= 3) {
        //     System.out.println("mostly negative emotions, check other factors --> ");
        //     if (currentState.getSobriety().equals(Sobriety.SOBER) || currentState.getSobriety().equals(Sobriety.TIPSY)) {
        //         System.out.println("Agent is mostly sober --> check environment");
        //         if (currEnvironment == Environment.NO_GO) {
        //             System.out.println("Environment is not suitable as well --> dont approach");
        //             return Move.DO_NOT_APPROACH;
        //         } else if (currEnvironment == Environment.DEFINITELY || currEnvironment == Environment.INVITING) {
        //             System.out.println("Environment is good --> check if any positive emotions");
        //             if (positive > 1) {
        //                 System.out.println("Still have some very positive emotions --> flirt");
        //                 return Move.FLIRT;
        //             } else if (positive == 1) {
        //                 System.out.println("Slightly positive emotions --> talk more");
        //                 return Move.TALK_MORE;
        //             }
        //             System.out.println("No positive emotions --> dont approach");
        //             return Move.DO_NOT_APPROACH;

        //         }
        //     }

        // }

        Boolean hasPositiveFeelings = false;

        if (dominantEmotion == EmotionalState.IMPRESSED && secondaryEmotion == EmotionalState.HAPPY
        || dominantEmotion == EmotionalState.AROUSED && (secondaryEmotion == EmotionalState.HAPPY || secondaryEmotion == EmotionalState.IMPRESSED)
        || dominantEmotion == EmotionalState.IMPRESSED && secondaryEmotion == EmotionalState.AROUSED
        || dominantEmotion == EmotionalState.HAPPY && (secondaryEmotion == EmotionalState.AROUSED || secondaryEmotion == EmotionalState.IMPRESSED)) {
            hasPositiveFeelings = true;
        }

        Boolean hasNegativeFeelings = false;

        if(dominantEmotion == EmotionalState.SAD && (secondaryEmotion == EmotionalState.ANGRY || secondaryEmotion == EmotionalState.BORED)
        || (dominantEmotion == EmotionalState.ANGRY || dominantEmotion == EmotionalState.BORED) && secondaryEmotion == EmotionalState.SAD) {
            hasNegativeFeelings = true;
        }

        Boolean isAroused = false;

        if(dominantEmotion == EmotionalState.AROUSED || secondaryEmotion == EmotionalState.AROUSED) {
            isAroused = true;
        }

        Boolean isConfused = false;

        if(dominantEmotion == EmotionalState.CONFUSED || secondaryEmotion == EmotionalState.CONFUSED) {
            isConfused = true;
        }

        Boolean isBored = false;

        if(dominantEmotion == EmotionalState.BORED) {
            isBored = true;
        }
        System.out.println("Has positive feelings: " + hasPositiveFeelings);
        System.out.println("Has negative feelings: " + hasNegativeFeelings);
        System.out.println("Aroused: " + isAroused);
        System.out.println("Bored: " + isBored);
        System.out.println("Confused: " + isConfused);
        //Prioritize dominant negative emotions for decisions for what move to make over logic based in traits
        if(dominantEmotion == EmotionalState.ANGRY || secondaryEmotion == EmotionalState.ANGRY) {
            System.out.println("EMOTION: If emotional state is angry, no reason to make a move or pursue, do not approach");
            return Move.DO_NOT_APPROACH;
        }



        if (currentState.getSobriety().equals(Sobriety.SOBER) || currentState.getSobriety().equals(Sobriety.TIPSY)) {
            System.out.println("The person is sober or is a little tipsy, there is not conflict --> go to the next method");
            //looking for long term

            if (currentState.getRelationshipGoal().equals(RelationshipGoal.LONG_TERM)) {
                System.out.println("If the person goal is looking for a long term relationship, there is no conflict --> go to the next method ");
                if (Main.physicalAttractionImportance >= 0 && Main.personalityImportance >= 0) {
                    System.out.println("Agent thinks physical and personality attraction are somewhat important --> check if they are seen");
                    if ((potentialMatePersonality.equals(Level.HIGH) || potentialMatePersonality.equals(Level.MEDIUM))
                    && potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) {
                        System.out.println("If the potential mate has good personality and an average physique or a an average personality and a good physique"
                            + ", there is no conflict --> go to the next method");
                        //high personality/physical attractiveness
                        if (!currentState.getEnvironment().equals(Environment.NO_GO)) {
                            System.out.println("If the environment is not inappropriate, there is no conflict --> go to the next method");
                            //attractive person, looking for long term, environment is perfect, make your move
                            if (currentState.getEnvironment().equals(Environment.DEFINITELY) && hasPositiveFeelings) {
                                System.out.println("EMOTION: If the environment is well suited and emotional state is positive, then make a move immediately ");
                                return Move.ASK_OUT;
                            }

                            if (hasNegativeFeelings && potentialMate.getAura() == Aura.BOOSTS_MY_EGO) {
                                System.out.println("EMOTION: The agent is experiencing negative feelings, but the potential mate is boosting my ego --> make a move");
                                return Move.ASK_OUT;
                            }
                            if (currentState.getMateState().equals(MateState.ALREADY_CRUSH)) {
                                System.out.println("If the person already has a crush on the potential mate --> go to the next method");
                                if (currentState.getEnvironment().equals(Environment.INVITING) && hasPositiveFeelings) {
                                    System.out.println("EMOTION: if the environment is appropriate and emotional state is positive --> make a move");
                                    return Move.ASK_OUT;
                                } else if (hasNegativeFeelings) {
                                    System.out.println("EMOTION: if the environment is appropriate but agent is sad or angry --> talk more to the crush");
                                    return Move.TALK_MORE;
                                }
                            } else if (currentState.getMateState().equals(MateState.FRIENDS)) {
                                System.out.println("If the potential mate is a friend --> go to the next method");
                                if (currentState.getEnvironment().equals(Environment.INVITING) || isAroused) {
                                    System.out.println("EMOTION: If the environment is inviting or feeling aroused --> flirt");
                                    return Move.FLIRT;
                                }
                            }
                            //barely know the person, environment isn't perfect, talk to them more
                            else {
                                if(shownInterestLevel.equals(Level.HIGH) || isAroused) {
                                    System.out.println("If the mate shows a high level of interest, despite environmental circumstances and user state is aroused --> flirt");
                                    return Move.FLIRT;
                                }
                                System.out.println("If the person barely knows the potential mate, the environment isn't perfect --> keep talking");
                                return Move.TALK_MORE;
                            }
                        }
                        if (hasPositiveFeelings) {
                            System.out.println("EMOTION: If the person is in a positive mood despite a bad environment --> talk more");
                            return Move.TALK_MORE;
                        }
                        System.out.println("The environment is bad and feeligns aren't positive--> do not approach");
                        //environment is a no go so don't approach
                        return Move.DO_NOT_APPROACH;
                    }
                }
                if (hasPositiveFeelings) {
                    System.out.println("EMOTION: If the person is in a positive mood --> Make a move");
                    return Move.ASK_OUT;
                } else {
                    System.out.println("No emotions send a strong signal, but there are positives --> talk more");
                    return Move.TALK_MORE;
                }

            } else if (currentState.getRelationshipGoal().equals(RelationshipGoal.ONE_NIGHT)) {
                System.out.println("If the person's goal is a one night stand --> then go to the next method");
                //dont take into account personality for one night goal
                if ((potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) && Main.physicalAttractionImportance > 0) {
                    System.out.println("If the potential mate has a good or even an average physique, and the agent cares about physical attraction --> go to the next method");
                    if (!currentState.getEnvironment().equals(Environment.NO_GO)) {
                        System.out.println("If the environment is not inappropriate -> go to the next method");
                        if (currentState.getEnvironment().equals(Environment.BE_CONFIDENT) || hasPositiveFeelings) {
                            System.out.println("EMOTION: if the environment is making the person feel confident or emotional state is good --> flirt");
                            return Move.FLIRT;
                        } else if (hasNegativeFeelings && potentialMate.getAura() == Aura.BOOSTS_MY_EGO) {
                            System.out.println("EMOTION: The agent is experiencing negative feelings, but the potential mate is boosting my ego --> make a move");
                            return Move.FLIRT;
                        } else {
                            System.out.println("No emotions are really influential right now, so will just talk more");
                            return Move.TALK_MORE;
                        }
                    }
                }
                System.out.println("None of the criteria matches the agents preferences for a one night stand --> do not approach");
                return Move.DO_NOT_APPROACH;
            } else {
                System.out.println("If the person is not sure what relationship he is looking for, nothin specific to look for --> go to the next method");
                //unsure of relationship goal
                if (Main.physicalAttractionImportance >= 2 || Main.physicalAttractionImportance >= 2) {
                    if ((potentialMatePersonality.equals(Level.HIGH) || potentialMatePersonality.equals(Level.MEDIUM))
                        && potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) {
                          System.out.println("If the potential mate has good personality and an average physique or a an average personality and a good physique"
                            + ", there is no conflict --> go to the next method");
                        if (!currentState.getEnvironment().equals(Environment.NO_GO)) {
                            if(shownInterestLevel.equals(Level.HIGH) || isAroused) {
                                System.out.println("EMOTION: If the mate shows a high level of interest, despite environmental circumstances, or user state is aroused --> flirt");
                                return Move.FLIRT;
                            }
                            if(isConfused) {
                                System.out.println("If the agent is confused --> just keep talking ");
                                return Move.TALK_MORE;
                            }
                            System.out.println("If the environment is not inconveninent --> just keep talking ");
                            return Move.TALK_MORE;
                        }
                        System.out.println("--> do not approach");
                        return Move.DO_NOT_APPROACH;
                    }
                } else {
                    if (hasNegativeFeelings && potentialMate.getAura() == Aura.BOOSTS_MY_EGO && Main.auraImportance >= 2) {
                        System.out.println("EMOTION: The agent is experiencing negative feelings, but the potential mate is boosting my ego, and I find that important --> make a move");
                        return Move.ASK_OUT;
                    } else if (hasNegativeFeelings) {
                        System.out.println("EMOTION: agent is experiencing negative emotions and nothing is helping right now --> don't approach");
                        return Move.DO_NOT_APPROACH;
                    }
                }

                System.out.println("If none of the previous method conflict -> make a move");
                return Move.ASK_OUT;
            }
        }

        //userState is intoxicated
        else {
            //user is drunk
            if (currentState.getSobriety().equals(Sobriety.DRUNK)) {
                System.out.println("If the person is drunk, he is more likely to make a move --> go to the next method");
                 //looking for long term
                if (currentState.getRelationshipGoal().equals(RelationshipGoal.LONG_TERM)) {
                    System.out.println("If he is looking for a long term relationship, no conflict --> go to the next method");
                    if ((potentialMatePersonality.equals(Level.HIGH) || potentialMatePersonality.equals(Level.MEDIUM))
                    && potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) {
                          System.out.println("If the potential mate has good personality and an average physique or a an average personality and a good physique"
                        + ", there is no conflict --> go to the next method");
                        //high personality/physical attractiveness
                        if (!currentState.getEnvironment().equals(Environment.NO_GO)) {
                            System.out.println("If the environment is not inappropriate, there is no conflict --> go to the next method");
                            //attractive person, looking for long term, environment is perfect, make your move
                            if (currentState.getEnvironment().equals(Environment.DEFINITELY) || hasPositiveFeelings) {
                                System.out.println("EMOTION: If the environment is well suited or emotional state is positive, then make move immediately");
                                return Move.ASK_OUT;
                            }
                            if (currentState.getMateState().equals(MateState.ALREADY_CRUSH)) {
                                System.out.println("If the potentialmate is already the person's crush -> go the next method");
                                if (currentState.getEnvironment().equals(Environment.INVITING) || hasPositiveFeelings) {
                                    System.out.println("EMOTION: If the the environment is inviting or has positive feelings --> make a move");
                                    return Move.ASK_OUT;
                                }
                            }
                            if (hasNegativeFeelings && potentialMate.getAura() == Aura.BOOSTS_MY_EGO) {
                                System.out.println("EMOTION: The agent is experiencing negative feelings, but the potential mate is boosting my ego --> make a move");
                                return Move.ASK_OUT;
                            }
                            //environment is good, userState is drunk, flirt more
                            if (currentState.getEnvironment().equals(Environment.INVITING) || isAroused) {
                                System.out.println("EMOTION: If the environment is inviting or person is aroused then flirt");
                                return Move.FLIRT;
                            }
                            //environment isn't perfect, talk to them more
                            else {
                                if(shownInterestLevel.equals(Level.HIGH) || isAroused || hasPositiveFeelings) {
                                    System.out.println("EMOTION: If the mate shows a high level of interest, despite environmental circumstances, and emotional state is positive or aroused --> flirt");
                                    return Move.FLIRT;
                                }
                                if(isConfused) {
                                    System.out.println("If the environment is not perfect or confusing --> just keep talking");
                                    return Move.TALK_MORE;
                                }
                                System.out.println("If the environment is not perfect --> just keep talking");
                                return Move.TALK_MORE;
                            }
                        }
                        if(shownInterestLevel.equals(Level.HIGH) || isAroused) {
                            System.out.println("EMOTION: If the mate shows a high level of interest or emotional state is aroused, despite environmental circumstances --> talk more");
                            return Move.TALK_MORE;
                        }
                        //environment is a no go so don't approach
                        if(isBored) {
                            System.out.println("EMOTION: The environment is not appropriate or feeling of boredom --> do not make a move");
                            return Move.DO_NOT_APPROACH;
                        }
                            System.out.println("The environment is not appropriate --> do not make a move");
                            return Move.DO_NOT_APPROACH;
                    }
                    System.out.println("If none of the previous methods conflict --> make a move");
                    return Move.ASK_OUT;
                } else if (currentState.getRelationshipGoal().equals(RelationshipGoal.ONE_NIGHT)) {
                    System.out.println("if the person's goal is a one night stand --> go to next method");
                    //dont take into account personality for one night goal
                    if (potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) {
                        System.out.println("if the potential mate have a good or an average physique at least --> go to next method");
                        if (!currentState.getEnvironment().equals(Environment.NO_GO)) {
                            if(isArousedLevel > 0) {
                                System.out.println("if the environment is not perfect, but just looking for a one night stand -->");
                                System.out.println("EMOTION: feeling of arousal --> flirt");
                                    //environment is not terrible, looking for one night, attractive potential mate, flirt more
                                    return Move.FLIRT;
                                }
                            }
                        }
                        if (hasNegativeFeelings && potentialMate.getAura() == Aura.BOOSTS_MY_EGO) {
                            System.out.println("EMOTION: The agent is experiencing negative feelings, but the potential mate is boosting my ego --> flirt");
                            return Move.FLIRT;
                        }
                        if(isBored) {
                            System.out.println("EMOTION: the potential mate physique is not good and person is feeling bored --> do not make a move");
                            return Move.DO_NOT_APPROACH;
                        }
                        if (Main.physicalAttractionImportance >= 2) {
                            System.out.println("EMOTION: neutral feeling, no emotional importance and the potential mate physique is not good, and I care about physical attraction --> do not make a move");
                            return Move.DO_NOT_APPROACH;
                        } else {
                            System.out.println("the potential mate physique is not good, but that's not super important --> talk more");
                            return Move.TALK_MORE;
                        }
                } else {
                    //unsure of relationship goal
                    if ((potentialMatePersonality.equals(Level.HIGH) || potentialMatePersonality.equals(Level.MEDIUM))
                    && potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) {
                        System.out.println("If the potential mate has good personality and an average physique or a an average personality and a good physique"
                        + ", there is no conflict --> go to the next method");
                        //environment is bad but userState is drunk, so talk more
                        if (currentState.getEnvironment().equals(Environment.NO_GO) || hasPositiveFeelings || isAroused) {
                            System.out.println("EMOTION: If the environment is bad but the person is drunk and there exist positive feelings --> talk more");
                            return Move.TALK_MORE;
                        } else {
                            if(isConfused) {
                                System.out.println("person is confused but mate is attractive-->flirt");
                                return Move.FLIRT;
                            }
                            if(isBored) {
                                System.out.println("person is bored but mate is attractive-->flirt");
                                return Move.FLIRT;
                            }
                            System.out.println("-->talk more");
                            return Move.TALK_MORE;
                        }
                    }
                    else {
                        if(isBored) {
                            System.out.println("person is boring and low attractiveness-->do not approach anymore");
                            return Move.DO_NOT_APPROACH;
                        }
                        System.out.println("the potential mate have a bad physique and the person is drunk --> do not approach");
                        return Move.DO_NOT_APPROACH;
                    }

                }
            }
            //disregard most social cues when extremely inebriated
            if (currentState.getSobriety().equals(Sobriety.BLACKOUT)) {
                System.out.println("if the person is totally blacked out, he will definetly make a move --> go to next method");
                if (currentState.getEnvironment().equals(Environment.NO_GO)) {
                    if(shownInterestLevel.equals(Level.HIGH) || isAroused || hasPositiveFeelings) {
                        System.out.println("EMOTION: If the mate shows a high level of interest, despite environmental circumstances, or positive emotional state --> flirt");
                        return Move.FLIRT;
                    }
                    System.out.println("if the environment is bad --> talk more");
                    return Move.TALK_MORE;
                } else {
                    System.out.println("if the environment is not bad --> flirt");
                    return Move.FLIRT;
                }
            }
        }

        System.out.println(" --> do not approach");
        return Move.DO_NOT_APPROACH;


    }




}