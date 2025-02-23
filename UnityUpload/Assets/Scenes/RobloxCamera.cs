using UnityEngine;

public class RobloxCamera : MonoBehaviour
{
    public Transform target; // Drag and drop the character's transform in the Inspector
    public float smoothSpeed = 0.1f;      
    private Vector3 offset; 

    private void Start()
    {
        // Calculate the initial offset between the camera and the character position
        offset = transform.position - target.position;
    }

    private void FixedUpdate()
    {
        // Follow the target's position with a smooth offset
        Vector3 targetCamPos = target.position + offset;
        transform.position = Vector3.Slerp(transform.position, targetCamPos, smoothSpeed);
    }
}