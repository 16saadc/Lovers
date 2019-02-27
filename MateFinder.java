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
        
        //userState is mostly sober
        if (currentState.getSobriety().equals(Sobriety.SOBER) || currentState.getSobriety().equals(Sobriety.TIPSY)) {
            //looking for long term
            if (currentState.getRelationshipGoal().equals(RelationshipGoal.LONG_TERM)) {
                if ((potentialMatePersonality.equals(Level.HIGH) || potentialMatePersonality.equals(Level.MEDIUM)) 
                && potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) {
                    //high personality/physical attractiveness
                    if (!currentState.getEnvironment().equals(Environment.NO_GO)) {
                        //attractive person, looking for long term, environment is perfect, make your move
                        if (currentState.getEnvironment().equals(Environment.DEFINITELY)) {
                            return Move.ASK_OUT;
                        }
                        if (currentState.getMateState().equals(MateState.ALREADY_CRUSH)) {
                            if (currentState.getEnvironment().equals(Environment.INVITING)) {
                                return Move.ASK_OUT;
                            }
                        } else if (currentState.getMateState().equals(MateState.FRIENDS)) {
                            if (currentState.getEnvironment().equals(Environment.INVITING)) {
                                return Move.FLIRT;
                            }
                        }
                        //barely know the person, environment isn't perfect, talk to them more 
                        else {
                            return Move.TALK_MORE;
                        }
                    }
                    //environment is a no go so don't approach
                    return Move.DO_NOT_APPROACH;
                }
                return Move.ASK_OUT;
            } else if (currentState.getRelationshipGoal().equals(RelationshipGoal.ONE_NIGHT)) {
                //dont take into account personality for one night goal
                if (potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) {
                    if (!currentState.getEnvironment().equals(Environment.NO_GO)) {
                        if (currentState.getEnvironment().equals(Environment.BE_CONFIDENT)) {
                            return Move.TALK_MORE;
                        } else {
                            return Move.FLIRT;
                        }
                    }
                    return Move.DO_NOT_APPROACH;
                }
            } else {
                //unsure of relationship goal
                if ((potentialMatePersonality.equals(Level.HIGH) || potentialMatePersonality.equals(Level.MEDIUM)) 
                && potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) {
                    if (!currentState.getEnvironment().equals(Environment.NO_GO)) {
                        return Move.TALK_MORE;
                    }
                    return Move.DO_NOT_APPROACH;
                }
                else {
                    return Move.DO_NOT_APPROACH;
                }
                
            }
            
        }
        //userState is intoxicated  
        else {
            //user is drunk
            if (currentState.getSobriety().equals(Sobriety.DRUNK)) {
                 //looking for long term
                if (currentState.getRelationshipGoal().equals(RelationshipGoal.LONG_TERM)) {
                    if ((potentialMatePersonality.equals(Level.HIGH) || potentialMatePersonality.equals(Level.MEDIUM)) 
                    && potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) {
                        //high personality/physical attractiveness
                        if (!currentState.getEnvironment().equals(Environment.NO_GO)) {
                            //attractive person, looking for long term, environment is perfect, make your move
                            if (currentState.getEnvironment().equals(Environment.DEFINITELY)) {
                                return Move.ASK_OUT;
                            }
                            if (currentState.getMateState().equals(MateState.ALREADY_CRUSH)) {
                                if (currentState.getEnvironment().equals(Environment.INVITING)) {
                                    return Move.ASK_OUT;
                                }
                            } 
                            //environment is good, userState is drunk, flirt more
                            if (currentState.getEnvironment().equals(Environment.INVITING)) {
                                return Move.FLIRT;
                            }
                            //environment isn't perfect, talk to them more 
                            else {
                                return Move.TALK_MORE;
                            }
                        }
                        //environment is a no go so don't approach
                        return Move.DO_NOT_APPROACH;
                    }
                    return Move.ASK_OUT;
                } else if (currentState.getRelationshipGoal().equals(RelationshipGoal.ONE_NIGHT)) {
                    //dont take into account personality for one night goal
                    if (potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) {
                        if (!currentState.getEnvironment().equals(Environment.NO_GO)) {
                                //environment is not terrible, looking for one night, attractive potential mate, flirt more
                                return Move.FLIRT;
                            }
                        }
                        return Move.DO_NOT_APPROACH;
                } else {
                    //unsure of relationship goal
                    if ((potentialMatePersonality.equals(Level.HIGH) || potentialMatePersonality.equals(Level.MEDIUM)) 
                    && potentialMatePhysical.equals(Level.HIGH) || potentialMatePhysical.equals(Level.MEDIUM)) {
                        //environment is bad but userState is drunk, so talk more
                        if (currentState.getEnvironment().equals(Environment.NO_GO)) {
                            return Move.TALK_MORE;
                        } else {
                            return Move.FLIRT;
                        }
                    }
                    else {
                        return Move.DO_NOT_APPROACH;
                    }
                    
                }
            }
            //disregard most social cues when extremely inebriated
            if (currentState.getSobriety().equals(Sobriety.BLACKOUT)) {
                if (currentState.getEnvironment().equals(Environment.NO_GO)) {
                    return Move.TALK_MORE;
                } else {
                    return Move.FLIRT;
                }
            }
        }
        
        return Move.DO_NOT_APPROACH;


    }


}