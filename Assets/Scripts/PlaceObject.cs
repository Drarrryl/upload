using System;
using UnityEngine;
using UnityEngine.UI;

public class PlaceObject : MonoBehaviour
{
    public GameObject placeableCube; // Drag and drop the "PlaceableCube" prefab in the Inspector
    public GameObject placeableSphere; // Drag and drop the "PlaceableCube" prefab in the Inspector
    public GameObject placeableCapsule; // Drag and drop the "PlaceableCube" prefab in the Inspector
    public GameObject cubeOutline; // Drag and drop the "OutlinePrefab" (a simple wireframe model) in the Inspector
    public GameObject sphereOutline; // Drag and drop the "OutlinePrefab" (a simple wireframe model) in the Inspector
    public GameObject capsuleOutline; // Drag and drop the "OutlinePrefab" (a simple wireframe model) in the Inspector

    public Button cubeButton; // Drag and drop the "CubeButton" in the Inspector
    public Button sphereButton;
    public Button capsuleButton;
    public Button removeButton;
    public Button cancelButton;

    public Material outline;


    private string selected = "";

    private bool isSelected = false;

    private bool hover = false;

    private GameObject currOutline;

    private GameObject currMouseSelected;

    private bool remover;

    private GameObject removerOutline;

    private void Awake()
    {
        cubeButton.onClick.AddListener(() => Select("Cube"));

        sphereButton.onClick.AddListener(() => Select("Sphere"));

        capsuleButton.onClick.AddListener(() => Select("Capsule"));

        removeButton.onClick.AddListener(() => SelectRemove());

        cancelButton.onClick.AddListener(() => Cancel());
    }

    private void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            if (isSelected && !hover && !remover) { Place(); }
            if (remover) { Remove(); }
        }

        UpdateOutline();
        UpdateMouseSelect();
    }

    void Place()
    {

        GameObject prefab = null;

        if (selected == "Cube") prefab = placeableCube;
        else if (selected == "Sphere") prefab = placeableSphere;
        else if (selected == "Capsule") prefab = placeableCapsule;
        else return;

        // Raycasting from camera to determine plane hit position
        Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
        RaycastHit hit;
 
        if (Physics.Raycast(ray, out hit))
        {
            // Create an instance of the cube prefab at the hit position
            GameObject Instance = Instantiate(prefab, hit.point, Quaternion.identity);
        }
    }

    public void Select(string name)
    {
        // When the cube button is selected, set the isCubeSelected flag to true
        // and update the button image color to indicate selection.
        Deselect(selected);
        Deselect("Remove");
        selected = name;
        isSelected = true;
        GetOutline(name);

        if (name == "Cube") {
            cubeButton.image.color = Color.green;
        } else if (name == "Sphere") {
            sphereButton.image.color = Color.green;
        } else if (name == "Capsule") {
            capsuleButton.image.color = Color.green;
        }
    }

    public void Deselect(string type)
    {
        if (type == "Cube") cubeButton.image.color = Color.white;
        else if (type == "Sphere") sphereButton.image.color = Color.white;
        else if (type == "Capsule") capsuleButton.image.color = Color.white;
        else if (type == "Remove") { 
            removeButton.image.color = Color.white;
            remover = false;
            print("bruh");
        }
    }

    void GetOutline(string type) {
        GameObject prefab = GetOutlinePrefab(type);

        if (currOutline != null)
        {
            Destroy(currOutline);
        }
        
        if (prefab != null) {
            currOutline = Instantiate(prefab, transform);
        }
    }

    void UpdateOutline() {
        if (currOutline != null) {
            // Raycast from camera to determine plane hit position
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            RaycastHit hit;

            if (Physics.Raycast(ray, out hit))
            {
                currOutline.transform.position = hit.point;

            }
        }
    }

    public void UpdateMouseSelect() {
        // Raycasting from camera to determine plane hit position
        Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
        RaycastHit hit;
 
        if (Physics.Raycast(ray, out hit))
        {
            if (hit.collider.gameObject.tag == "Block") {
                if (currMouseSelected != hit.collider.gameObject) {
                    currMouseSelected = hit.collider.gameObject;
                    if (removerOutline is not null) { Destroy(removerOutline); }
                    removerOutline = null;
                }
                if (remover && removerOutline == null) { 
                    removerOutline = Instantiate(currMouseSelected, currMouseSelected.transform.position, currMouseSelected.transform.rotation);
                    removerOutline.GetComponent<MeshRenderer>().material = outline;
                    removerOutline.tag = "Outline";
                }
            } else if (hit.collider.gameObject.tag != "Outline") {
                currMouseSelected = null;
                Destroy(removerOutline);
                removerOutline = null;
            }
        }
    }

    public void SelectRemove() {
        Cancel(false);
        if (!remover) { 
            remover = true;
            removeButton.image.color = Color.green;
        }
        else { 
            Deselect("Remove");
        }
    }

    public void Remove() {
        if (currMouseSelected is not null) {
            Destroy(currMouseSelected);
            Destroy(removerOutline);
        }
    }

    public void Cancel(bool remove = true) {
        Destroy(currOutline);
        Deselect(selected);
        if (remove) Deselect("Remove");
        selected = "";
        isSelected = false;
    }

    public void HoverEnter() {
        hover = true;
    }

    public void HoverExit() {
        hover = false;
    }

    // Helper method to get the corresponding button for a selected type
    private Button GetButton(string type)
    {
        switch (type)
        {
            case "Cube":
                return cubeButton;
            case "Sphere":
                return sphereButton;
            case "Capsule":
                return capsuleButton;
            default:
                return null;
        }
    }

    // Helper method to get the corresponding prefab for a selected type
    private GameObject GetSelectedPrefab()
    {
        switch (selected)
        {
            case "Cube":
                return placeableCube;
            case "Sphere":
                return placeableSphere;
            case "Capsule":
                return placeableCapsule;
            default:
                return null;
        }
    }

    // Helper method to get the corresponding prefab for a selected type
    private GameObject GetOutlinePrefab(string type)
    {
        switch (type)
        {
            case "Cube":
                return cubeOutline;
            case "Sphere":
                return sphereOutline;
            case "Capsule":
                return capsuleOutline;
            default:
                return null;
        }
    }
}