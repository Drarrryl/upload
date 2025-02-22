using UnityEngine;
using UnityEngine.UI;

public class ItemSlot : MonoBehaviour
{
    public Image itemIcon;
    public Text itemCountText; // Make sure the Text component is optional in the prefab
    private Item _item;
    private InventoryManager _manager;

    public void SetItem(Item newItem)
    {
        _item = newItem;
        itemIcon.sprite = newItem.iconSprite;
      
        // Display item count if more than one
        itemCountText.gameObject.SetActive(_item.count > 1);
        itemCountText.text = _item.count.ToString();
    }

    public Item GetItem() {
        return _item;
    }

    public void SetManager(InventoryManager newManager) {
        _manager = newManager;
    }
    public InventoryManager GetManager() {
        return _manager;
    }

    public void SetBorder(bool condition) {
        transform.Find("Border").gameObject.SetActive(condition);
    }
}