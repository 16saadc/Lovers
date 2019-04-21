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

    public MateFinder(PotentialMate mate, UserState currentState) {
        potentialMate = mate;
        this.currentState = currentState;
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


        //adjust emotion based on important traits
        int highTraitHighImportanceCount = 0;
        int highTraitMediumImportanceCount = 0;
        int lowTrait_highImportanceCount = 0;

        //count high important traits
        if (Main.confidenceImportance > 4 && potentialMate.getConfidence().getLevel() == Level.HIGH) highTraitHighImportanceCount++;
        if (Main.intelligenceImportance > 4 && potentialMate.getIntelligence().getOverallLevel() == Level.HIGH) highTraitHighImportanceCount++;
        if (Main.auraImportance > 4 && potentialMate.getAura() == Aura.BOOSTS_MY_EGO) highTraitHighImportanceCount++;
        if (Main.physicalAttractionImportance > 4 && potentialMatePhysical == Level.HIGH) highTraitHighImportanceCount++;
        if (Main.personalityImportance > 4 && potentialMatePersonality == Level.HIGH) highTraitHighImportanceCount++;


        if (Main.confidenceImportance > 2 && potentialMate.getConfidence().getLevel() == Level.HIGH) highTraitMediumImportanceCount++;
        if (Main.intelligenceImportance > 2 && potentialMate.getIntelligence().getOverallLevel() == Level.HIGH) highTraitMediumImportanceCount++;
        if (Main.auraImportance > 2 && potentialMate.getAura() == Aura.BOOSTS_MY_EGO) highTraitMediumImportanceCount++;
        if (Main.physicalAttractionImportance > 2 && potentialMatePhysical == Level.HIGH) highTraitMediumImportanceCount++;
        if (Main.personalityImportance > 2 && potentialMatePersonality == Level.HIGH) highTraitMediumImportanceCount++;


        if (Main.confidenceImportance > 2 && potentialMate.getConfidence().getLevel() == Level.LOW) lowTrait_highImportanceCount++;
        if (Main.intelligenceImportance > 2 && potentialMate.getIntelligence().getOverallLevel() == Level.LOW) lowTrait_highImportanceCount++;
        if (Main.auraImportance > 2 && (potentialMate.getAura() == Aura.BOOSTS_MY_EGO
            || potentialMate.getAura() == Aura.TALKS_DOWN
            || potentialMate.getAura() == Aura.IGNORANT)) lowTrait_highImportanceCount++;
        if (Main.physicalAttractionImportance > 2 && potentialMatePhysical == Level.LOW) lowTrait_highImportanceCount++;
        if (Main.personalityImportance > 2 && potentialMatePersonality == Level.LOW) lowTrait_highImportanceCount++;



        if (highTraitHighImportanceCount > 4) {
            System.out.println("EMOTION: This mate has 4 or more traits that I REALLY like, so I am more impressed and happier");
            Main.updateEmotion(EmotionalState.IMPRESSED, Level.HIGH);
            Main.updateEmotion(EmotionalState.HAPPY, Level.MEDIUM);
            return;
        } else if (highTraitHighImportanceCount > 2) {
            System.out.println("EMOTION: This mate has 2-3 traits that I REALLY like, so I am more impressed and happier");
            Main.updateEmotion(EmotionalState.IMPRESSED, Level.MEDIUM);
            Main.updateEmotion(EmotionalState.HAPPY, Level.LOW);
            return;
        } else if (highTraitHighImportanceCount > 0) {
            System.out.println("EMOTION: This mate has some traits that I REALLY like, so I am more impressed");
            Main.updateEmotion(EmotionalState.IMPRESSED, Level.MEDIUM);
            return;
        } else if (highTraitMediumImportanceCount > 4) {
            System.out.println("EMOTION: This mate has 4 or more traits that I SORT OF like, so I am more impressed and happier");
            Main.updateEmotion(EmotionalState.IMPRESSED, Level.MEDIUM);
            Main.updateEmotion(EmotionalState.HAPPY, Level.LOW);
            return;
        } else if (highTraitMediumImportanceCount > 2) {
            System.out.println("EMOTION: This mate has 2-3 traits that I SORT OF like, so I am more impressed");
            Main.updateEmotion(EmotionalState.IMPRESSED, Level.MEDIUM);
            return;
        } else if (highTraitMediumImportanceCount > 0) {
            System.out.println("EMOTION: This mate has some traits that I SORT OF like, so I am slightly more impressed");
            Main.updateEmotion(EmotionalState.IMPRESSED, Level.LOW);
            return;
        } else if (lowTrait_highImportanceCount > 4) {
            System.out.println("EMOTION: This mate has 4 or more traits that I DO NOT like, so I am a lot more sad and bored");
            Main.updateEmotion(EmotionalState.SAD, Level.HIGH);
            Main.updateEmotion(EmotionalState.BORED, Level.MEDIUM);
            return;
        } else if (lowTrait_highImportanceCount > 2) {
            System.out.println("EMOTION: This mate has 2-3 traits that I DO NOT like, so I am slightly more sad and mored");
            Main.updateEmotion(EmotionalState.SAD, Level.MEDIUM);
            Main.updateEmotion(EmotionalState.BORED, Level.LOW);
            return;
        } else if (lowTrait_highImportanceCount > 0) {
            System.out.println("EMOTION: This mate has some traits that I DO NOT like, so I am slightly more sad");
            Main.updateEmotion(EmotionalState.SAD, Level.MEDIUM);
            return;
        }
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


        adjustEmotionsBasedOnLearning();
        Emotion emotionalState = Main.emotion;


        EmotionalState dominantEmotion = emotionalState.getDominantEmotion();
        EmotionalState secondaryEmotion = emotionalState.getSecondaryEmotion();
        EmotionalState tertiaryEmotion = emotionalState.getTertiaryEmotion();


        System.out.println("===========");
        System.out.println("Potential mate: " + potentialMate.getName());
        System.out.println("Potential mate attractiveness level: " + potentialMatePhysical);
        System.out.println("Potential mate personality level: " + potentialMatePersonality);
        System.out.println("Environment: " + currentState.getEnvironment());
        System.out.println("Potential mate interest level: " + shownInterestLevel);

        System.out.println("Emotions: " + dominantEmotion + ", " + secondaryEmotion + ", " + tertiaryEmotion);

        Boolean hasPositiveFeelings = false;

        if(dominantEmotion == EmotionalState.IMPRESSED || dominantEmotion == EmotionalState.HAPPY
        || dominantEmotion == EmotionalState.AROUSED || secondaryEmotion == EmotionalState.HAPPY || secondaryEmotion == EmotionalState.AROUSED
        || secondaryEmotion == EmotionalState.IMPRESSED) {
            hasPositiveFeelings = true;
        }

        Boolean hasNegativeFeelings = false;

        if(dominantEmotion == EmotionalState.SAD || dominantEmotion == EmotionalState.ANGRY
        || secondaryEmotion == EmotionalState.SAD || secondaryEmotion == EmotionalState.ANGRY) {
            hasNegativeFeelings = true;
        }

        Boolean isAroused = false;

        if(dominantEmotion == EmotionalState.AROUSED || secondaryEmotion == EmotionalState.AROUSED || tertiaryEmotion == EmotionalState.AROUSED) {
            isAroused = true;
        }

        Boolean isConfused = false;

        if(dominantEmotion == EmotionalState.CONFUSED || secondaryEmotion == EmotionalState.CONFUSED) {
            isConfused = true;
        }

        Boolean isBored = false;

        if(dominantEmotion == EmotionalState.BORED || secondaryEmotion == EmotionalState.BORED) {
            isBored = true;
        }

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
                        if (currentState.getMateState().equals(MateState.ALREADY_CRUSH)) {
                            System.out.println("If the person already has a crush on the potential mate --> go to the next method");
                            if (currentState.getEnvironment().equals(Environment.INVITING) && hasPositiveFeelings) {
                                System.out.println("EMOTION: if the environment is appropriate and emotional state is positive --> make a move");
                                return Move.ASK_OUT;
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
                    System.out.println("If any of the previous methods fail, there is a conflict --> do not approach");
                    //environment is a no go so don't approach
                    return Move.DO_NOT_APPROACH;
                }
                System.out.println("If the person goal is a long term relationship --> make a move");
                return Move.ASK_OUT;
            } else if (currentState.getRelationshipGoal().equals(RelationshipGoal.ONE_NIGHT)) {
                System.out.println("If the person's goal is a one nigh stand --> then go to the next method");
                //dont take into account personality for one night goal
                if (potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) {
                    System.out.println("If the potential mate has a good or even an average physique --> go to the next method");
                    if (!currentState.getEnvironment().equals(Environment.NO_GO)) {
                        System.out.println("If the environment is not inappropriate -> go to the next method");
                        if (currentState.getEnvironment().equals(Environment.BE_CONFIDENT) || hasPositiveFeelings) {
                            System.out.println("EMOTION: if the environment is making the person feel confident or emotional state is good --> talk more");
                            return Move.TALK_MORE;
                        } else if(hasPositiveFeelings) {
                            System.out.println("EMOTION: emotional state is positive --> flirt");
                            return Move.FLIRT;
                        }
                    }
                    System.out.println("If any of the previous methods conflict --> do not approach");
                    return Move.DO_NOT_APPROACH;
                }
            } else {
                System.out.println("If the person is not sure what relationship he is looking for, no conflict --> go to the next method ");
                //unsure of relationship goal
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
                            System.out.println("If the environment is not conveninent or the user is confused --> just keep talking ");
                            return Move.TALK_MORE;
                        }
                        System.out.println("If the environment is not conveninent --> just keep talking ");
                        return Move.TALK_MORE;
                    }
                    System.out.println("--> do not approach");
                    return Move.DO_NOT_APPROACH;
                }
                else {
                    System.out.println("If none of the previous method conflict -> make a move");
                    return Move.ASK_OUT;
                }
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
                        if (!currentState.getEnvironment().equals(Environment.NO_GO) || isAroused) {
                            System.out.println("if the environment is not perfect, but just looking for a one night stand or is aroused --> flirt more");
                                //environment is not terrible, looking for one night, attractive potential mate, flirt more
                                return Move.FLIRT;
                            }
                        }
                        if(isBored) {
                            System.out.println(" the potential mate physique is not good and person is boring --> do not make a move");
                            return Move.DO_NOT_APPROACH;
                        }
                        System.out.println(" the potential mate physique is not good --> do not make a move");
                        return Move.DO_NOT_APPROACH;
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