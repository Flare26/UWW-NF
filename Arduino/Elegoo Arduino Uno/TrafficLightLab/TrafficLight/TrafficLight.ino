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
private:
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
Flasher led2(8,1000,2000,1000);
Flasher led3(7,1000,2000,2000);
void setup() {
  // put your setup code here, to run once:
  pinMode(greenPin, OUTPUT);
  pinMode(yellowPin, OUTPUT);
  pinMode(redPin, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(greenPin, HIGH);   // turn the LED on (HIGH is the voltage level)
  digitalWrite(yellowPin, LOW);    // turn the LED off by making the voltage LOW
  digitalWrite(redPin, LOW);    // turn the LED off by making the voltage LOW
  delay(1000);                       // wait for a second
  digitalWrite(greenPin, LOW);
  digitalWrite(yellowPin, HIGH);
  digitalWrite(redPin, LOW);
  delay(1000);
  digitalWrite(greenPin, LOW);
  digitalWrite(yellowPin, LOW);
  digitalWrite(redPin, HIGH);
  delay(1000);
}
