#include<Servo.h>

int maxDeg = 180;

float lastMillis = 0;

int epin = 11;
int tpin = 13;
int bpin = 3;
int spin = 9;
bool isWaiting = false;
Servo s;


void setup() {
  // put your setup code here, to run once:

  // US Sens Initialization
  pinMode(tpin, OUTPUT);
  pinMode(epin, INPUT);
  pinMode(bpin, INPUT_PULLUP);

  // Servo init
  s.attach(spin);
}

int sonarScan ()
{
  int degree = 0;
  float duration;
  isWaiting = true;
  int  distances [maxDeg + 1]= {0}; // if the servo goes 0 - 180 that is technically 181 total
  
  while (degree <= maxDeg)
  {
    // read sonic sensor, store the distance reading in readings[degree] where int [].len = maxDegree
    duration = pulseIn(epin, HIGH);
    distances[degree] = distance(duration);

    // set up for next read
    degree++;
    moveServo(degree);
    delay(100);
  }
  isWaiting = false;

  // find shortest. distances is unsorted
  int closeIdx = distances[0]; // initialize maxId
 for (int i = 1 ; i <= maxDeg; i++)
 {
   if ( distances[i] < distances[closeIdx] )
    closeIdx = i;
 }
  return distances[closeIdx];
}

float distance(float t)
{
  return t*0.034 / 2;
}

void moveServo(int deg)
{
  s.write(deg);
}

void loop() {
  lastMillis = millis();
  int result = -1;
  int b = digitalRead(bpin);
  if (!isWaiting && b == 0)
  {
     result = sonarScan();
     Serial.println("Scan complete!");
     Serial.println( "Closest distance = " + result);
  }

  
}
