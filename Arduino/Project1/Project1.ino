 
#include <Adafruit_CircuitPlayground.h>
#include <Adafruit_Circuit_Playground.h>
#include "MIDIUSB.h"
/*
   MIDIUSB_test.ino

   Created: 4/6/2015 10:47:08 AM
   Author: gurbrinder grewal
   Modified by Arduino LLC (2015)
*/
#define NOTE_C1  33
#define NOTE_CS1 35
#define NOTE_D1  37
#define NOTE_DS1 39
#define NOTE_E1  41
#define NOTE_F1  44
#define NOTE_FS1 46
#define NOTE_G1  49
#define NOTE_GS1 52
#define NOTE_A1  55
#define NOTE_AS1 58
#define NOTE_B1  62
#define NOTE_C2  65
#define NOTE_CS2 69
#define NOTE_D2  73
#define NOTE_DS2 78
#define NOTE_E2  82
#define NOTE_F2  87
#define NOTE_FS2 93
#define NOTE_G2  98
#define NOTE_GS2 104
#define NOTE_A2  110
#define NOTE_AS2 117
#define NOTE_B2  123
#define NOTE_C3  131
#define NOTE_CS3 139
#define NOTE_D3  147
#define NOTE_DS3 156
#define NOTE_E3  165
#define NOTE_F3  175
#define NOTE_FS3 185
#define NOTE_G3  196
#define NOTE_GS3 208
#define NOTE_A3  220
#define NOTE_AS3 233
#define NOTE_B3  247
#define NOTE_C4  262
#define NOTE_CS4 277
#define NOTE_D4  294
#define NOTE_DS4 311
#define NOTE_E4  330
#define NOTE_F4  349
#define NOTE_FS4 370
#define NOTE_G4  392
#define NOTE_GS4 415
#define NOTE_A4  440
#define NOTE_AS4 466
#define NOTE_B4  494
#define NOTE_C5  523
#define NOTE_CS5 554
#define NOTE_D5  587
#define NOTE_DS5 622
#define NOTE_E5  659
#define NOTE_F5  698
#define NOTE_FS5 740
#define NOTE_G5  784
#define NOTE_GS5 831
#define NOTE_A5  880
#define NOTE_AS5 932
#define NOTE_B5  988
#define NOTE_C6  1047
#define NOTE_CS6 1109
#define NOTE_D6  1175
#define NOTE_DS6 1245
#define NOTE_E6  1319
#define NOTE_F6  1397
#define NOTE_FS6 1480
#define NOTE_G6  1568
#define NOTE_GS6 1661
#define NOTE_A6  1760
#define NOTE_AS6 1865
#define NOTE_B6  1976
#define NOTE_C7  2093
#define NOTE_CS7 2217
#define NOTE_D7  2349
#define NOTE_DS7 2489
#define NOTE_E7  2637
#define NOTE_F7  2794
#define NOTE_FS7 2960
#define NOTE_G7  3136
#define NOTE_GS7 3322
#define NOTE_A7  3520
#define NOTE_AS7 3729
#define NOTE_B7  3951
#define NOTE_C8  4186
#define NOTE_CS8 4435
#define NOTE_D8  4699
#define NOTE_DS8 4978

int mode = 0;
int freqMap[] =
{33
,35
,37
,39
,41
,44
,46
,49
,52
,55
,58
,62
,65
,69
,73
,78
,82
,87
,93
,98
,104
,110
,117
,123
,131
,139
,147
,156
,165
,175
,185
,196
,208
,220
,233
,247
,262
,277
,294
,311
,330
,349
,370
,392
,415
,440
,466
,494
,523
,554
,587
,622
,659
,698
,740
,784
,831
,880
,932
,988
,1047
,1109
,1175
,1245
,1319
,1397
,1480
,1568
,1661
,1760
,1865
,1976
,2093
,2217
,2349
,2489
,2637
,2794
,2960
,3136
,3322
,3520
,3729
,3951
,4186
,4435
,4699
,4978};
bool beingHeld[12] = {false, false, false, false, false, false, false, false, false, false, false, false};
float timeOfPress[4];
int beatint = 0;
// First parameter is the event type (0x09 = note on, 0x08 = note off).
// Second parameter is note-on/note-off, combined with the channel.
// Channel can be anything between 0-15. Typically reported to the user as 1-16.
// Third parameter is the note number (48 = middle C).
// Fourth parameter is the velocity (64 = normal, 127 = fastest).
void setup() {
  Serial.begin(4);
  CircuitPlayground.begin();
}

void drumModeLoop()
{
  // activate with button click
}

void noteOn(byte channel, byte pitch, byte velocity) {
  midiEventPacket_t noteOn = {0x09, 0x90 | channel, pitch, velocity};
  MidiUSB.sendMIDI(noteOn);
}

void noteOff(byte channel, byte pitch, byte velocity) {
  midiEventPacket_t noteOff = {0x08, 0x80 | channel, pitch, velocity};
  MidiUSB.sendMIDI(noteOff);
}



void controlChange(byte channel, byte control, byte value) {
  midiEventPacket_t event = {0x0B, 0xB0 | channel, control, value};
  MidiUSB.sendMIDI(event);
}

void setAllPixels(int colorhex, int brightness)
{
  CircuitPlayground.setBrightness(brightness);
  for (int16_t i = 0; i < 10; i ++)
  {
    CircuitPlayground.setPixelColor(i, colorhex);
  }
}

