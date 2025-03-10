﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour
{

    public Transform target;
    Vector3 targetpos;
    public float smoothingTimePercentage = .1f;

    public bool constrainX = false;
    public bool constraintY = false;
    public float xoff;
    public float yoff;

    void FixedUpdate(){

        if (target != null) {
            // just setting manual cam anchor
            targetpos = target.position;
            Vector3 offset = targetpos;
            offset = target.position;
            offset.x += xoff;
            offset.y += yoff;
            targetpos = offset;
            

            Vector3 differance = targetpos - transform.position;
            if (constrainX) {
                differance.x = 0;
            }

            if (constraintY) {
                differance.y = 0;
            }




            transform.position = new Vector3(transform.position.x + differance.x * smoothingTimePercentage, transform.position.y + differance.y * smoothingTimePercentage, -10f);
        }
        
    }
}
