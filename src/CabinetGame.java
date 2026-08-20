
public class CabinetGame extends ArcadeGame {
 private boolean canPayOutReward;

    public CabinetGame( String gameId , String name , int pricePerPlay , boolean canPayOutReward) throws InvalidGameIdException {
        super(gameId , name, "cabinet", pricePerPlay);
     // Sees that the ID starts with 'C' and is 10 characters long and throws an invalid game exception.
        if(!gameId.startsWith("C") || gameId.length() !=10){
            throw new InvalidGameIdException( "Invalid Game Id:" + gameId +"( Cabinet game id must start with C)");
        }

        this.canPayOutReward = canPayOutReward;

    }
    public boolean isCanPayOutReward() {
        return canPayOutReward;

    }
//Applying the off-peak discounts
@Override
    public int calculatePrice(boolean peak) {
        if (peak) {
            return getPricePerPlay();
        }else if (canPayOutReward) {
            return (int) (getPricePerPlay() * 0.8);
        }else {
            return(int) (getPricePerPlay() * 0.5);
        }
        }
// Overrides the game type specifically for cabinet games.
 @Override
 public String getGameType() {

        return "Cabinet";
 }

    @Override
    public String toString() {

        return super.toString() + ", Pays Out Reward: " + (isCanPayOutReward() ? "yes" : "no");
    }
}
