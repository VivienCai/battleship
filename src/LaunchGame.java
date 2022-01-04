
public class LaunchGame {
	public static void main(String[] args) throws Exception {

		Main.initArrays();
		Game.initHitPoint();
		Placing.place(Main.AIPlacementBoard);
		Game.printPlacementArray(Main.AIPlacementBoard);
		GUI.setUpWindow();
	}
}
