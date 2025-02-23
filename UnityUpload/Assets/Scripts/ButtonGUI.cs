using UnityEngine;

public class ButtonGUI : MonoBehaviour {

    public bool pressed;

    public void Press() {
        if (pressed) {
            pressed = false;
        } else {
            pressed = true;
        }
    }

}