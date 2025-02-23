using UnityEngine;

public class TurretController2 : MonoBehaviour
{
    public float rotationSpeed = 5f;           // Speed of turret rotation
    public float lockOnRange = 10f;             // Distance to lock onto the player
    public float bulletSpeed = 20f;            // Speed of the bullets
    public GameObject bulletPrefab;           // Prefab of the bullet capsule
    public Transform barrelTransform;         // Reference to the barrel transform

    private GameObject player;                // Reference to the player object
    private float timeSinceLastShot = 0f;      // Timer for controlling bullet shooting rate
    private const float shootInterval = 0.5f;  // Time between each bullet shot

    void Start()
    {
        // Find the player in the scene and store its reference
        player = GameObject.FindGameObjectWithTag("Player");
    }

    void Update()
    {
        if (player != null)
        {
            // Rotate the turret to face the player
            RotateTurret();

            // Lock onto the player within the lockOnRange
            if (Vector3.Distance(transform.position, player.transform.position) <= lockOnRange)
            {
                ShootBullet();
            }
        }
    }

    private void RotateTurret()
    {
        // Calculate the direction to the player
        Vector3 targetDirection = player.transform.position - transform.position;

        // Get the rotation needed to look at the player
        Quaternion targetRotation = Quaternion.LookRotation(new Vector3(targetDirection.x, 0f, targetDirection.z));

        // Rotate the turret head gradually towards the target rotation
        transform.rotation = Quaternion.Slerp(transform.rotation, targetRotation, rotationSpeed * Time.deltaTime);
    }

    private void ShootBullet()
    {
        // Check if it's time to shoot again
        if (timeSinceLastShot >= shootInterval)
        {
            timeSinceLastShot = 0f;

            // Create a new bullet instance from the prefab
            GameObject bullet = Instantiate(bulletPrefab, barrelTransform.position, barrelTransform.rotation);

            // Set the bullet's velocity towards the player
            bullet.GetComponent<Rigidbody>().velocity = barrelTransform.forward * bulletSpeed;
        }

        timeSinceLastShot += Time.deltaTime;
    }
}  