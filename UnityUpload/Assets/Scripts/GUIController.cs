using Microsoft.Unity.VisualStudio.Editor;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.InputSystem;
using UnityEngine.UI;

public class GUIController : MonoBehaviour {
    
    public void ButtonClick(GameObject button) {
        if (button.GetComponent<UnityEngine.UI.Image>().color == Color.white) {
            button.GetComponent<UnityEngine.UI.Image>().color = Color.yellow;
        } else {
            button.GetComponent<UnityEngine.UI.Image>().color = Color.white;
        }
    }
}