public class SocialStatus {
    private Popularity popularity;
    private Education education;
    private RelationshipStatus relationshipStatus;

    public SocialStatus(Popularity popularity, Education education, RelationshipStatus relationshipStatus) {
        this.popularity = popularity;
        this.education = education;
        this.relationshipStatus = relationshipStatus;
    }

    public Level calculateLevel() {
        int pop = 0;
        int edu = 0;
        int rel = 0;
        if (popularity == Popularity.VERY_POPULAR) pop += 2;
        if (education == Education.A_STUDENT || education == Education.INTERESTING_WORK) edu += 2;
        if (relationshipStatus.getStatus() == Status.SINGLE) rel += 2;

        if (popularity == Popularity.AVERAGE) pop++;
        if (education == Education.AVERAGE_STUDENT) edu++;
        if (relationshipStatus.getStatus() == Status.COMPLICATED) rel++;


        int total = pop + edu + rel;
        if (total >= 5) return Level.HIGH;
        if (total >= 3) return Level.MEDIUM;
        return Level.LOW;

    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public RelationshipStatus getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(RelationshipStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

}