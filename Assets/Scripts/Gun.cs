using System.Collections;
using UnityEngine;

public class Gun : MonoBehaviour {

    public GameObject bulletPrefab;
    public Transform barrel;
    public GameObject crosshair;
    public float gunSpeed = 50f;

    private bool onCoolDown;

    void Start() {

    }

    void Update() {

    }

    public void Shoot() {
        if (onCoolDown) return;

        print("Gun Shot");
        
        if (crosshair == null) return;
        onCoolDown = true;

        // Instantiate a bullet and set its direction towards the crosshair
        GameObject bullet = Instantiate(bulletPrefab, barrel.position, Quaternion.identity);
        bullet.GetComponent<BulletController>().ChangeSpeed(gunSpeed);
        bullet.GetComponent<BulletController>().targetPos = crosshair.transform.position;

        StartCoroutine(Cooldown(0.5f));
    }

    public IEnumerator Cooldown(float seconds) {
        yield return new WaitForSeconds(seconds);

        onCoolDown = false;
    }
}