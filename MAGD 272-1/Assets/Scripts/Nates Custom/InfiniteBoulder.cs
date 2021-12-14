using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InfiniteBoulder : MonoBehaviour
{
    Vector3 respawn;
    [SerializeField] float fallDist;
    Rigidbody2D rb;
    float dist_travelled;
    // Start is called before the first frame update
    void Start()
    {
        respawn = gameObject.GetComponent<Transform>().position;
        rb = gameObject.GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        Vector3 curr = gameObject.transform.position;
        dist_travelled = Mathf.Abs(Vector3.Distance(respawn, curr));
        
        if (dist_travelled > fallDist)
        {
            gameObject.transform.position = respawn;
            rb.velocity = Vector2.zero;
            // no need to reset dist travelled, its calculated automatically, lol oops
        }
        
    }
}
