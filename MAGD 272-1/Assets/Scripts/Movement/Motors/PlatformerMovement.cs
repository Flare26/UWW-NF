using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[RequireComponent(typeof(Rigidbody2D))]
public class PlatformerMovement : MonoBehaviour, IMove
{
    Rigidbody2D rb;
    [SerializeField] Animator[] animators;

    IAttack<Health>[] attackScrpits;

    public float moveSpeed = 10f;


    void Start(){
        attackScrpits = GetComponents<IAttack<Health>>();
        rb = GetComponent<Rigidbody2D>();
    }


    public void Move(Vector2 direction)
    {
        if (Mathf.Abs(direction.x) > .01f)
        {
            rb.velocity = new Vector2(moveSpeed * direction.x, rb.velocity.y);
        }
        else {
            rb.velocity = new Vector2(0f, rb.velocity.y);
        }

        UpdateAnimations(rb.velocity.normalized.x, rb.velocity.normalized.y); //jump animations set in jump script
    }

    public void UpdateAnimations(float horizontal, float vertical) {
        if (animators.Length > 0){
            foreach (Animator a in animators) {
                a.SetFloat("HorizontalSpeed", horizontal);
                a.SetFloat("VerticalSpeed", vertical);
            }
        }

        if (attackScrpits.Length > 0)
        {
            foreach (IAttack<Health> attack in attackScrpits)
            {
                attack.SetDirection(new Vector2(horizontal, vertical).normalized);
            }
        }
    }

}
