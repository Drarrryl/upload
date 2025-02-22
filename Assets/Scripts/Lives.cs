using System.Collections;
using System.Collections.Generic;
using Microsoft.Unity.VisualStudio.Editor;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.UIElements;

public class Lives : MonoBehaviour
{

    public int lives;

    public GameObject content;

    public Sprite aliveImage;
    
    public Sprite deadImage;

    // Update is called once per frame
    void Update()
    {
        if (content != null) {
            CheckLives();
        }
    }

    void CheckLives() {
        lives = 0;
        foreach (UnityEngine.UI.Image child in content.GetComponentsInChildren<UnityEngine.UI.Image>()) {
            if (child.sprite == aliveImage) {
                lives += 1;
            }
        } 
    }

    public void RemoveAHeart() {
        if (lives > 0) { 
            content.transform.Find(lives.ToString()).GetComponent<UnityEngine.UI.Image>().sprite = deadImage;
            lives -= 1;

            print("Removed a heart");
        }
    }
}
