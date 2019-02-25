public class RelationshipStatus {

	private Status status;
	private Loyalty loyalty;

	public RelationshipStatus(Status status, Loyalty loyalty) {
        this.status = status;
        this.loyalty = loyalty;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Loyalty getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }

}


