using UnityEngine;

public static class MaterialChecker
{
    public static bool IsURPLit(Material material)
    {
        return material.shader.name.StartsWith("Universal Render Pipeline/Lit");
    }
}