void playNote(int noteint, int ms, bool blocking)
{
  noteint += 12; //  adding +12 or +xx to noteint because the map indexes start at C1, change root note in FL
  int modu = noteint % 12; // get which note in the repeating sequence it is
  
  
  //int octave = noteint / 12;
  //float rnd = round( noteint / 10 ) * 10; // just want to round down from something like 25 to 20
  //int rounded = rnd + modu;
  //Serial.println("Unmodified note int: ");
  //Serial.print(noteint);
  //Serial.println();
  
  int freqIdx = freqMap[noteint]; // gives correct index for the octave frequency
  

  //Serial.println("modu = "); 
  //Serial.print(modu);
  //Serial.println();
  
  // B
  if (modu == 11) CircuitPlayground.playTone(freqIdx, ms, blocking);
  // A#
  if (modu == 10) CircuitPlayground.playTone(freqIdx, ms, blocking);
  // A
  if (modu == 9) CircuitPlayground.playTone(freqIdx, ms, blocking);

  //G#
  if (modu == 8) CircuitPlayground.playTone(freqIdx, ms, blocking);

  //G
  if (modu == 7) CircuitPlayground.playTone(freqIdx, ms, blocking);

  // F#
  if (modu == 6) CircuitPlayground.playTone(freqIdx, ms, blocking);
  // F
  if (modu == 5)CircuitPlayground.playTone(freqIdx, ms, blocking);


  // E
  if (modu == 4) CircuitPlayground.playTone(freqIdx, ms, blocking);

  //D#
  if (modu == 3) CircuitPlayground.playTone(freqIdx, ms, blocking);

  //D
  if (modu == 2) CircuitPlayground.playTone(freqIdx, ms, blocking);


  // C#
  if (modu == 1) CircuitPlayground.playTone(freqIdx, ms, blocking);


  // its a C
  if (modu == 0) CircuitPlayground.playTone(freqIdx, ms, blocking);
}

void updateLights(int h, int noteint)
{
  if ( h == 9 ) // note has been pressed
  {
    int modu = noteint % 12;
    if (beingHeld[modu] == false) beingHeld[modu] = true;

    switch (modu) {
      case 11:
        // B
        setAllPixels(0xFF0000, 5);
        break;

      case 10:
        // A#
        setAllPixels(0x00FF00, 150);
        break;

      case 9:
        // A
        setAllPixels(0xFF0000, 5);
        break;

      case 8:
        //G#
        setAllPixels(0x00FF00, 150);
        break;

      case 7:
        setAllPixels(0xFF0000, 5);
        //G
        break;

      case 6:
        // F#
        setAllPixels(0x00FF00, 150);
        break;

      case 5:
        setAllPixels(0x9000FF, 50);
        // F
        break;

      case 4:
        setAllPixels(0x00FBFF, 50);
        // E
        break;

      case 3:
        setAllPixels(0x9000FF, 5);;
        //D#
        break;

      case 2:
        //D
        setAllPixels(0xFF0059, 150);
        break;

      case 1:
        // C#
        setAllPixels(0xA1FF00, 5);
        break;

      case 0:
        // its a C
        setAllPixels(0xFF5900, 255); // orange
        break;
      default:
        
        break;
    }
  } else if (h == -1)
  {
    setAllPixels(0x00FF00, 255);
  }

}
void showMode()
{
   int n; // note
  // First parameter is the event type (0x0B = control change).
  // Second parameter is the event type, combined with the channel.
  // Third parameter is the control number number (0-119).
  // Fourth parameter is the control value (0-127).
  midiEventPacket_t rx;

  /*
    Serial.println("Sending note on");
    noteOn(0, 48, 64);   // Channel 0, middle C, normal velocity
    MidiUSB.flush();
    delay(500);
    Serial.println("Sending note off");
    noteOff(0, 48, 64);  // Channel 0, middle C, normal velocity
    MidiUSB.flush();
    delay(1500);
  */
  // controlChange(0, 10, 65); // Set the value of controller 10 on channel 0 to 65

  //do {
  rx = MidiUSB.read();
  if (rx.header == 9) {
    /*Serial.print("Received: ");
    Serial.print(rx.header, HEX);
    Serial.print("-");
    Serial.print(rx.byte1, HEX);
    Serial.print("-");
    Serial.print(rx.byte2, HEX);
    Serial.print("-");
    Serial.println(rx.byte3, HEX);
    */

    n = rx.byte2;
    /*Serial.println("Note to int: ");
    Serial.print(n);
    Serial.println();
    Serial.print("-");
    Serial.print(n % 12);
    Serial.println();
    */
    updateLights(rx.header, n);
    playNote(n, 42, false);
  }
  //}  while (rx.header != 0);

  if ( rx.header == 8 ) // note has been released
  {
    CircuitPlayground.clearPixels();
    // so we have a release signal now and I check to see if it was being held
    if (beingHeld[n % 12] == true)
    {
      beingHeld[n % 12] = false;
    }
  }

}
void calculateTempo()
{ 
  // tempo in 4/4
  
  long timeBetweenPress[4];
  for (int i = 0; i < 4; i++)
  {
   timeBetweenPress[i] = timeOfPress[i] - timeOfPress[i+1]; 
  }

  long sum = 0;

  for (int i = 0; i < 4; i++)
  {
   timeBetweenPress[i] = timeOfPress[i] - timeOfPress[i+1]; 
  }
}
void metronomeMode()
{

  if ( beatInt == 3)
  {
    
  }
}
void loop() {
  
  if (CircuitPlayground.leftButton() && mode == 0) mode = 1;
  else if (CircuitPlayground.leftButton() && mode == 1) mode = 0;
  
  if(mode == 0) showMode();
  if(mode == 1) 
  { 
    updateLights(-1, -1);
    metronomeMode()
    if (beatint == 3) deltaBeat += ;
    
  }
}
