using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.InputSystem;

public class Drag : MonoBehaviour
{

    private ItemSlot itemSlot;
    private bool selected = false;

    // Start is called before the first frame update
    void Start()
    {
        itemSlot = GetComponent<ItemSlot>();
    }

    // Update is called once per frame
    void Update()
    {
        if (selected && itemSlot.GetManager().inventoryPanel.activeSelf) {
            transform.position = Mouse.current.position.ReadValue();
        }
    }

    public void Select() {
        selected = true;
    }

    public void Deselect() {
        selected = false;
    }

    public void FireSwap() {
        itemSlot.GetManager().Swap(itemSlot);
    }
}
