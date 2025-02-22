using UnityEngine;

public static class MaterialColorizer
{
    public static void ChangeColor(Material material, Color color)
    {
        if (MaterialChecker.IsURPLit(material))
        {
            material.SetColor("_BaseColor", color);
        }
        else
        {
            Debug.LogWarning("Material is not URP Lit");
        }
    }
}