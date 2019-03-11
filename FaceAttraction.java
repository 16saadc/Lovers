public class FaceAttraction {

    private Level nice_smile;
    private Level nice_eyes;
    private Level nice_jawline;


    public FaceAttraction(Level nice_smile, Level nice_eyes, Level nice_jawline) {
        this.nice_jawline = nice_jawline;
        this.nice_smile = nice_smile;
        this.nice_eyes = nice_eyes;
    }

    public void setEyes(Level eyes) {
        this.nice_eyes = eyes;
    }

    public void setSmile(Level smile) {
        this.nice_smile = smile;
    }

    public void setJawline(Level jaw) {
        this.nice_jawline = jaw;
    }

    public Level getJawline() {
        return nice_jawline;
    }

    public Level getSmile() {
        return nice_smile;
    }

    public Level getEyes() {
        return nice_eyes;
    }

}