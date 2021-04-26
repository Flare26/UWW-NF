using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameManager : MonoBehaviour
{
    // Start is called before the first frame update
    public Room curr_room; // current room object
    public Text rl; // rool label
    // Update is called once per frame

    private void Start()
    {
        rl = GameObject.Find("RoomLabel").GetComponent<Text>();
        ChangeRoom(curr_room);
    }

    void ChangeRoom(Room r)
    {
        Debug.Log("Moving to " + curr_room.name);
        curr_room = r;
        rl.text = curr_room.name; // set the room indicator to the current room
    }
    
    void Update()
    {
        
    }
}
