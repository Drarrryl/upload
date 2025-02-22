using System;
using UnityEngine;
using UnityEngine.UI;

public class PlaceObjectB : MonoBehaviour
{
    public GameObject placeableCube; // Drag and drop the "PlaceableCube" prefab in the Inspector
    public GameObject placeableSphere; // Drag and drop the "PlaceableSphere" prefab in the Inspector
    public GameObject placeableCapsule; // Drag and drop the "PlaceableCapsule" prefab in the Inspector
    public GameObject outlinePrefab; // Drag and drop the "Outline Prefab" (a simple wireframe model) in the Inspector

    public Button cubeButton; // Drag and drop the "CubeButton" in the Inspector
    public Button sphereButton;
    public Button capsuleButton;
    public Button cancelButton;

    private string selected = ""; 
    private bool isSelected = false;
    private bool hover = false;
    private GameObject outlineInstance; // To store the reference to the outline instance

    private void Awake()
    {
        cubeButton.onClick.AddListener(() => Select("Cube"));  
        sphereButton.onClick.AddListener(() => Select("Sphere"));
        capsuleButton.onClick.AddListener(() => Select("Capsule"));  
        cancelButton.onClick.AddListener(() => Cancel());
    }

    private void Update()
    {
        if (Input.GetMouseButtonDown(0) && isSelected && !hover) 
        {
            Place();
        }

        // Update outline visibility based on selection and hover
        UpdateOutlineVisibility();
    }

    void Place()
    {
        GameObject prefab = null;
        
        if (selected == "Cube") prefab = placeableCube;
        else if (selected == "Sphere") prefab = placeableSphere;
        else if (selected == "Capsule") prefab = placeableCapsule;
        else return;

        // Raycast from camera to determine plane hit position
        Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
        RaycastHit hit;
        
        if (Physics.Raycast(ray, out hit))
        {
            // Create an instance of the selected prefab at the hit position
            GameObject instance = Instantiate(prefab, hit.point, Quaternion.identity);
        }

        // Disable the outline instance since the object is placed
        if (outlineInstance != null)
        {
            outlineInstance.SetActive(false);
        }
    }

    public void Select(string name)  {
        Deselect(selected);
        selected = name; 
        isSelected = true;
        // Update the button color to indicate selection
        GetButton(selected).image.color = Color.green;
        
        // Create an outline instance if it doesn't exist already
        if (outlineInstance == null)
            CreateOutline();
    } 

    public void Deselect(string type)
    {
        if (type != "")
        { 
            GetButton(type).image.color = Color.white;    
        }
        isSelected = false;      
    }

    void CreateOutline()
    {
        // Create an instance of the outline prefab and store it
        outlineInstance = Instantiate(outlinePrefab);
        // Disable the outline instance initially
        outlineInstance.SetActive(false);
    }

    void UpdateOutlineVisibility()
    {
        if (outlineInstance != null)
        {
            // Show the outline only when an object is selected and mouse is not hovering over the place button
            outlineInstance.SetActive(isSelected && !hover);

            // Update the position and rotation of the outline to match the selected object
            if (isSelected)
            {
                GameObject prefab = GetSelectedPrefab();
                if (prefab != null)
                {
                    outlineInstance.transform.position = prefab.transform.position;
                    outlineInstance.transform.rotation = prefab.transform.rotation;
                    outlineInstance.transform.localScale = prefab.transform.lossyScale;
                }
            }
        }
    }

    public void Cancel()  
    { 
        Deselect(selected);  
        selected = "";  
        // Destroy the outline instance if it exists
        if (outlineInstance != null)
        {
            Destroy(outlineInstance);
        }
    } 
    
    public void HoverEnter()
    {
        hover = true;  
    } 

    public void HoverExit()
    {  
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
}