public class VirtualRealityGameTest {
    public static void main(String[] args) {
        try {
            // Testing valid VR games
            VirtualRealityGame vrGame1 = new VirtualRealityGame("AV12345678", "VR Shooting", 500, 12, "headsetOnly");
            VirtualRealityGame vrGame2 = new VirtualRealityGame("AV87654321", "VR Adventurous", 700, 15, "headsetAndController");
            VirtualRealityGame vrGame3 = new VirtualRealityGame("AVABCDEFGH", "VR Soccer", 1000, 18, "Full-Body Tracking");

            System.out.println(vrGame1);
            System.out.println("Peak Price: " + vrGame1.calculatePrice(true) + " pence");
            System.out.println("Off-Peak Price: " + vrGame1.calculatePrice(false) + " pence");

            System.out.println(vrGame2);
            System.out.println("Peak Price: " + vrGame2.calculatePrice(true) + " pence");
            System.out.println("Off-Peak Price: " + vrGame2.calculatePrice(false) + " pence");

            System.out.println(vrGame3);
            System.out.println("Peak Price: " + vrGame3.calculatePrice(true) + " pence");
            System.out.println("Off-Peak Price: " + vrGame3.calculatePrice(false) + " pence");

            // Invalid Game ID
            VirtualRealityGame vrGame4 = new VirtualRealityGame("A123456789", "Invalid VR", 500, 12, "Headset Only");

        } catch (InvalidGameIdException | IllegalArgumentException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }
    }
}

