using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Trap : MonoBehaviour
{

    public Transform spawn;
    // basic trap, kills on contact

    private void OnEnable()
    {
        spawn = transform;
    }
    public virtual void ResetTrap()
    {

    }

    public virtual void OnCollisionEnter2D(Collision2D collision) {

    }
}
