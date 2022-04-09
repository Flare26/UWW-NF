#ifndef Flasher_h
#define Flasher_h
#include "Arduino.h"
#include <limits>
class Flasher {
  public:
int ledPin;
long OnTime;
long OffTime;
int ledState;

unsigned long previousMillis;
long intervalTime;


public:
Flasher(int pin, long on, long off, long interval)
{
  ledPin = pin;
  pinMode(ledPin, OUTPUT);
  OnTime = on;
  OffTime = off;
  ledState = LOW;
  previousMillis = 0;
  intervalTime=interval;
}

void updateTimes(long on, long off);

 // currentMillis - previousMillis will give the amount of millis passed between the last time previousMillis was assigned
  void Update() {
  unsigned long currentMillis = millis()+intervalTime;
  if((ledState==HIGH) && (currentMillis-previousMillis >= OnTime)) {
    ledState = LOW; // Turn it off
    previousMillis = currentMillis;
    digitalWrite(ledPin, ledState); // Update the LED
  } else if ((ledState == LOW) && (currentMillis - previousMillis >= OffTime)) {
    ledState = HIGH;
    previousMillis = currentMillis;
    digitalWrite(ledPin, ledState); 
  }  } 
};
#endif

int greenPin = 5;
int yellowPin = 6;
int redPin = 9;
int inPin = 11;
int bstate;

Flasher led1(redPin,1000,2000,0);
Flasher led2(greenPin,1000,2000,1000);
Flasher led3(yellowPin,1000,2000,2000);

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  
  pinMode(greenPin, OUTPUT);
  pinMode(yellowPin, OUTPUT);
  pinMode(redPin, OUTPUT);
  pinMode(inPin, INPUT_PULLUP); // so pullup mode works most reliably for how I have the button set up. Otherwise digitalRead will read both ones and zeroes while not pressed and thats no good

  bstate = digitalRead(inPin);
}

void UpdateButtonValue()
{
  int r = digitalRead(inPin);
  if (bstate != r)
  {
    bstate = r;
    ChangeBehavior(bstate);
  }
}

void ChangeBehavior(int state)
{
  if (state == HIGH)
  {
    // button not pressed
    led1.OffTime = 2000;
    led2.OnTime = 1000;
    led3.OnTime = 1000;
    
  } else if (state == LOW)
  {
    //button held
    led1.OffTime = 0;
    led2.OnTime = 0;
    led3.OnTime = 0;
  }
}

void loop() {
  // put your main code here, to run repeatedly:
  led1.Update();
  led2.Update();
  led3.Update();
  UpdateButtonValue();
}
