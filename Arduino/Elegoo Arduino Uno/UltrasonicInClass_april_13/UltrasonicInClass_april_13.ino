int trigpin = 3;
int echopin = 5;
int 
long duration;
long distance;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(trigpin, OUTPUT);
  pinMode(echopin, INPUT);

  
}

long microsecondstocentimeter(long time)
{
  return time * 0.034 / 2;
}
void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(trigpin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigpin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigpin,LOW);
  duration = pulseIn(echopin, HIGH);
  distance = microsecondstocentimeter(duration);
  Serial.print("Distance to target is: ");
  Serial.print(distance);
  Serial.println("Centimeters.");
}
