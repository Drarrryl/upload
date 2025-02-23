using UnityEngine;

public class RobloxCameraControl : MonoBehaviour
{
    public float sensitivity = 5f;         // Adjust mouse sensitivity
    public float smoothing = 2f;            // Smooths camera rotation
    private float xRotation = 0f;            // Rotation around x-axis
    private Vector3 lastMousePosition;      // To track mouse movement
    private bool isCameraRotating = false;  // Flag to indicate camera rotation


    void Update()
    {
        if (Input.GetMouseButtonDown(1))
        {
            isCameraRotating = true;
            Cursor.lockState = CursorLockMode.Locked;
            lastMousePosition = Input.mousePosition;
        }

        if (Input.GetMouseButtonUp(1))
        {
            isCameraRotating = false;
            Cursor.lockState = CursorLockMode.None;
        }

        if (isCameraRotating)
        {
            // Get mouse delta since last frame
            float mouseX = Input.mousePosition.x - lastMousePosition.x;
            float mouseY = Input.mousePosition.y - lastMousePosition.y;

            // Smooth the rotation around the x-axis
            xRotation -= mouseY * sensitivity;
            xRotation = Mathf.Clamp(xRotation, -90f, 90f);

            Quaternion localRotation = Quaternion.Euler(xRotation, 0f, 0f);

            // Rotate the camera pivot
            transform.rotation = Quaternion.Slerp(transform.rotation, localRotation, smoothing * Time.deltaTime);

            // Update lastMousePosition for next frame's calculation
            lastMousePosition = Input.mousePosition;
        }
    }
}