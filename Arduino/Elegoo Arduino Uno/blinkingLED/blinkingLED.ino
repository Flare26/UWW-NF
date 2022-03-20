const int OPIN = 9; // LED will be connected to pin 9
void setup() {
  // put your setup code here, to run once:
  pinMode(OPIN, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(OPIN,HIGH); // On
  delay(1000); // pauses entire program temporarily
  digitalWrite(OPIN, LOW); // off
  delay (1000);
}
