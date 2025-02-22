using UnityEngine;

public class PlayerRotationController2 : MonoBehaviour
{
    public float rotationSpeed = 100f;
    private float rotationDirection = 0f;

    void Update()
    {
        // Rotate the player based on direction
        if (rotationDirection != 0f)
        {
            transform.Rotate(Vector3.forward * rotationDirection * rotationSpeed * Time.deltaTime);
        }
    }

    public void RotateLeft(bool isPressed)
    {
        rotationDirection = isPressed ? 1f : 0f;  // Rotate counter-clockwise
    }

    public void RotateRight(bool isPressed)
    {
        rotationDirection = isPressed ? -1f : 0f; // Rotate clockwise
    }
}