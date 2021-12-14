using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class EnemyKillCountUI : MonoBehaviour
{
    public int killCount = 0;
    public Text killCountUIText;

    // Start is called before the first frame update
    void Start()
    {
        // subscribe to EnemyDeath Event -- will call CountEnemyDeath
        EnemyHealth.EnemyDeath += CountEnemyDeath;
        killCount = 0;
    }

    // Update is called once per frame
    void Update()
    {
        //EnemyHealth.EnemyDeath += CountEnemyDeath;
    }

    void CountEnemyDeath()
    {
        killCount++;
        if (killCountUIText)
        {
            killCountUIText.text = killCount.ToString();
        }
    }
}
