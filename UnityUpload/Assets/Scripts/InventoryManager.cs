using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using System.Collections.Generic;
using TMPro;
using UnityEngine.InputSystem;

public class InventoryManager : MonoBehaviour
{
    public InventoryManager instance;

    public List<Item> inventory = new List<Item>(); // List to store all the items
    public Dictionary<int, Item> hotbar = new Dictionary<int, Item>(); // Dictionary to store slot number
    public GameObject inventoryPanel; // Reference to the Inventory Panel UI
    public GameObject hotbarPanel;
    public GameObject itemSlotPrefab; // Reference to the Item Slot Prefab
    public Transform inventorySlotParent; // Reference to the parent for item slots
    public Transform equippedHand; // Reference to Transform of equipped item position

    public Color hotbarDisplay;

    private string _currMouseSelected;
    private GameObject _currEquipped;
    private Item _currItem = null;

    private void Awake() {
        instance = this;
    }

    private void Start()
    {
        // Close the inventory initially
        hotbarPanel.SetActive(true);
        inventoryPanel.SetActive(false);

        // Add initial test items (You can remove these later)
        inventory.Add(new Item { itemID = 1, itemName = "Health Potion", iconSprite = Resources.Load<Sprite>("HealthPotionIcon"), itemPrefab = Resources.Load<GameObject>("Potion"), hotbar = true });
        inventory.Add(new Item { itemID = 2, itemName = "Sword", iconSprite = Resources.Load<Sprite>("SwordIcon"), itemPrefab = Resources.Load<GameObject>("Sword"), hotbar = true });
        inventory.Add(new GunItem { itemID = 3, itemName = "Gun", iconSprite = Resources.Load<Sprite>("GunIcon"), itemPrefab = Resources.Load<GameObject>("Gun"), hotbar = true });  // Added a duplicate for testing stack size

        // Update the inventory UI
        UpdateInventoryUI();
        UpdateHotbarUI();

        // Update the equipment
        UpdateEquipment();
    }

    private void Update() {
        UpdateMouseSelected();
        UpdateEquipment();
        if (Mouse.current.leftButton.ReadValue() > 0 && _currItem is not null) { _currItem.PrimaryAction(_currEquipped); }
    }

    public void ToggleInventory()
    {
        inventoryPanel.SetActive(!inventoryPanel.activeSelf);
        if (inventoryPanel.activeSelf) hotbarPanel.GetComponent<Image>().color = hotbarDisplay;
        else hotbarPanel.GetComponent<Image>().color = new Color(88, 88, 88, 0);
    }

    // Update the inventory UI by creating item slots and populating them
    public void UpdateInventoryUI()
    {
        // Clear any existing item slots
        foreach (Transform child in inventorySlotParent)
        {
            Destroy(child.gameObject);
        }

        // Create new item slots for each inventory item
        for (int i = 0; i < inventory.Count; i++)
        {
            Item item = inventory[i];
            if (!item.hotbar) {
                GameObject newSlot = Instantiate(itemSlotPrefab, inventorySlotParent);
                newSlot.GetComponent<ItemSlot>().SetManager(instance);
                newSlot.GetComponent<ItemSlot>().SetItem(item); // Set the Item data to the ItemSlot script
                newSlot.GetComponent<Image>().color = new Color(0, 0, 0, 0);
            }
        }
    }

    public void UpdateHotbarUI() {
        // Clear any existing item slots
        foreach (Transform child in hotbarPanel.transform)
        {
            Destroy(child.gameObject);
        }

        for (int i = 0; i < inventory.Count; i++)
        {
            Item item = inventory[i];
            if (item.hotbar) {
                GameObject newSlot = Instantiate(itemSlotPrefab, hotbarPanel.transform);
                newSlot.GetComponent<ItemSlot>().SetManager(instance);
                newSlot.GetComponent<ItemSlot>().SetItem(item); // Set the Item data to the ItemSlot script
            }
        }
    }

    public void UpdateMouseSelected() {
        PointerEventData pointerEventData = new PointerEventData(EventSystem.current);
        pointerEventData.position = Input.mousePosition;

        List<RaycastResult> raycastResultList = new List<RaycastResult>();
        EventSystem.current.RaycastAll(pointerEventData, raycastResultList);

        foreach (RaycastResult raycastResult in raycastResultList) {
            if (raycastResult.gameObject.name == "InventoryPanel") {
                _currMouseSelected = "Inventory";
            } else if (raycastResult.gameObject.name == "HotbarPanel") {
                _currMouseSelected = "Hotbar";
            }
        }
    }

    public void Swap(ItemSlot itemSlot) {
        if (!inventoryPanel.activeSelf) return;
        if (_currMouseSelected == "Inventory") {
            itemSlot.GetItem().hotbar = false;
        } else if (_currMouseSelected == "Hotbar") {
            itemSlot.GetItem().hotbar = true;
        }

        UpdateInventoryUI();
        UpdateHotbarUI();
    }

    public void Select(ItemSlot itemSlot) {
        if (_currEquipped != null) { Destroy(_currEquipped); }
        foreach (Transform item in hotbarPanel.transform) { // Deselect all other items
            ItemSlot slot = item.GetComponent<ItemSlot>();
            if (slot != itemSlot) {
                slot.SetBorder(false);
                slot.GetComponent<Select>().selected = false;
            }
        }

        GameObject prefab = itemSlot.GetItem().itemPrefab;
        _currItem = itemSlot.GetItem();

        if (prefab != null) { _currEquipped = Instantiate(prefab); _currEquipped.name = prefab.name; }
        else _currEquipped = null;
    }

    public void Deselect() {
        if (_currEquipped != null) { Destroy(_currEquipped); }
        _currEquipped = null;
        _currItem = null;
    }

    public void UpdateEquipment() {
        if (_currEquipped != null) {
            Transform handle = _currEquipped.transform.Find("Handle");
            Vector3 displacement = _currEquipped.transform.position - handle.position;

            _currEquipped.transform.position = equippedHand.position + displacement;
            _currEquipped.transform.rotation = Quaternion.LookRotation(equippedHand.forward);
        }
    }

    public GameObject GetEquipped() {
        return _currEquipped;
    }
}
