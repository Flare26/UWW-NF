#ifndef Flasher_h
#define Flasher_h
#include "Arduino.h"
class Flasher {
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

  void Update() {
  unsigned long currentMillis = millis()+intervalTime;
  if((ledState==HIGH) && (currentMillis-previousMillis >= OnTime)) {
    ledState = LOW; // Turn it off
    previousMillis = currentMillis; // Remember the time
    digitalWrite(ledPin, ledState); // Update the actual LED
  } else if ((ledState == LOW) && (currentMillis - previousMillis >= OffTime)) {
    ledState = HIGH; // turn it on
    previousMillis = currentMillis;
    digitalWrite(ledPin, ledState); 
  }  } 
};
#endif

int greenPin = 5;
int yellowPin = 6;
int redPin = 9;
Flasher led1(9,1000,2000,0);
Flasher led2(5,1000,2000,1000);
Flasher led3(6,1000,2000,2000);
void setup() {
  // put your setup code here, to run once:
  pinMode(greenPin, OUTPUT);
  pinMode(yellowPin, OUTPUT);
  pinMode(redPin, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  led1.Update();
  led2.Update();
  led3.Update();
}
