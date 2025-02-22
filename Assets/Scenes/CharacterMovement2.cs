using UnityEngine;

public class CharacterMovement2 : MonoBehaviour
{
    public float movementSpeed = 5f;
    public float jumpForce = 500f;
    public float turnSmoothTime = 0.1f;    // New variable for camera smoothing
    private CharacterController controller;
    private Vector3 moveDirection = Vector3.zero;
    private Quaternion targetRotation; 
    private float turnSmoothVelocity;   // New variable for camera smoothing

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
        transform.rotation = Quaternion.Slerp(transform.rotation, targetRotation, turnSmoothTime * Time.deltaTime); 

        moveDirection = new Vector3(horizontalInput, 0f, verticalInput);
        moveDirection = transform.TransformDirection(moveDirection);
        moveDirection *= movementSpeed;

       if (controller.isGrounded)
        {
            // Jumping 
            if (Input.GetKeyDown(KeyCode.Space))
            {
                moveDirection.y = jumpForce;
            } 
            controller.Move(moveDirection * Time.deltaTime);      
        }
        else 
        { 
            moveDirection.y -= 9.81f * Time.deltaTime;    
            controller.Move(moveDirection * Time.deltaTime);
        }
    }
}