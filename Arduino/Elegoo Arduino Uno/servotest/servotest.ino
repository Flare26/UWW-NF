#include <Servo.h> //library
int servoPin = 9; //pwm to control angle
Servo servo1;  //Create servo motor object
void setup()
{
servo1.attach(servoPin); // Connect  pin 9 and servo object
}
// find it under file-examples-Servo-sweep
void loop() {
int position;
servo1.write(90); //90 degree
delay(1000); // spare time for moving servo motor
servo1.write(180); //180 degree
delay(1000);
servo1.write(0); //0 degree
delay(1000);
for(position = 0; position < 180; position += 2) {
servo1.write(position); delay(20); }
for(position = 180; position >= 0; position -= 1) {
servo1.write(position); delay(20);
}  } //end
