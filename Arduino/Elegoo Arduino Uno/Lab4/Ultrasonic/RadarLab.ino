#include<Servo.h>

int maxDeg = 180;

float lastMillis = 0;

int epin = 11;
int tpin = 10;
int bpin = 3;
int spin = 9;
bool isWaiting = false;
Servo s;
int degree;
long distance;
long duration;
long lowDist;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  // US Sens Initialization
  pinMode(tpin, OUTPUT);
  pinMode(epin, INPUT);
  pinMode(bpin, INPUT_PULLUP);

  // Servo init
  s.attach(spin);
}



long findDist(long time)
{
  return time*0.034 / 2;
}

void moveServo(int deg)
{
  s.write(deg);
}

void loop() {
  int b = digitalRead(bpin);
  //Serial.println(b);

  if (!isWaiting & b == 0)
  {
     // start the scan 
     s.write(0);
     delay(500);
  }
  
  if (isWaiting && degree < maxDeg)
  {
    long thisdist = 9999;
       // read sonic sensor, store the distance reading in readings[degree] where int [].len = maxDegree
  digitalWrite(tpin, HIGH);
  delayMicroseconds(10);
  digitalWrite(tpin,LOW);
    duration = pulseIn(epin, HIGH, 1000);
    thisdist = findDist(duration);
    if (  thisdist < lowDist)
    {
      lowDist = thisdist;
      Serial.println("New low distance");
      Serial.print(degree + " / " + lowDist);
    }


    // set up for next read
    degree++;
    moveServo(degree);
    
  }else if (degree == maxDeg)
  {
    isWaiting = false;
  }
}
