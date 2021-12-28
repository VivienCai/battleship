public class testingfile {
    public static void main(String[] args) {
        // TESTING CODE DUMP
        // System.out.println("Enter a y (A-J) coordinate to check if there is a ship there.");
        // int inputy = AI.getInputY();
        // System.out.println("Enter an x (1-10) coordinate to check if there is a ship there.");
        // int inputx = AI.getInputX();

        // char keyChar = Coordinate.columnIndex(inputy);
        // String accessKey = String.valueOf(keyChar) + String.valueOf(inputx);
        // System.out.println(Game.AIMapOfCoor.get(accessKey));

        // AI.place(playerPlacementBoard);
        // Game.printPlacementArray(playerPlacementBoard);

        // if (Main.playerPlacementBoard[y][x].getIsShip()) {
        //     String accessKey = Game.getAccessKey(y, x);

        //     Ship shipHit = Game.playerMapOfCoor.get(accessKey);
        //     shipHit.addTimesHit();

        //     System.out.println("The AI hit one of your ships. It hit your: " + shipHit);
        //     System.out.println("Your " + shipHit + " has been hit " + shipHit.getTimesHit() + " times.");
        // }

        // if (Main.playerPlacementBoard[y][x].getIsShip()) {
        //     String accessKey = Game.getAccessKey(y, x);

        //     Ship shipHit = Game.playerMapOfCoor.get(accessKey);
        //     shipHit.addTimesHit();

        //     System.out.println("The AI hit one of your ships. It hit your: " + shipHit);
        //     System.out.println("Your " + shipHit + " has been hit " + shipHit.getTimesHit() + " times.");
        // }
        // else {
        //     System.out.println("The AI missed.");
        // }


        // initliaze the player board and ai board (should have 2?)
        // Prompting user to place ships
        // Game.placeShip();

        // temp... getting AI to place players ship bc we are lazy
        // AI.place(playerPlacementBoard);
        // Game.printPlacementArray(playerPlacementBoard);

        // loop through all 5 ships
    // limit the range where they can place the ships based on its size
    // ask for vertical vs horizontal orientation and "home coordinate"
    // place each ship and assign the home coordinate for the ship and check if
    // there are any ships where it is trying to be placed
    // create the ship class and put it in the arraylist
    // static Scanner sc = new Scanner(System.in);

    // public static void placeShip() {
    //     for (int i = 2; i <= 6; i++) {
    //         int shipSize = i;
    //         if (i == 6) {
    //             shipSize = 3;
    //         }
    //         System.out.printf("You are currently placing a ship that is %d units long.\n", shipSize);
    //         System.out.println(
    //                 "What orientation do you want the ship to be? Write 1 for vertical and anything else for horizontal.");
    //         boolean isV;
    //         char input = sc.next().charAt(0);
    //         if (input == '1') {
    //             isV = true;
    //         } else {
    //             isV = false;
    //         }

    //         System.out.println("Please enter an uppercase letter from A-J for your y coordinate. ");
    //         int y = AI.getInputY();
    //         System.out.println("Please enter a number from 1-10 for your x coordinate. ");
    //         int x = AI.getInputX();
    //         boolean inBounds = false;

    //         if (isV) {
    //             inBounds = boundsCheck(y, isV, shipSize);
    //         } else {
    //             inBounds = boundsCheck(x, isV, shipSize);
    //         }

    //         if (!inBounds) {
    //             System.out.println("Your ship is out of bounds. Please choose another point to place your ship.");
    //             i--;
    //             continue;
    //         } else {

    //             Coordinate home = Main.playerPlacementBoard[y][x];
    //             if (AI.anyGeneratedIsShip(home, isV, shipSize, Main.playerPlacementBoard)) {
    //                 System.out.println(
    //                         "Your ship overlaps a previous placed ship. Please choose another point to place your ship.");
    //                 i--;
    //             } else {
    //                 Ship ship = new Ship(isV, shipSize, home);
    //                 System.out.println("You placed a: " + ship);
    //                 playerPlaceShip(home, shipSize, isV, ship);
    //                 playerMapOfCoor.replace(home.toString(), ship);

    //                 for (int j = 1; j < shipSize; j++) {
    //                     home.setIsShip(true);
    //                     Main.playerPlacementBoard[home.getY()][home.getX()].setIsShip(true);

    //                     // if the orientation is vertical
    //                     if (isV) {
    //                         Coordinate cur = Main.playerPlacementBoard[home.getY() + j][home.getX()];
    //                         playerMapOfCoor.replace(cur.toString(), ship);
    //                         cur.setIsShip(true);

    //                     } else {
    //                         Coordinate cur = Main.playerPlacementBoard[home.getY()][home.getX() + j];
    //                         playerMapOfCoor.replace(cur.toString(), ship);
    //                         cur.setIsShip(true);
    //                     }
    //                 }
    //             }
    //         }
    //         System.out.println("Your placement board:");
    //         printPlacementArray(Main.playerPlacementBoard);

                // if (playerAttackBoard[inputy][inputx].getIsHit()) {
            //     continue;
            // }
    //     }

    // }
    // public static void hitNextPoint(){
    //     Coordinate hit = hitPointQueue.get(0);
    //     int y = hit.getY(), x = hit.getX();
    //     Main.AIAttackBoard[y][x].setIsHit(true);
        
        
    //     System.out.printf("The AI hit coordinate %c%d\n", hit.columnIndex(y), x);
    //     System.out.println("Is it a hit or miss or sink?");
    //     getInput();
    // }

    // for (Coordinate i : hitPointQueue) {
    //     System.out.print(i.getProbability() + " ");
    // }
    // System.out.println();


            // for (int i = 1; i <= 10; i++) {
        //     for (int j = 1; j <= 10; j++) {
        //         coorBoard[i][j] = new Coordinate(j, i);
        //     }
        // }
            // public void occupyShip() {
    //     isShip = true;
    // }

    // public void hitShip() {
    //     isHit = true;
    // }
        
    // public static char columnIndex(int ind) {
    //     return (char) (ind + 'A' - 1);
    // }

    // public static void removeShipFromGrid(Ship shipHit) {
    // int y = shipHit.getHomeCoord().getY();
    // int x = shipHit.getHomeCoord().getX();
    // // Main.AIPlacementBoard[y][x];

    // }

    // if (!directionConfirmed) {
        //     sumArray(h, shipSize);
        //     Coordinate nextHit = max(h.getY(), h.getX(), shipSize);
        //     getInput(nextHit);
        // } else {
        //     Coordinate nextHit = max(h.getY(), h.getX(), shipSize);
        //     getInput(nextHit);
        // }
        // if (!directionConfirmed) {
        //     AI.resetArray();
        // }
        // hit max (set the coordinate as hit)
        // Main.AIAttackBoard[nextHit.getY()][nextHit.getX()].setIsHit(true);
        // ask user if this point is hit miss sunk
        // clear the arraylist and continue until sunk, if sunk set ishunting as false 

                // if (hitPointQueue.size() == shipSize - 1) {
            
        //     directionConfirmed = true;
        //     // only hit what is left in the hitpoint queue, don't call hunt again (dont sum the array)


        // }
        // for (Coordinate i : hitPointQueue) {
        //     System.out.print(i.getProbability() + " ");
        // }
        // System.out.println();


        //HUNT FILE BY JIAAN LI
        /*
         * 
         * 
         * import java.util.ArrayList;
         * import java.util.Scanner;
         * 
         * public class hunt extends AI{
         * private static Scanner sc = new Scanner(System.in);
         * 
         * private static int sum[][] = new int[11][11];
         * private static int max,timesMissed, counter = 0;
         * private static ArrayList<Coordinate> possibleHits = new
         * ArrayList<Coordinate>();
         * private static Coordinate hitPoints[]=new Coordinate[10];
         * private static int size;
         * // static Coordinate board[][];
         * private static Coordinate hitPoint;
         * private static boolean shipExists[]=new boolean[5];
         * private static int rangeL, rangeR, rangeU, rangeD=size-1;
         * private static char board[][]=new char[11][11];
         * private static boolean didHit;
         * private static boolean sunk=false;
         * static int timesHit=1;
         * public static void main(String args[]) { //testing stuff
         * Coordinate hitTest=new Coordinate(5,5); //CHANGE THIS TO FIND WHICH POINT YOU
         * WANT TO HIT
         * initBoard();
         * board[4][7]='S'; //CHANGE THIS TO SIMULATE ALREADY HIT OR MISSED POINTS
         * board[1][3]='S'; //CHANGE THIS TO SIMULATE ALREADY HIT OR MISSED POINTS
         * 
         * 
         * printBoard();
         * hunt1(hitTest, board, 5);
         * printArray();
         * }
         * 
         * //actual hunt code
         * public static void hunt1(Coordinate hitPoint1, char board [][],int size1) {
         * // System.out.println(hitPoints.size());
         * hitPoints[0]= hitPoint1;
         * size=size1;
         * initSum();
         * max=0;
         * possibleHits.clear();
         * hitPoint=hitPoint1;
         * // O - not hit or occupied
         * // X - occupied by a ship, hit
         * // M - not occupied by a ship, hit
         * // S - occupied by a ship, not hit
         * // Coordinate board[][]=board1.clone();
         * exist(); //sets all ships to exist
         * sumX(size);
         * sumY(size);
         * 
         * 
         * 
         * sum[hitPoint.getY()][hitPoint.getX()]=0; //makes the value of hit point=0 so
         * ai wont hit there again
         * for (int i = 1; i <= 10; i++) { //find the max number of the array
         * for (int j = 1; j <= 10; j++) {
         * max = Math.max(max, sum[i][j]);
         * }
         * }
         * for (int i = 1; i <= 10; i++) {
         * for (int j = 1; j <= 10; j++) {
         * if (sum[i][j] == max) {
         * possibleHits.add(new Coordinate(i, j));
         * }
         * }
         * }
         * int randIndex = (int) (Math.random() * possibleHits.size());
         * Coordinate hit = possibleHits.get(randIndex);
         * System.out.printf("The AI hit coordinate %c%d\n",
         * hit.columnIndex(hit.getY()), hit.getX()); //displaying hit place
         * printArray();
         * printBoard();
         * System.out.println("Did your ship get hit? Please enter yes or no");
         * String temp=sc.nextLine();
         * 
         * System.out.println(hit.getY()+"h"+hit.getX());
         * if (temp.equals("yes")) { //stores either hit or miss
         * didHit=true;
         * board[hit.getY()][hit.getX()]='X';
         * System.out.println(counter+"gay");
         * counter++;
         * hitPoints[counter]= hit;
         * hunt2(hitPoint,board, size);
         * }
         * else {
         * didHit=false;
         * board[hit.getY()][hit.getX()]='S';
         * hunt1(hitPoint,board, size);
         * }
         * 
         * //Below code should only be reached when ship is sunk
         * System.out.println("I sunk your "+timesHit+" unit long ship");
         * 
         * 
         * 
         * 
         * 
         * 
         * }
         * 
         * public static void hunt2(Coordinate hitPoint1, char board [][], int size1) {
         * initSum();
         * possibleHits.clear();
         * if(hitPoints[0].getX()==hitPoints[1].getX()) { //if ship horizontal
         * sumY(size-1);
         * System.out.println("^^ right after sum");
         * }
         * 
         * else if (hitPoints[0].getY()==hitPoints[1].getY());{
         * System.out.println(hitPoints[0].getY()+" hi "+hitPoints[1].getY());
         * sumX(size-1);
         * 
         * }
         * 
         * for (int i=0;i<counter;i++) {
         * sum[hitPoints[i].getY()][hitPoints[i].getX()]=0;
         * } //makes the value of hit point=0 so ai wont hit there again
         * printArray();
         * 
         * for (int i = 1; i <= 10; i++) { //find the max number of the array
         * for (int j = 1; j <= 10; j++) {
         * max = Math.max(max, sum[i][j]);
         * }
         * }
         * for (int i = 1; i <= 10; i++) {
         * for (int j = 1; j <= 10; j++) {
         * if (sum[i][j] == max) {
         * possibleHits.add(new Coordinate(i, j));
         * }
         * }
         * }
         * int randIndex = (int) (Math.random() * possibleHits.size());
         * Coordinate hit = possibleHits.get(randIndex);
         * System.out.println("The coordinate hit is "+hit.columnIndex(hit.getY())
         * +" "+hit.getX());
         * // System.out.printf("The AI hit coordinate %c%d\n",
         * hit.columnIndex(hit.getY()), hit.getX()); //displaying hit place
         * printArray();
         * System.out.println("Did your ship get hit? Please enter yes or no");
         * String temp=sc.nextLine();
         * if(temp.equals("yes")) {
         * timesHit++;
         * System.out.println("Did your ship get sunk? Please enter yes or no");
         * temp=sc.nextLine();
         * if (temp.equals("yes")) {
         * System.out.println("yay");
         * //do smtg to remove ship from future searches end program
         * }
         * else {
         * board[hit.getY()][hit.getX()]='X';
         * 
         * hunt2(hitPoint, board, size); //call program again
         * }
         * }
         * 
         * else {
         * board[hit.getY()][hit.getX()]='S';
         * 
         * }
         * 
         * 
         * 
         * 
         * }
         * 
         * 
         * 
         * public static void sumX(int shipSize) {
         * nearby(false, false); //initialize range values. find nearby occupied spots
         * nearby(false,true);
         * 
         * // System.out.println(rangeL+" "+rangeR);
         * // rangeR=0;
         * for(int j=hitPoint.getX()-rangeL;j<hitPoint.getX()+rangeR-size+2;j++) {
         * //runs for the number of open squares
         * for (int g=j;g<j+shipSize;g++) { //i have no clue what this does it was
         * copied from sarinas code and its supposed to sum stuff
         * if (g>10) {
         * break;
         * }
         * sum[hitPoint.getY()][g] +=1;
         * 
         * }
         * }
         * }
         * 
         * public static void sumY(int shipSize) {
         * nearby(true, false);
         * nearby(true,true);
         * 
         * for(int j=hitPoint.getY()-rangeU;j<(hitPoint.getY()+rangeD)-size+2;j++) {
         * //runs for the number of open squares
         * for (int g=j;g<j+shipSize;g++) {
         * if (g>10) {
         * break;
         * }
         * 
         * sum[g][hitPoint.getX()] +=1;
         * 
         * }
         * }
         * }
         * 
         * 
         * 
         * 
         * //below code could be shortened, I am just too lazy
         * public static void nearby(boolean isV, boolean positive) {
         * char boardValue;
         * int boardAscii;
         * int targetAscii=79;
         * int targetAscii2=88;
         * 
         * if (isV==false&&positive==true) { //any obstructions to the right
         * for (int i=1;i<size;i++) {
         * if (hitPoint.getX()+i>10) { //makes sure code doesnt crash
         * rangeR=i-1;
         * break;
         * }
         * boardValue=board[hitPoint.getY()][hitPoint.getX()+i];
         * boardAscii=boardValue;
         * if (targetAscii==boardAscii||targetAscii2==boardAscii) { //if not open //ADD
         * EDGE DETECTION AFOISHEFIUSHFIKJSEF
         * rangeR=size-1;
         * }
         * else {
         * rangeR=i-1;
         * break;
         * }
         * }
         * }
         * else if (isV==false&&positive==false) {
         * 
         * for (int i=1;i<size;i++) { //any obstructions to the left
         * if (hitPoint.getX()-i<1) {
         * rangeL=i-1;
         * break;
         * }
         * boardValue=board[hitPoint.getY()][hitPoint.getX()-i];
         * boardAscii=boardValue;
         * if (targetAscii==boardAscii||targetAscii2==boardAscii) { //if not open //ADD
         * EDGE DETECTION AFOISHEFIUSHFIKJSEF
         * rangeL=size-1;
         * }
         * else {
         * rangeL=i-1;
         * break;
         * 
         * }
         * }
         * }
         * else if (isV==true&&positive==true) {
         * 
         * for (int i=1;i<size;i++) { //any obstructions up
         * if (hitPoint.getY()-i<1) {
         * rangeU=i-1;
         * break;
         * }
         * boardValue=board[hitPoint.getY()-i][hitPoint.getX()];
         * boardAscii=boardValue;
         * if (targetAscii==boardAscii||targetAscii2==boardAscii) { //if not open //ADD
         * EDGE DETECTION AFOISHEFIUSHFIKJSEF
         * rangeU=size-1;
         * 
         * }
         * else {
         * rangeU=i-1;
         * break;
         * 
         * }
         * }
         * }
         * else if (isV==true&&positive==false) {
         * 
         * for (int i=1;i<size;i++) { //any obstructions down
         * if (hitPoint.getY()+i>10) {
         * rangeD=i-1;
         * break;
         * }
         * boardValue=board[hitPoint.getY()+i][hitPoint.getX()];
         * boardAscii=boardValue;
         * if (targetAscii==boardAscii||targetAscii2==boardAscii) { //if not open //ADD
         * EDGE DETECTION AFOISHEFIUSHFIKJSEF
         * rangeD=size-1;
         * 
         * }
         * else {
         * rangeD=i-1;
         * break;
         * 
         * }
         * }
         * }
         * }
         * 
         * public static void printArray() {
         * System.out.print("   ");
         * 
         * for (int i = 1; i < 11; i++) { // for the bar at the top
         * System.out.print(i + ("  "));
         * }
         * System.out.println();
         * System.out.println("_______________________________");
         * char c = 'a';
         * for (int i = 1; i <= 10; i++) {
         * 
         * System.out.print(c + " ");
         * c++;
         * for (int j = 1; j <= 10; j++) {
         * if (sum[i][j] < 10) {
         * System.out.print("0" + sum[i][j] + " ");
         * } else {
         * System.out.print(sum[i][j] + " ");
         * }
         * }
         * System.out.println();
         * }
         * }
         * 
         * 
         * public static void exist() {
         * for (int a=0;a<5;a++) {
         * shipExists[a]=true;
         * }
         * }
         * 
         * public static void initBoard() {
         * for (int i=0;i<11;i++) {
         * for (int j=0;j<11;j++) {
         * board[i][j]='O';
         * }
         * }
         * }
         * 
         * public static void initSum() {
         * for (int i=0;i<11;i++) {
         * for (int j=0;j<11;j++) {
         * sum[i][j]=0;
         * }
         * }
         * }
         * public static void copyArray() {
         * 
         * }
         * public static void printBoard() {
         * for (int i=1;i<11;i++) {
         * for(int j=1;j<11;j++) {
         * System.out.print(board[i][j]+" ");
         * }
         * System.out.println();
         * }
         * }
         * 
         * }
         * 
         */



    }
}
