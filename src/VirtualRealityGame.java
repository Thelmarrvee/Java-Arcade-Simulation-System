// Virtual Reality Game extend of the active game class.
public class VirtualRealityGame extends ActiveGame {
    private String equipmentUsed;

    public VirtualRealityGame(String gameId, String name, int pricePerPlay, int ageLimit, String equipmentUsed) throws InvalidGameIdException {
        super(gameId, name, pricePerPlay, ageLimit);
        if (!gameId.startsWith("AV") || gameId.length() != 10) {
            throw new InvalidGameIdException("invalid Game ID:" + gameId+ "(Virtual reality game id should start with AV)");
        }

        this.equipmentUsed = equipmentUsed;
    }
    private static boolean isValidVirtualRealityGameId(String gameId) {
        return gameId.startsWith("AV") && gameId.length() == 10;
    }

    public String getEquipmentUsed() {

        return equipmentUsed;
    }
//Method for calculating off-peak discounts.
@Override
    public int calculatePrice(boolean peak) {
    if (peak) {
        return getPricePerPlay();
    }else if (equipmentUsed.equals("headsetOnly")) {
        return (int) (getPricePerPlay() * 0.9);
    } else if (equipmentUsed.equals("headsetAndController")) {
        return (int) (getPricePerPlay() * 0.95);
    } else {
        return getPricePerPlay();
    }
}
   //@Override
    public String getGameType() {
        return "virtualReality";

    }
        @Override
        public String toString() {
            return super.toString() + " Equipment: " + equipmentUsed;
        }
    }



