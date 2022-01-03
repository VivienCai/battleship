
public class LaunchGame {
	public static void main(String[] args) throws Exception {
		Main.initArrays();
		Placing.place(Main.AIPlacementBoard);
		GUI.setUpWindow();
	}
}
