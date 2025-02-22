using UnityEngine;

[System.Serializable]
public class Item
{
    public int itemID;
    public string itemName;
    public Sprite iconSprite;   
    public GameObject itemPrefab;
    public int count;
    public bool hotbar;
    public virtual void PrimaryAction(GameObject obj) {}
}