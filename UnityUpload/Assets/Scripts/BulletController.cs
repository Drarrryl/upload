using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using Unity.VisualScripting;
using UnityEngine;

public class BulletController : MonoBehaviour
{
    public float bulletSpeed = 10f; // How fast the bullet moves

    public int damage = 10; // The amount of damage the bullet deals

    public Vector3 targetPos;

    private void Update()
    {
        // Move the bullet forward
        if (targetPos != null) {
            float distance = Vector3.Distance(targetPos, transform.position);
            if (distance <= 0.01f) { print("Destroy Bullet"); Destroy(gameObject); return; }

            Vector3 direction = targetPos - transform.position;
            direction.Normalize();
            transform.Translate(direction * bulletSpeed * Time.deltaTime);
        }

        // Destroy the bullet after a certain time or when it hits something
        // You can add additional collision detection here if needed
        Destroy(gameObject, 3f);
    }

    public void OnCollisionEnter(UnityEngine.Collision collision)
    {
        print("Bullet Hit!");

        if (collision.gameObject.tag == "Player") {
            collision.gameObject.GetComponent<ToLives>().TakeDamage();
        }

        if (!collision.gameObject.CompareTag("Item")) Destroy(gameObject);
    }

    public void ChangeSpeed(float newSpeed) {
        bulletSpeed = newSpeed;
    }
}
