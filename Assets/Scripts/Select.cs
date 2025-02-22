using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Select : MonoBehaviour
{

    public bool selected = false;
    private ItemSlot itemSlot;

    // Start is called before the first frame update
    void Start()
    {
        itemSlot = GetComponent<ItemSlot>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void ToggleSelect() {
        if (selected) {
            selected = false;

            itemSlot.SetBorder(false);
            itemSlot.GetManager().Deselect();
        } else {
            selected = true;

            itemSlot.SetBorder(true);
            itemSlot.GetManager().Select(itemSlot);
        }
    }
}
