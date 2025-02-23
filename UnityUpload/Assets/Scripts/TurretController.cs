using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TurretController : MonoBehaviour
{

    public GameObject target; // The player's game object

    public float rotationSpeed = 5f; // How fast the turret rotates

    public float maximumRange = 10f; // The range at which the turret starts rotating

    public GameObject bulletPrefab; // The bullet prefab

    public Transform barrel;

    void Start() {
        target = target.transform.Find("Target").gameObject;
    }

    private void Update()
    {
        // Check if the player is within the maximum range
        if (Vector3.Distance(transform.position, target.transform.position) <= maximumRange)
        {
            // Calculate the direction vector from the turret to the player
            Vector3 direction = target.transform.position - transform.position;

            //direction = Quaternion.Euler(0f, 90f, 0f) * direction;

            // Rotate the turret towards the adjusted direction
            transform.rotation = Quaternion.Slerp(transform.rotation, Quaternion.LookRotation(direction), rotationSpeed * Time.deltaTime);

            // Shoot the bullet if the player is in range
            if (Input.GetKeyDown(KeyCode.F))
            {
                Shoot();
            }
        }
    }

    public void Shoot()
    {
        // Instantiate a bullet and set its direction towards the player
        GameObject bullet = Instantiate(bulletPrefab, barrel.position, Quaternion.identity);
        bullet.GetComponent<BulletController>().targetPos = target.transform.position;
    }
}
