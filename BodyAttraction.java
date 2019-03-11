public class BodyAttraction {
    private Level fat;
    private Level muscle;
    private Level hair;

    public BodyAttraction(Level fat, Level muscle, Level hair) {
        this.fat = fat;
        this.muscle = muscle;
        this.hair = hair;
    }

    public void setFat(Level fat) {
        this.fat = fat;
    }

    public void setHair(Level hair) {
        this.hair = hair;
    }

    public void setMuscle(Level muscle) {
        this.muscle = muscle;
    }

    public Level getFat() {
        return fat;
    }

    public Level getMuscle() {
        return muscle;
    }

    public Level getHair() {
        return hair;
    }

}