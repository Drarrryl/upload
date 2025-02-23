using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ArrowController : MonoBehaviour
{

    public Material regularArrow;

    public Material hoverArrow;

    public GameObject pointingTo;

    public GameObject target;


    private MeshRenderer _renderer;


    // Start is called before the first frame update
    void Start()
    {
        _renderer = GetComponent<MeshRenderer>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void hovered() {
        _renderer.material = hoverArrow;
    }

    public void unhovered() {
        _renderer.material = regularArrow;
    }

    public void goTo() {
        if (pointingTo != null && target != null) {
            print("Target @ pos: " + target.transform.position + " going to spawnpoint @ pos: " + pointingTo.transform.position);
            target.GetComponent<CharacterController>().enabled = false;
            target.transform.position = pointingTo.transform.position;
            target.GetComponent<CharacterController>().enabled = true;
        }
    }
}
