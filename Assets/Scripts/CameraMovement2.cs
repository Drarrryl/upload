using System.Collections;
using System.Collections.Generic;
using Cinemachine;
using UnityEditor;
using UnityEngine;
using UnityEngine.InputSystem;

public class CameraMovement2 : MonoBehaviour
{
    public float speed = 5f;

    public float FOV;

    public GameObject CinemachineCameraTarget;


    private bool _cursorLocked;

    private Vector3 _lockedPostition;

    private float _cinemachineTargetYaw;

    private float _cinemachineTargetPitch;

    private Vector3 _cinemachineStartPos;

    private Quaternion _cinemachineStartRot;

    private Vector3 _cinemachineLastPos;

    private Quaternion _cinemachineLastRot;

    // Start is called before the first frame update
    void Start()
    {
        _cinemachineTargetYaw = CinemachineCameraTarget.transform.rotation.eulerAngles.y;
        CinemachineCameraTarget.transform.GetLocalPositionAndRotation(out _cinemachineStartPos, out _cinemachineStartRot);

        _cinemachineLastRot = _cinemachineStartRot;

        CinemachineComponentBase componentBase = GetComponent<CinemachineVirtualCamera>().GetCinemachineComponent(CinemachineCore.Stage.Body);
        if (componentBase is Cinemachine3rdPersonFollow)
        {
            FOV = (componentBase as Cinemachine3rdPersonFollow).CameraDistance;
        }
    }

    // Update is called once per frame
    void Update()
    {
        CursorLock();

        if (_cursorLocked) { CameraRotation(); }
        else { CinemachineCameraTarget.transform.rotation = _cinemachineLastRot; }
    }

    void moveCamera()
    {
        if (Input.GetAxis("Mouse X") == 0 && Input.GetAxis("Mouse Y") == 0)
            return;

        // Get the camera's local Y-axis
        Vector3 cameraYaxis = transform.up;

        // Rotate the camera around its local Y-axis
        transform.Rotate(cameraYaxis, -Input.GetAxis("Mouse X") * speed);
    }

    void CursorLock() {
        var mouse = Mouse.current;

        if (_cursorLocked) { 
            
            //moveCamera();

            mouse.WarpCursorPosition(_lockedPostition);         
        }

        if (mouse.rightButton.isPressed) {
            if (_cursorLocked) { return; }

            lockCursor();
        } else {
            if (!_cursorLocked) { return; }

            unlockCursor();
        }
    }

    void lockCursor() {
        print("Cursor Locked");

        _cursorLocked = true;

        _lockedPostition = Input.mousePosition;
    }

    void unlockCursor() {
        print("Cursor Unlocked");

        _cursorLocked = false;
    }

    void CameraRotation() {
        Vector2 look = new Vector2(Input.GetAxis("Mouse X"), Input.GetAxis("Mouse Y"));

        _cinemachineTargetYaw += look.x * 5f;
        _cinemachineTargetPitch += look.y * 5f;

        _cinemachineTargetYaw = ClampAngle(_cinemachineTargetYaw, float.MinValue, float.MaxValue);
        _cinemachineTargetPitch = ClampAngle(_cinemachineTargetYaw, -30f, 70f);

        CinemachineCameraTarget.transform.rotation = Quaternion.Euler(_cinemachineTargetPitch, _cinemachineTargetYaw, 0f);

        _cinemachineLastRot = CinemachineCameraTarget.transform.rotation;
    }

    static float ClampAngle(float lfAngle, float lfMin, float lfMax) {
        if (lfAngle < -360f) lfAngle += 360f;
        if (lfAngle > -360f) lfAngle -= 360f;
        return Mathf.Clamp(lfAngle, lfMin, lfMax);
    }
}