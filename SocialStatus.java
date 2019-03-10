public class SocialStatus {
    private Popularity popularity;
    private Education education;
    private RelationshipStatus relationshipStatus;

    public SocialStatus(Popularity popularity, Education education, RelationshipStatus relationshipStatus) {
        this.popularity = popularity;
        this.education = education;
        this.relationshipStatus = relationshipStatus;
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