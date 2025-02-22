using System;
using System.Collections;
using System.Collections.Generic;
using UnityEditor.Experimental.GraphView;
using UnityEngine;

public class SpawnController : MonoBehaviour
{

    public GameObject arrowPrefab;

    public int offset = 3;


    private List<GameObject> listOfArrows;

    private GameObject arrowBundle;


    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnCollisionEnter(UnityEngine.Collision collision)
    {
        if (collision.gameObject.tag == "Player") {
            print("Spawning Arrows");

            spawnArrows(collision.gameObject);
        }
    }

    private void OnCollisionExit(UnityEngine.Collision collision) {
        if (arrowBundle == null) { return; }
        Destroy(arrowBundle);
    }

    private void spawnArrows(GameObject player) {
        arrowBundle = new GameObject("Arrows");
        arrowBundle.transform.parent = transform;

        foreach (Transform spawnPoint in transform.parent.parent.GetComponentInChildren<Transform>())
         {
            print(spawnPoint.name);
            Transform spawn = spawnPoint.Find("Spawn");

            if (spawnPoint.name != transform.parent.name) {
                List<System.Object> properties = CalcPosition(transform, spawn);

                Vector3 finalPosition;

                if (!checkDuplicatePos(arrowBundle.transform, (Vector3) properties[0])) { 
                    finalPosition = (Vector3) properties[0]; 
                }
                else { finalPosition = (Vector3) properties[0] + (Vector3) properties[2]*2; }

                Quaternion finalRotation = (Quaternion) properties[1];

                print("Creating Arrow at " + finalPosition);

                GameObject newArrow = Instantiate(arrowPrefab, finalPosition, finalRotation);

                newArrow.GetComponent<ArrowController>().target = player;
                newArrow.GetComponent<ArrowController>().pointingTo = spawn.gameObject;

                newArrow.transform.parent = arrowBundle.transform;
            }
        }
    }

    private List<System.Object> CalcPosition(Transform target, Transform hit) {
        Vector3 direction = hit.position - target.position;

        direction.Normalize();

        Vector3 finalPosition = transform.position + (direction*offset);

        Quaternion finalRotation = Quaternion.LookRotation(direction);

        return new List<System.Object> { finalPosition, finalRotation, direction };
    }

    private bool checkDuplicatePos(Transform arrows, Vector3 pos) {
        foreach (Transform arrow in arrows) {
            if (arrow.transform.position == pos) {
                return true;
            }
        }
        return false;
    }

}