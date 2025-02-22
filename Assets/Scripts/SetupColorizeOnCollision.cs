using UnityEngine;

public class SetupColorizeOnCollision : MonoBehaviour
{
    public Mesh cube;
    void Start()
    {
        // Create a new GameObject and attach the ColorizeOnCollisionURP script to it.
        GameObject colorizedObject = new GameObject("Colorized Object");
        colorizedObject.AddComponent<ColorizeOnCollisionURP>();

        // Set the colorToChangeTo property to red.
        ColorizeOnCollisionURP colorizeOnCollisionScript = colorizedObject.GetComponent<ColorizeOnCollisionURP>();
        colorizeOnCollisionScript.colorToChangeTo = Color.red;

        // Add a MeshRenderer component to the object.
        MeshRenderer renderer = colorizedObject.AddComponent<MeshRenderer>();

        MeshFilter filter = colorizedObject.AddComponent<MeshFilter>();
        colorizedObject.GetComponent<MeshFilter>().mesh = cube;

        // Assign a URP lit material to the renderer.
        renderer.material = new Material(Shader.Find("Universal Render Pipeline/Lit"));

        // Place the object in the scene.
        colorizedObject.transform.position = new Vector3(0, 0, 0);
    }
}