
public class LaunchGame {
	public static void main(String[] args) throws Exception {
		Main.initArrays();
		Hunting.initHitPoint();
		Placing.place(Main.AIPlacementBoard);
		GUI.setUpWindow();
	}
}
