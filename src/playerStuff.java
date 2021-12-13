
import java.util.ArrayList;
import java.util.Scanner;
public class playerStuff {
    static Scanner sc = new Scanner(System.in);
    static int x;
	static int y;
    static ArrayList<Coordinate> shipPositions = new ArrayList<Coordinate>();
    static int size; //size of ship
	public static Coordinate getCoords() {
		//give vertical and horizontal coord and check if it overlaps
	boolean check=true;	
	for (int i=1;i<6;i++) {
		while (true) {
			check=true;
			//getting input values
	        x =getInputx("Pick your x coordinate for ship"+(i));
	        y =getInputy("Pick your y coordinate for ship"+(i));
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
			shipPositions.set(i, coords);
			
			
			//checking input values
			if (i==1||i==4||i==5) {
				size=i+1;
				if (vertical==true) {
					for (int j=0;j<size;j++) {
						shipPositions.get(i).setIsShip(true);
					}
				}
			}
			
			
		
		
		}

	
    }
	
   
	
	return (Coordinate.x_coor==x);
	}
	
	
	
	
	
	
	
	 public static int getInputx(String output) {
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
	    	
	    public static int getInputy(String output) {
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
