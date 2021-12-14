
import java.util.ArrayList;
import java.util.Scanner;
public class playerStuff {
    static Scanner sc = new Scanner(System.in);
    static int x;
	static int y;
    static ArrayList<Coordinate> shipPositions = new ArrayList<Coordinate>();
    static int size; //size of ship
    static char ships[][]=new char[11][11];
    static Coordinate coorb[][]=new Coordinate[11][11];
    
	public static Coordinate getCoords() {
		//give vertical and horizontal coord and check if it overlaps
		boolean check=true;	
		for (int i=1;i<6;i++) {
			while (true) {
				check=true;
				//getting input values
		        x =getInputxs("Pick your x coordinate for ship"+(i)); //place in the order 2,3,4,5,3
		        y =getInputys("Pick your y coordinate for ship"+(i));
				boolean vertical;
				System.out.println("Please enter 1 for vertical, and anything else for horizontal");
				int temp=sc.nextInt();
				if (temp==1) {
					vertical=true;
				}
				else {
					vertical=false;
				}
				
				//assigning input values
				Coordinate coords=new Coordinate (y,x);	
				shipPositions.set(i-1, coords); //home coord value for ship number i
				if (i==1||i==2||i==3||i==4) {
					size=i+1;
					if (vertical==true) {
						for (int j=0;j<size;j++) {
							coorb[y+j][x].setIsShip(true); //if vertical extend size tiles
						}
					}
					if (vertical==false) {
						for (int j=0;j<size;j++) {
							coorb[y][x+j].setIsShip(true);
						}
					}
				}
				else if (i==5)  {  //for the last ship, since there are 2 ships with 3 tiles 
					size=3;
					if (vertical==true) {
						for (int j=0;j<size;j++) {
							coorb[y+j][x].setIsShip(true); //if vertical extend size tiles down
						}
					}
					if (vertical==false) {
						for (int j=0;j<size;j++) { 
							coorb[y][x+j].setIsShip(true); //if horizontal extend size tiles right
						}
					}
				}
				
				
				//check
				if (isOccupied(coords, vertical,size)) {
					check=true; //if overlapping, while loop keeps running
				}
				else {
					check=false;//if not, while loop ends and next iteration of for loop runs
				}
				
			}
		}
		return (shipPositions.get(1));
		}
	
	
	public static boolean inBounds(Coordinate homeCoord, boolean isV, int size ) {
		
		if (isV) {
			
		}
		
		return true;
	}
	
	public static boolean isOccupied(Coordinate homeCoord, boolean isV, int size ) {
		homeCoord.getX();
		for (int i=0;i<size;i++) { //loops for each stud of ship
			if(isV) {
				if (coorb[homeCoord.getY()+i][homeCoord.getX()].getIsShip()) { //if there is a ship on grid, 
					return true;
				}
				else {
					if (i==(size-1)) {
						return false; //only returns false if all places have been checked
					}
					
					
				}
			}
			else { //not vertical
				if (coorb[homeCoord.getY()][homeCoord.getX()+i].getIsShip()) { //if there is a ship on grid, 
					return true;
				}
				else {
					if (i==(size-1)) {
						return false; //only returns false if all places have been checked
					}
				}
			}
		}
		return false;
				
				
	}
	
	
	
	
	 public static int getInputxs(String output) {
	    	String input;
	    	int coord=-1;
	    	while (true) {  //keeps on running until user makes a valid input
		    	System.out.println(output);
		    	input=sc.nextLine();
		    	if(isInt(input)) {
		    		coord=Integer.parseInt(input);
		    		if (coord>=1 &&coord<=10) {
		    			return coord;
		    		}else {
		    			System.out.println("Please enter a valid input");
		    		}
		    		
		    	}
		    	else {
		    		System.out.println("Please enter a valid input");
		    	}
	    	}
	    }
	    	
	    public static int getInputys(String output) {
	    	String input;
	    	char c;
	    	int coord=-1;
	    	while (true) {  //keeps on running until user makes a valid input
		    	System.out.println(output);
		    	input=sc.nextLine();
		    	c=input.charAt(0);
		    	coord=(c-'a')+1;
		    	if (coord>=1 &&coord<=10) {
		    		return coord;
		   		}else {
		   			System.out.println("Please enter a valid input");
		   		}   		
		    }
		}
	    	
	    public static boolean isInt(String input) {
			try {Integer.parseInt(input); //checks if the user input is an int
		            return true; //If it is, return true
		        }
		        catch( Exception e ) {
		            return false;//else, return false
		        }
		}
	    
	
}
