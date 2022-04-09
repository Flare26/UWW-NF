#ifndef Flasher_h
#define Flasher_h
#include "Arduino.h"
#include "Servo.h"

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
      intervalTime = interval;
    }

    void changeTimings(long on, long off, long interval, long m)
    {
      OnTime = on;
      OffTime = off;

      intervalTime = interval;
      ledState = LOW;
      digitalWrite(ledPin, ledState);
      previousMillis = m;
    }

    // currentMillis - previousMillis will give the amount of millis passed between the last time previousMillis was assigned
    void Update() {

      if ( OnTime == 0 && OffTime == 0 && intervalTime == 0 )
      {
        // manual OFF mode
        ledState = LOW;
        digitalWrite(ledPin, ledState);
      } else
      {
        unsigned long currentMillis = millis() + intervalTime;
        if ((ledState == HIGH) && (currentMillis - previousMillis >= OnTime)) {
          ledState = LOW; // Turn it off
          previousMillis = currentMillis;
          digitalWrite(ledPin, ledState); // Update the LED
        } else if ((ledState == LOW) && (currentMillis - previousMillis >= OffTime)) {
          ledState = HIGH;
          previousMillis = currentMillis;
          digitalWrite(ledPin, ledState);
        }
      }
    }
};
#endif

int greenPin = 5;
int yellowPin = 6;
int redPin = 9;
int inPin = 11;
int mPin = 3;
int bstate;
int servoAngle;
int maxAngle = 90;
Servo m;

Flasher led1(redPin, 1000, 2000, 0);
Flasher led2(greenPin, 1000, 2000, 1000);
Flasher led3(yellowPin, 1000, 2000, 2000);

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

  m.attach(mPin); // servo driver

  pinMode(greenPin, OUTPUT);
  pinMode(yellowPin, OUTPUT);
  pinMode(redPin, OUTPUT);
  pinMode(inPin, INPUT_PULLUP); // so pullup mode works most reliably for how I have the button set up. Otherwise digitalRead will read both ones and zeroes while not pressed and thats no good

  bstate = digitalRead(inPin);
}

void restoreFlashers(long m)
{
  // include the current millis when restoring. Since im heavily modding the code from the slides
  // passing current millis when releasing button will ensure the update function of Flasher doesnt subtract against a time further in the past ( this will case interval desync )
  led1.changeTimings(1000, 2000, 0, m);
  led2.changeTimings(1000, 2000, 1000, m);
  led3.changeTimings(1000, 2000, 2000, m);
}
void UpdateServoValue(int state)
{
  if (state == HIGH)
  {
    // button not pressed
    if (servoAngle > 0)
      m.write(servoAngle -=m.read());
  } else if (state == LOW)
  {
    //button held
    if ( servoAngle < maxAngle)
      m.write(servoAngle += 5);
  }
}
int UpdateButtonValue()
{
  // called in loop
  int r = digitalRead(inPin);
  if (bstate != r)
  {
    bstate = r;
    ChangeBehavior(bstate);
  }
  return r;
}

void ChangeBehavior(int state)
{
  if (state == HIGH)
  {
    // button not pressed
    restoreFlashers(millis());
  } else if (state == LOW)
  {
    //button held
    led1.changeTimings(1000, 0, 0, millis());
    led2.changeTimings(0, 0, 0, millis());
    led3.changeTimings(0, 0, 0, millis());
  }
}

void loop() {
  // put your main code here, to run repeatedly:
  led1.Update();
  led2.Update();
  led3.Update();
  int s = UpdateButtonValue();
  UpdateServoValue(s);
  //Serial.println("Angle="+servoAngle);
}
