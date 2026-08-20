//Abstract base class representing a generic arcade game.
public abstract class ArcadeGame {
    private String gameId;
    private String name;
    private String gameType;
    private int pricePerPlay;

    public ArcadeGame(String gameId, String name, String gameType, int pricePerPlay) throws InvalidGameIdException {

            if (!isValidGameId(gameId)) {
            throw new InvalidGameIdException("Invalid Game id:" + gameId);
            }

            this.gameId = gameId;
            this.name = name;
            this.gameType = gameType;
            this.pricePerPlay = pricePerPlay;

    }
// boolean method for checking if the game id is valid has exactly 10 alphanumeric characters.
private boolean isValidGameId(String gameId){
        if (gameId == null || gameId.length()  !=10){
            return false;
        }
    return gameId.chars().allMatch(Character::isLetterOrDigit);
}
public String getGameId() {

        return gameId;
    }
public String getName() {

        return name;
}

public int getPricePerPlay() {

        return pricePerPlay;
}

public String getGameType() {
        return gameType;
}
//abstract method to be implemented by the subclasses.
public abstract int calculatePrice(boolean peak);


    @Override
    public String toString() {

        return "Game ID : " + gameId + ", Name: " + name + ", Price per Play: " + pricePerPlay;
    }

    public int getAgeLimit() {

        return 0;
    }
}

