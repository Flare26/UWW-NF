using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class deathActivate : MonoBehaviour
{

    public GameObject objectTwoActivate;
    public bool deactivateAtStart;

    // Start is called before the first frame update
    void Start()
    {
        if (deactivateAtStart)
        {
            objectTwoActivate.SetActive(false);
        }
    }

    void OnDestroy()
    {
        objectTwoActivate.SetActive(true);
    }
}
