using UnityEngine;
using UnityEngine.UI;

public class InventoryButtonController : MonoBehaviour
{
    public GameObject inventoryPanel;

    public void OnInventoryButtonClick()
    {
        inventoryPanel.SetActive(!inventoryPanel.activeSelf);
    }
}