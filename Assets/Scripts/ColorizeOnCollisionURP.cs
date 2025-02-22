using UnityEngine;

public class ColorizeOnCollisionURP : MonoBehaviour
{
    public Color colorToChangeTo;

    private void OnCollisionEnter(Collision collision)
    {
        // Get the renderer of the object we collided with.
        MeshRenderer renderer = gameObject.GetComponent<MeshRenderer>();

        // If the renderer is not null, change its material color to the color we specified.
        if (renderer != null)
        {
            Material material = renderer.material;
            MaterialColorizer.ChangeColor(material, colorToChangeTo);
        }
    }
}