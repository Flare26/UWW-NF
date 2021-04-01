using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UIElements;

public abstract class Room : MonoBehaviour
{
    TextElement gd;
    
    public new string name;
    void start()
    {
        gd = GameObject.Find("GameDialogue").GetComponent<TextElement>();
        
    }
   void SetDialogue (string str)
    {
        gd.text = str;
    }
}
