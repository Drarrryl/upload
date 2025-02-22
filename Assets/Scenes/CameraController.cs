using UnityEngine;

public class CameraController : MonoBehaviour
{
    public float smoothSpeed = 5f;         // Smoothing speed for camera movement

    private Quaternion defaultRotation;  // Store the default camera rotation

    private void Start()
    {
        // Set the default rotation to the camera's current rotation
        defaultRotation = transform.rotation;
    }

    private void Update()
    {
        // When the right mouse button is not down, 
        if (!Input.GetMouseButton(1))
        {
            // Smoothly rotate the camera back to its default rotation
            transform.rotation = Quaternion.Slerp(transform.rotation, defaultRotation, smoothSpeed * Time.deltaTime);
        }
    }
}
