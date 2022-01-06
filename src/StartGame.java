
//Vivien Cai, Sarina Li, Jiaan Li
//January 6, 2020
//Mr. Anandarajan
//Battleship Project



public class StartGame {
	public static void main(String[] args) throws Exception {

		Main.initArrays();
		Game.initHitPoint();
		// Placing.place(Main.AIPlacementBoard);							//moved this somewhere else cause it kept on bugging my file handling
	//	Game.printPlacementArray(Main.AIPlacementBoard);
		GUI.setUpWindow();
	}
}
