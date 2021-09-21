
// Nathan F Assignment 1
#include <Adafruit_CircuitPlayground.h>
#include <math.h>
float x,y,z;
float dx,dy,dz;
float modifier = 3;

void setup() {
  
  Serial.begin(1000);
  CircuitPlayground.begin();
  dx = CircuitPlayground.motionX();
  dy = CircuitPlayground.motionY();
  dz = CircuitPlayground.motionZ();
}

bool CheckIsMoving(float x,float y,float z) {
  
  if ( x > modifier || y > modifier || z > modifier )
  {
    //Serial.print("CheckIsMoving() true!\n");
    return true;
  } else {
    //Serial.print("CheckIsMoving() false!\n");
    return false;
  }
}

void loop() {
  // put your main code here, to run repeatedly:

  x = CircuitPlayground.motionX(); // Pos 
  y = CircuitPlayground.motionY(); // Down is positive, up negative
  z = CircuitPlayground.motionZ();
  
  Serial.print("X: ");
  Serial.print(x);
  Serial.print("  Y: ");
  Serial.print(y);
  Serial.print("  Z: ");
  Serial.println(z);
  
  if ( y > dy )
  {
        CircuitPlayground.setPixelColor(0, 255, 0, 0);
        CircuitPlayground.setPixelColor(9, 255, 0, 0);
  } else if ( y < dy ) {
        CircuitPlayground.setPixelColor(4, 0, 0, 255);
        CircuitPlayground.setPixelColor(5, 0, 0, 255);
  }
  else {
    CircuitPlayground.clearPixels();
  }



  //bool moving = CheckIsMoving(x, y, z);
  //Serial.print("CheckIsMoving() = " + moving);

  dx = x;
  dy = y;
  dz = z;
  delay(500);
}
