#include <LiquidCrystal.h>
#include <Wire.h>
const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);


void setup() {
  lcd.begin(16, 2);
  lcd.print("Please wait");
  lcd.setCursor(0,1);
  lcd.print("Listening...");

  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial connection
  }

  // clear the previous msg
  lcd.setCursor(0,1);
  lcd.print("                ");
  lcd.setCursor(0, 0); 
  lcd.print("                ");
  //print new msg
  lcd.print("OK!"); 
}

void loop() {

  if (Serial.available() > 0) {    
    byte incomingBytes [] = {0};
    lcd.setCursor(0, 0); // set cursor to first row
    lcd.print("<3 = "); // print out to LCD

    Serial.readBytes(incomingBytes, sizeof(incomingBytes)); // reads from JVM output stream
    // reconstruct the byte array
    
    // set the cursor down to the second row before writing the byte array to it.
    lcd.setCursor(0, 1); // set cursor to secon row
    for (int i = 0 ; i < sizeof(incomingBytes) ; i++ )
    {
      lcd.print(incomingBytes[i]);
      lcd.setCursor(i+1, 1); // set cursor to secon row
    }
  }
}
