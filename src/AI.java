public class AI {
    boolean isHunting;
    Coordinate test;

    // placing algorithm
    public static void place() {
        // for every ship
        for (int shipSize = 2; shipSize <= 5; shipSize++) {
            boolean orientationV = (int) ((Math.random() * 2) + 1) == 1 ? true : false;
            
            if (orientationV) {
                
            }

            // int x = (int) (Math.random() * (10 - 5));
            // int y = (int) (Math.random() * (10 - 5));
            // System.out.println(x);
            // System.out.println(y);
            // System.out.println((int) ((Math.random() * 2) + 1));
        }

    }

    public static void main(String[] args) {
        place();
    }

}
