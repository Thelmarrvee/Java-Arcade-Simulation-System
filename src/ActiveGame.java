public class ActiveGame extends ArcadeGame  {
    private int ageLimit;

    public ActiveGame(String gameId, String name, int pricePerPlay, int ageLimit) throws InvalidGameIdException {
        super(gameId, name,"active" , pricePerPlay);
        if (!gameId.startsWith("A") || gameId.length() != 10) {
            throw new InvalidGameIdException( "Invalid game ID:" + gameId+ "(Game ID should start with A)");
        }

        this.ageLimit = ageLimit;
    }

    public int getAgeLimit() {

        return this.ageLimit;
    }

    @Override
    public int calculatePrice(boolean peak) {
        if (peak) {
            return getPricePerPlay();
        } else {
            return (int) (getPricePerPlay() * 0.8);

        }

    }
// Overriden method for getting the game type.
    @Override
    public String getGameType() {

        return "Active";
    }

    @Override

    public String toString() {
        return super.toString() + ", Age Limit: " + ageLimit;

    }
}


