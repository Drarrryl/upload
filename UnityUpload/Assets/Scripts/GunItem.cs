using UnityEngine;

public class GunItem : Item {

    public override void PrimaryAction(GameObject obj) {
        obj.GetComponent<Gun>().Shoot();
    }

}