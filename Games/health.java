//class to model player's/opponent's health

import javafx.scene.Node;

public class health {

	private int health;

	private Node node;

	public health(Node node, int health){
		this.node = node;
		this.health = health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

}//end class health
