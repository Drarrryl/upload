using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.InputSystem;

public class CameraMovement : MonoBehaviour
{
    public float speed = 1f;             // Camera movement speed
    public float sensitivity = 0.5f;        // Camera rotation sensitivity
    public float maxPitch = 70f;          // Maximum vertical rotation angle
    public float minPitch = -30f;         // Minimum vertical rotation angle
    public ButtonGUI cameraControlButton; // Reference to the camera control button

    private Transform _mainCamera;
    private bool cursorLocked = false;   // To keep track of cursor lock state
    private Vector3 lastCursorPosition;  // To store the last cursor position when locking
    private float cameraYaw;             // Yaw rotation of the camera
    private float cameraPitch;            // Pitch rotation of the camera
    private Quaternion lastCameraRot;     // The last rotation of the camera
    private float FOV;

    void Start() {
        _mainCamera = transform.Find("Main Camera");

        lastCameraRot = transform.rotation;

        FOV = _mainCamera.position.z;
    }

    // Update is called once per frame
    void Update()
    {
        CursorLock();
        HandleCameraMovement();
        FixRotation();
        FixDistance();
    }

    void FixRotation() {
        if (!cursorLocked) { transform.rotation = lastCameraRot; }
    }

    void FixDistance()
    {
        FOV = _mainCamera.localPosition.z;

        // Change the distance based on scroll input
        float zoomDelta = Mouse.current.scroll.y.ReadValue() * speed * Time.deltaTime;
        if (zoomDelta == 0) { return; }
        FOV = Mathf.Clamp(FOV + zoomDelta, -15f, 0f); // You can adjust the minimum and maximum FOV values as needed

        // Update camera position based on new FOV
        Vector3 newPosition = _mainCamera.localPosition;
        newPosition.z = FOV;
        _mainCamera.localPosition = newPosition;
    }

    void HandleCameraMovement()
    {
        if (Mouse.current.rightButton.isPressed && checkControl())
        {
            // Get mouse delta
            Vector2 mouseDelta = Mouse.current.delta.ReadValue();

            // Modify cameraYaw and cameraPitch based on mouseDelta
            cameraYaw += mouseDelta.x * sensitivity;
            cameraPitch -= mouseDelta.y * sensitivity;

            // Clamp cameraPitch to avoid gimbal lock
            cameraPitch = Mathf.Clamp(cameraPitch, minPitch, maxPitch);

            // Create a new rotation Quaternion from cameraYaw and cameraPitch
            Quaternion cameraRotation = Quaternion.Euler(cameraPitch, cameraYaw, 0f);

            // Rotate the camera transform by the calculated Quaternion
            transform.rotation = cameraRotation;

            lastCameraRot = transform.rotation;
        }

        //FOV += Mouse.current.scroll.EvaluateMagnitude();
    }

    void CursorLock()
    {
        if (Mouse.current.rightButton.isPressed && checkControl())
        {
            if (!cursorLocked)
            {
                // Lock the cursor and store the initial cursor position
                Cursor.lockState = CursorLockMode.Locked;
                cursorLocked = true;
                lastCursorPosition = Mouse.current.position.ReadValue();
            }
        }
        else
        {
            if (cursorLocked)
            {
                // Unlock the cursor
                Cursor.lockState = CursorLockMode.None;
                cursorLocked = false;
            }
        }
    }

    public bool checkControl() {
        if (cameraControlButton != null && cameraControlButton.pressed) {
            return true;
        } else {
            return false;
        }
    }
      
}