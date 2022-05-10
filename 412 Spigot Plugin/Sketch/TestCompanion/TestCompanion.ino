#include <LiquidCrystal.h>
#include <Wire.h>
const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);
int bPin = 7;
int lastHP = 20;
int thisHP = 20;
void setup() {

  pinMode(bPin, OUTPUT);
  lcd.begin(16, 2);
  lcd.print("Please wait");
  lcd.setCursor(0,1);
  lcd.print("Listening...");

  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect.
  }

  // clear the previous msg
  lcd.setCursor(0,1);
  lcd.print("                ");
  lcd.setCursor(0, 0); 
  lcd.print("                ");
  //print new msg
  lcd.print("OK!"); 
  delay(1000);
}

void UpdateBlinker()
{
  // lastHP - thisHP > 2
  if ((lastHP - thisHP > 2) || thisHP < 6)
  {
    if (((int) millis() % 250) == 0)
    {
      digitalWrite(bPin, HIGH);
    }else
    {
      digitalWrite(bPin, LOW);
    }
  } else
  {
    digitalWrite(bPin, LOW);
  }
}

void loop() {

  if (Serial.available() > 0) {    

    lastHP = thisHP; // hand off the value to lastHP cuz its about to be replaced

    byte incomingByte = 0;
    incomingByte = Serial.read(); // incoming byte, removes first char inside of arduinos serial recieve buffer and shifts the remaining 
    if (incomingByte != -1) { // -1 means no data is available
      lcd.setCursor(0, 1); // set cursor to secon row
      lcd.print("                "); // clear second row
      lcd.setCursor(0, 1); // set cursor to secon row
      lcd.print(incomingByte); // print out byte value to second row
      thisHP = (int) incomingByte;
    }

     lcd.setCursor(0, 0); // set cursor to first row
     if (lastHP > thisHP)
     {
      lcd.print("You are hit! :( "); // print out to LCD
     } else
     {
       lcd.print("You r healed :) "); // print out to LCD
     }

  }

  UpdateBlinker();
}
