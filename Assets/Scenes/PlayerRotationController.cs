using UnityEngine;
using UnityEngine.UI;

public class PlayerRotationController : MonoBehaviour
{
    public float rotationSpeed = 100f; // Rotation speed in degrees per second
    private bool rotateLeft = false;
    private bool rotateRight = false;

    void Update()
    {
        if (rotateLeft)
        {
            RotateLeft();
        }
        else if (rotateRight)
        {
            RotateRight();
        }
    }

    public void OnRotateLeftButtonDown()
    {
        print("WTF?");
        rotateLeft = true;
    }

    public void OnRotateLeftButtonUp()
    {
        print("Lol1");
        rotateLeft = false;
    }

    public void OnRotateRightButtonDown()
    {
        rotateRight = true;
    }

    public void OnRotateRightButtonUp()
    {
        rotateRight = false;
    }

    private void RotateLeft()
    {
        transform.Rotate(Vector3.forward * rotationSpeed * Time.deltaTime);
    }

    private void RotateRight()
    {
        transform.Rotate(-Vector3.forward * rotationSpeed * Time.deltaTime);
    }
}