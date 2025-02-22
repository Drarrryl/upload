using UnityEngine;

public class ColorizeOnCollision : MonoBehaviour
{
    public Color colorToChangeTo;

    void OnCollisionEnter(Collision collision)
    {
        // Get the renderer of the object we collided with.
        MeshRenderer renderer = gameObject.GetComponent<MeshRenderer>();

        bool isURPLit = MaterialChecker.IsURPLit(gameObject.GetComponent<MeshRenderer>().material);

        // If the renderer is not null, change its material color to the color we specified.
        if (renderer != null && collision.gameObject.layer != 3)
        {
            if (isURPLit) MaterialColorizer.ChangeColor(renderer.material, colorToChangeTo);
        }
    }
}