using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ToLives : MonoBehaviour
{

    public Lives script;

    public void TakeDamage() {
        script.RemoveAHeart();
    }
}
