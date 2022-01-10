/* Sarina Li, Vivien Cai, Jiaan Li
* Sun January 09
* ICS4U1
* StartGame File 
* ROOT OF OUR APPLICATION
*/

// root of our java project, run the GUI based game here. 
public class StartGame {
	public static void main(String[] args) throws Exception {
		Main.initArrays();
		Game.initHitPoint();
		GUI.setUpWindow();
	}
}
