using UnityEngine;

public class CharacterMovement : MonoBehaviour
{
    public float movementSpeed = 5f;
    public float jumpForce = 500f;
    public float turnSmoothTime = 0.1f;      // New variable for camera smoothing
    public Transform cameraHolder;           // Reference to the Camera Holder
    private CharacterController controller;
    private Vector3 moveDirection = Vector3.zero;
    private Quaternion targetRotation;

    private void Start()
    {
        controller = GetComponent<CharacterController>();
    }

    private void Update()
    {
        // Walking
        float horizontalInput = Input.GetAxis("Horizontal");
        float verticalInput = Input.GetAxis("Vertical");

        // Get the desired rotation based on input 
        targetRotation = Quaternion.LookRotation(new Vector3(horizontalInput, 0f, verticalInput));

        // Rotate the character only (not the transform)
        transform.rotation = Quaternion.Slerp(transform.rotation, targetRotation, Time.deltaTime * 5f); 

        moveDirection = new Vector3(horizontalInput, 0f, verticalInput);
        moveDirection = transform.TransformDirection(moveDirection);
        moveDirection *= movementSpeed;

        if (controller.isGrounded)
        {
            // Jumping
            if (Input.GetKeyDown(KeyCode.Space))
            {
                moveDirection.y = jumpForce;
                print("Jump");
            }
            controller.transform.rotation = Quaternion.Slerp(controller.transform.rotation, targetRotation, Time.deltaTime * 5f);  
            controller.Move(moveDirection * Time.deltaTime);
            print("Grounded");
        }
        else 
        {
            Vector3 gravity = new Vector3(0, -9.81f, 0);
            controller.Move(moveDirection * Time.deltaTime + gravity * Time.deltaTime);
            print("In Air");
        }
        cameraHolder.transform.position = transform.position;
    }
}