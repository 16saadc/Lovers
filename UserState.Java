public class UserState {

    /*
     -- User state schema:
        - relationship goals: {long term, one-night-stand, not-sure, ...}
        - sobriety: {will not remember this, sober, tipsy, wasted, ...}
        - potentialMateState: {talked, never met, friends, had a crush for a while, ...}
        -
     */

    private RelationshipGoal goal;
    private Sobriety sobriety;
    private MateState mateState;
    private Environment environment;


    public UserState(RelationshipGoal goal, Sobriety sobriety, MateState mateState, Environment environment) {
        this.goal = goal;
        this.sobriety = sobriety;
        this.mateState = mateState;
        this.environment = environment;
    }


    public RelationshipGoal getRelationshipGoal() {
        return goal;
    }

    public void setRelationshipGoal(RelationshipGoal goal) {
        this.goal = goal;
    }

    public Sobriety getSobriety() {
        return sobriety;
    }

    public void setSobriety(Sobriety sobriety) {
        this.sobriety = sobriety;
    }

    public MateState getMateState() {
        return mateState;
    }

    public void setMateState(MateState mateState) {
        this.mateState = mateState;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}






