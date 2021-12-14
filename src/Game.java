public class Game {
    // O - not hit or occupied
    // X - occupied by a ship, hit
    // M - not occupied by a ship, hit
    // S - occupied by a ship, not hit
    public static void printPlacementArray(Coordinate array[][]) {
        for (int i = 0; i <= 10; i++) {
            char ind = (char) ('A' + i-1);
            if (i==0) {
                for (int j = 0; j <=10 ; j++) {
                    System.out.print(j + " ");
                }
            }
            if (i > 0) {
                System.out.print(ind + " ");
            }
            for (int j = 1; j <= 10; j++) {
                if (i == 0) {
                    continue;
                } else {
                    Coordinate cur = array[i][j];
                    boolean isShip = cur.getIsShip(), isHit = cur.getIsHit();
                    if (isHit && isShip) {
                        System.out.print("X ");
                    } else if (isHit && !isShip) {
                        System.out.print("M ");
                    } else if(!isHit && isShip){
                        System.out.print("S ");
                    } else{
                        System.out.print("O ");
                    }
                }
                
            }
            System.out.println();
        }
    }
    


}
