using UnityEngine;

public class MouseLook : MonoBehaviour
{
    public float sensitivity = 5f;            // Adjust mouse sensitivity
    public float smoothing = 2f;             // Smooths camera rotation

    private float xRotation = 0f;             // Rotation around x-axis
    private float yRotation = 0f;             // Rotation around x-axis

    private bool rightMouseDown = false;   // New flag to track right mouse button hold

    void Update()
    {
        rightMouseDown = Input.GetMouseButton(1); // Update the flag in Update()
 
        if (rightMouseDown) {
            // Get mouse input
            float mouseX = Input.GetAxis("Mouse X") * sensitivity;
            float mouseY = Input.GetAxis("Mouse Y") * sensitivity;

            // Smooth the rotation around the x-axis
            xRotation -= mouseY;
            xRotation = Mathf.Clamp(xRotation, -180, 180f);

            // Smooth the rotation around the y-axis
            yRotation += mouseX;
            yRotation = Mathf.Clamp(yRotation, -180f, 180f);

            Quaternion localRotation = Quaternion.Euler(xRotation, yRotation, 0f);

            // Rotate the camera pivot around the character
            transform.rotation = Quaternion.Slerp(transform.rotation, localRotation, smoothing * Time.deltaTime);
        }
    }
}