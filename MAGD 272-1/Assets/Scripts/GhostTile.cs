using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class GhostTile : Trap
{
    // Start is called before the first frame update

    public float att_delay;
    private float delay_timer;
    public float spd_mult;
    float damage;
    float spinspd;
    bool inRange;
    bool inFlight;
    Vector2 moveVector;
    Vector2 attack_target;
    [SerializeField] public GameObject sprite;
    [SerializeField] GameObject targeter;

    public Vector2 spawn_pos;
    public Quaternion spawn_rot;
    private void Start()
    {
        spawn_pos = transform.position;
        spawn_rot = transform.rotation;
    }
    void OnEnable()
    {
        delay_timer = att_delay;
    }

    void OnTriggerEnter2D(Collider2D c)
    {
        if (inFlight != true)
        {
            //Debug.Log("Collider2D entered range");
            if (c.tag == "Player")
            {
                targeter.transform.position = c.transform.position;
                //attack_target = c.transform.position;
                //moveVector = c.transform.position - transform.position;
                inRange = true;
                //Debug.Log("BANZAI!!!");
                //Invoke("SavePerformance", 4);
            }
        }
    }

    public override void OnCollisionEnter2D(Collision2D collision)
    {
        //do nothing otherwise there will be an error since there is only a trigger associated with the object, not a collider
    }

    private void OnDisable()
    {
        inRange = false;
    }
    public override void ResetTrap()
    {
        // i fixed this nathan :)

        transform.SetPositionAndRotation(spawn_pos, spawn_rot);
        inFlight = false;
        inRange = false;
        gameObject.SetActive(true);
    }

    void SavePerformance()
    {
        inRange = false;
        gameObject.SetActive(false);
    }

    void AttackRoutine()
    {
        sprite.transform.Rotate(0, 0, 25, Space.World);

        if (delay_timer > 0)
        {
            delay_timer -= Time.deltaTime;
        } else
        {
            inFlight = true;
            transform.position = Vector2.Lerp(transform.position, targeter.transform.position, Time.deltaTime * spd_mult);
        }
    }
    // Update is called once per frame
    void FixedUpdate()
    {
        if (inRange == true) AttackRoutine();
    }
}
