public class testingfile {
    public static void main(String[] args) {
        // TESTING CODE DUMP
        System.out.println("Enter a y (A-J) coordinate to check if there is a ship there.");
        int inputy = AI.getInputY();
        System.out.println("Enter an x (1-10) coordinate to check if there is a ship there.");
        int inputx = AI.getInputX();

        char keyChar = Coordinate.convertIntToChar(inputy);
        String accessKey = String.valueOf(keyChar) + String.valueOf(inputx);
        System.out.println(Game.playerMapOfCoor.get(accessKey));

        // AI.place(playerPlacementBoard);
        // Game.printPlacementArray(playerPlacementBoard);

    }
}
