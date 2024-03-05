public class Votes {

    private int votersTotal;

    private int validVotes;

    private int blankVotes;

    private int nullVotes;

    public Votes() {
    }

    public Votes(int votersTotal, int validVotes, int blankVotes, int nullVotes) {
        this.votersTotal = votersTotal;
        this.validVotes = validVotes;
        this.blankVotes = blankVotes;
        this.nullVotes = nullVotes;
    }

    public float percentageValidVotes(int votersTotal, int validVotes) {
        return (float) (validVotes * 100) / votersTotal;
    }

    public float percentageBlankVotes(int votersTotal, int blankVotes) {
        return (float) (blankVotes * 100) / votersTotal;
    }

    public float percentageNullVotes(int votersTotal, int nullVotes) {
        return (float) (nullVotes * 100) / votersTotal;
    }

    public int getVotersTotal() {
        return votersTotal;
    }

    public void setVotersTotal(int votersTotal) {
        this.votersTotal = votersTotal;
    }

    public int getValidVotes() {
        return validVotes;
    }

    public void setValidVotes(int validVotes) {
        this.validVotes = validVotes;
    }

    public int getBlankVotes() {
        return blankVotes;
    }

    public void setBlankVotes(int blankVotes) {
        this.blankVotes = blankVotes;
    }

    public int getNullVotes() {
        return nullVotes;
    }

    public void setNullVotes(int nullVotes) {
        this.nullVotes = nullVotes;
    }
}
