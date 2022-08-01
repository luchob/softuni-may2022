package bg.softuni.mobilele.model.dto.vote;

public class VoteDTO {

  private boolean canVote;
  private Integer positive;
  private Integer negative;

  public boolean isCanVote() {
    return canVote;
  }

  public VoteDTO setCanVote(boolean canVote) {
    this.canVote = canVote;
    return this;
  }

  public int getPositive() {
    return positive;
  }

  public VoteDTO setPositive(int positive) {
    this.positive = positive;
    return this;
  }

  public int getNegative() {
    return negative;
  }

  public VoteDTO setNegative(int negative) {
    this.negative = negative;
    return this;
  }

  @Override
  public String toString() {
    return "VoteDTO{" +
        "canVote=" + canVote +
        ", positive=" + positive +
        ", negative=" + negative +
        '}';
  }
}
