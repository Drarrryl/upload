package interface_adapter.Game;

import entity.Game.Collidable;
import entity.Game.GameObject;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class Physics {
    public static GameObject Collision(GameObject gameObject, ArrayList<Collidable> targets) {

        for (int i = 0; i < targets.size(); i++) {

            if (gameObject.getBounds().intersects(targets.get(i).getBounds())) {
                return (GameObject) targets.get(i);
            }

        }

        return null;
    }

    public static ArrayList<GameObject> Collisions(GameObject gameObject, ArrayList<Collidable> targets) {
        ArrayList<GameObject> result = new ArrayList<>();

        for (int i = 0; i < targets.size(); i++) {

            if (gameObject.getBounds().intersects(targets.get(i).getBounds())) {
                result.add((GameObject) targets.get(i));
            }

        }

        return result;
    }
}
