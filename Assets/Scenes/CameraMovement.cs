using UnityEngine;

public class CameraMovement : MonoBehaviour
{
    public float movementSpeed = 5f;
    public float jumpForce = 500f;
    private CharacterController controller;
    private Vector3 moveDirection = Vector3.zero;

    private void Start()
    {
        controller = GetComponent<CharacterController>();
    }

    private void Update()
    {
        // Walking
        float horizontalInput = Input.GetAxis("Horizontal");
        float verticalInput = Input.GetAxis("Vertical");

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
            
            controller.Move(moveDirection * Time.deltaTime);
            print("Grounded");
        }
        else 
        {
            Vector3 gravity = new Vector3(0, -9.81f, 0);
            controller.Move(moveDirection * Time.deltaTime + gravity * Time.deltaTime);
            print("In Air");
        }
    }
}