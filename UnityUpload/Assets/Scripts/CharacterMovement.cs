using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CharacterMovement : MonoBehaviour
{

    public float speed = 5f;

    public float jumpSpeed = 5f;

    public float gravity = 5f;


    private CharacterController _controller;

    private Animator _animator;

    private Camera _mainCamera;

    private int _speedAnimID;
    private int _inAirAnimID;

    private float _currentRotVel;

    private Vector3 targetDir = new Vector3(0, 0, 0);

    // Start is called before the first frame update
    void Start()
    {
        _controller = GetComponent<CharacterController>();
        _animator = GetComponent<Animator>();
        //_mainCamera = transform.Find("Camera Target").Find("Main Camera").GetComponent<Camera>();
        _mainCamera = transform.Find("PlayerCameraFollow").Find("Main Camera").GetComponent<Camera>();

        SetAnims();
    }

    // Update is called once per frame
    void Update()
    {
        Move();
        Gravity();
        Jump(); 
    }

    void SetAnims() {
        _speedAnimID = Animator.StringToHash("Speed");
        _inAirAnimID = Animator.StringToHash("InAir");
    }

    void Move() {
        var moveX = Input.GetAxis("Horizontal");
        var moveY = Input.GetAxis("Vertical");

        Vector3 inputDirection = new Vector3(moveX, 0f, moveY);

        if (moveX == 0f && moveY == 0f) {
            _animator.SetFloat(_speedAnimID, _controller.velocity.magnitude);
            _controller.Move(new Vector3(0, 0, 0));
            return; 
        }

        float _targetRotation = Mathf.Atan2(inputDirection.x, inputDirection.z) * Mathf.Rad2Deg +
                                _mainCamera.transform.eulerAngles.y;

        float rotation = Mathf.SmoothDampAngle(transform.eulerAngles.y, _targetRotation, ref _currentRotVel,
            0.12f);

        // rotate to face input direction relative to camera position
        transform.rotation = Quaternion.Euler(0.0f, rotation, 0.0f);
        Physics.SyncTransforms();

        Vector3 targetDirection = Quaternion.Euler(0.0f, _targetRotation, 0.0f) * Vector3.forward;

        targetDir = targetDirection;
        
        _controller.Move(targetDirection.normalized*speed*Time.deltaTime);

        _animator.SetFloat(_speedAnimID, _controller.velocity.magnitude);
    }

    void Gravity() {
        if (!_controller.isGrounded) {
            _controller.Move(new Vector3(0f, -gravity, 0f)*Time.deltaTime);
            _animator.SetBool(_inAirAnimID, true);
        } else {
            _animator.SetBool(_inAirAnimID, false);
        }
    }

    void Jump() {
        if (Input.GetKeyDown(KeyCode.Space)) {
            _controller.Move(new Vector3(0f, jumpSpeed, 0f));
        }
    }
}
