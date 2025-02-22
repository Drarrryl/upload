using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.InputSystem;

[RequireComponent(typeof(Animator))]

public class Arms : MonoBehaviour
{
    protected Animator animator;

    public bool ikActive = false;

    public GameObject lookObj;

    public GameObject lookObjPrefab;

    public InventoryManager inventoryManager;

    public GameObject crosshairPrefab;

    private float _lookWeight;

    private bool left;
    private bool right;

    private bool equippedRanged;

    private float distance;

    public Transform primaryHand;
    // Start is called before the first frame update
    void Start()
    {
        animator = GetComponent<Animator>();

        distance = 50f;
    }

    // Update is called once per frame
    void Update()
    {
        CheckEquipped();

        if (equippedRanged) {
            distance = 10f;
            left = false;
            right = true;
            UpdateLookObj();
            ikActive = true;
            FixCrosshair();
        } else {
            float leftVal = Mouse.current.leftButton.ReadValue();
            float rightVal = Mouse.current.rightButton.ReadValue();

            if (leftVal != 0) left = true;
            else left = false;

            if (rightVal != 0) right = true;
            else right = false;

            if (leftVal != 0 || rightVal != 0) {
                UpdateLookObj();
                ikActive = true;
            } else {
                ikActive = false;
            }
        }
    }

    void UpdateLookObj() {
        // Raycasting from camera to determine plane hit position
        Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
        RaycastHit hit;
        GameObject hitObj = null;
        int layerMask = 1 << 6;

        layerMask = ~layerMask;

        bool contact = Physics.Raycast(ray, out hit, distance, layerMask);

        if (contact) hitObj = hit.transform.gameObject;

        Vector3 displacement = Camera.main.transform.position - transform.position;

        Vector3 newPos = transform.position + displacement + (ray.direction.normalized * distance);

        if (lookObj != null && lookObj.transform.position == newPos) { return; }
        if (contact && hit.distance <= distance && !hitObj.CompareTag("Player") && !hitObj.CompareTag("Item")) {
            if (hitObj.tag == "Temp") { return; }
            else {
                Destroy(lookObj);

                newPos = hit.point;

                if (equippedRanged) lookObj = Instantiate(crosshairPrefab, newPos, Quaternion.identity);
                else lookObj = Instantiate(lookObjPrefab, newPos, Quaternion.identity);
                lookObj.tag = "Temp";

                return;
            }
        }

        Destroy(lookObj);

        if (equippedRanged) { lookObj = Instantiate(crosshairPrefab, newPos, Quaternion.identity);}
        else lookObj = Instantiate(lookObjPrefab, newPos, Quaternion.identity);
        lookObj.tag = "Temp";
    }

    //a callback for calculating IK
    void OnAnimatorIK()
    {
        if(animator) {

            //if the IK is active, set the position and rotation directly to the goal.
            if(ikActive) {

                // Set the look target position, if one has been assigned
                if(lookObj != null) {
                    animator.SetLookAtWeight(_lookWeight);
                    animator.SetLookAtPosition(lookObj.transform.position);
                }    

                // Set the right hand target position and rotation, if one has been assigned
                if(primaryHand != null) {
                    if (left) {
                        animator.SetIKPositionWeight(AvatarIKGoal.LeftHand,1);
                        animator.SetIKRotationWeight(AvatarIKGoal.LeftHand,1);
                        animator.SetIKPosition(AvatarIKGoal.LeftHand,lookObj.transform.position);
                        animator.SetIKRotation(AvatarIKGoal.LeftHand,lookObj.transform.rotation);
                    }
                    if (right) {
                        animator.SetIKPositionWeight(AvatarIKGoal.RightHand,1);
                        animator.SetIKRotationWeight(AvatarIKGoal.RightHand,1);
                        animator.SetIKPosition(AvatarIKGoal.RightHand,lookObj.transform.position);
                        animator.SetIKRotation(AvatarIKGoal.RightHand,lookObj.transform.rotation);
                    }
                }        

            }

            //if the IK is not active, set the position and rotation of the hand and head back to the original position
            else {          
                animator.SetIKPositionWeight(AvatarIKGoal.RightHand,0);
                animator.SetIKRotationWeight(AvatarIKGoal.RightHand,0);
                animator.SetLookAtWeight(0);
            }
        }
    }

    public void CheckEquipped() {
        GameObject currEquipped = inventoryManager.instance.GetEquipped();
        if (currEquipped == null) { 
            equippedRanged = false; 
            if (lookObj != null && lookObj.name == "Crosshair(Clone)") Destroy(lookObj); 
            return; 
        }

        if (currEquipped.name == "Gun") {
            Vector3 direction = lookObj.transform.position - currEquipped.transform.position;
            direction.Normalize();
            equippedRanged = true;
            currEquipped.GetComponent<Gun>().crosshair = lookObj;
            currEquipped.transform.rotation = Quaternion.LookRotation(direction);
        } else {
            equippedRanged = false;
        }
    }

    public void FixCrosshair() {
        if (lookObj == null) return;

        lookObj.transform.rotation = Quaternion.LookRotation(lookObj.transform.position - primaryHand.transform.position);
    }
}