#include <USB-MIDI.h>
#include <Adafruit_CircuitPlayground.h>

/*
 * readCap()
Description
Return capacitive touch sensor reading.
Syntax
readCap(p);
readCap(p, samples);
Parameters
p [uint8_t]: the capacitive sensor (0, 1, 2, 3, 6, 9, 10, or 12)
samples [uint8_t]: number of samples (default=10)
Returns
[uint16_t]: The raw capacitive touch sensor reading.
 */
int cpuppersens = 100; // once capacitence is higher than this value, the board sends MIDI off signal
int cplowersens = 50; // once capacitence is lower than this value, board sends MIDI on signal
USBMIDI_CREATE_DEFAULT_INSTANCE();
bool is_pressed [100 * false]; // initialize all as false
void setup()
{
  CircuitPlayground.begin();
  MIDI.begin(4); // Launch MIDI and listen to channel 4
}

void updateTouches()
{
  for (int i = 0; i < 7; i++)
  {
    int kick_midi_value = 60; // c4
    int snare_midi_value = 62;
    int hat_midi_value = 64;
    int tom_midi_value = 65;
    int kickcp = CircuitPlayground.readCap(1, 10);
    int snarecp = CircuitPlayground.readCap(2, 100);
    int hatcp = CircuitPlayground.readCap(3, 10);
    int tomcp = CircuitPlayground.readCap(6, 10);

    if (snarecp > 1000)
      Serial.println(CircuitPlayground.readCap(2, 10));

    // KICK
    if ( kickcp < cplowersens || CircuitPlayground.leftButton())
    {
      if (is_pressed[kick_midi_value] == false)
      {
        Serial.println("Kick");
        MIDI.sendNoteOn(kick_midi_value, 127, 1);    // Send a Note (pitch 42, velo 127 on channel 1)
        Serial.println(is_pressed[kick_midi_value]);
      }
      is_pressed[kick_midi_value] = true;

    } else if (kickcp > cpuppersens)
    { // if releasing touch
      if (is_pressed[kick_midi_value] == true)
      { // if the corresponding note to that touch pad was previously on....
        MIDI.sendNoteOff(kick_midi_value, 0, 1);     // Stop the note
        //Serial.println(is_pressed[kick_midi_value]);
      }
      is_pressed[kick_midi_value] = false; // force the value to be false regardless of previous state
    }

    // SNARE
    if ( snarecp < cplowersens || CircuitPlayground.rightButton() )
    {

      if (is_pressed[snare_midi_value] == false)
      {
        Serial.println("Snare");
        MIDI.sendNoteOn(snare_midi_value, 127, 1);    // Send a Note (pitch 42, velo 127 on channel 1)
        //Serial.println(is_pressed[snare_midi_value]);
      }
      is_pressed[snare_midi_value] = true;

    } else if (snarecp > cpuppersens)
    { // if releasing touch
      if (is_pressed[snare_midi_value] == true)
      { // if the corresponding note to that touch pad was previously on....
        MIDI.sendNoteOff(snare_midi_value, 0, 1);     // Stop the note
        //Serial.println(is_pressed[snare_midi_value]);
      }
      is_pressed[snare_midi_value] = false; // force the value to be false regardless of previous state
    }

        // HAT
    if ( hatcp < cplowersens )
    {
      if (is_pressed[hat_midi_value] == false)
      {
        Serial.println("Hat");
        MIDI.sendNoteOn(hat_midi_value, 127, 1);    // Send a Note (pitch 42, velo 127 on channel 1)
        //Serial.println(is_pressed[hat_midi_value]);
      }
      is_pressed[hat_midi_value] = true;

    } else if (hatcp > cpuppersens)
    { // if releasing touch
      if (is_pressed[hat_midi_value] == true)
      { // if the corresponding note to that touch pad was previously on....
        MIDI.sendNoteOff(hat_midi_value, 0, 1);     // Stop the note
        //Serial.println(is_pressed[hat_midi_value]);
      }
      is_pressed[hat_midi_value] = false; // force the value to be false regardless of previous state
    }

            // TOM
    if ( tomcp < cplowersens )
    {
      if (is_pressed[tom_midi_value] == false)
      {
        Serial.println("Tom");
        MIDI.sendNoteOn(tom_midi_value, 127, 1);    // Send a Note (pitch 42, velo 127 on channel 1)
        //Serial.println(is_pressed[tom_midi_value]);
      }
      is_pressed[tom_midi_value] = true;

    } else if (tomcp > cpuppersens)
    { // if releasing touch
      if (is_pressed[tom_midi_value] == true)
      { // if the corresponding note to that touch pad was previously on....
        MIDI.sendNoteOff(tom_midi_value, 0, 1);     // Stop the note
        //Serial.println(is_pressed[tom_midi_value]);
      }
      is_pressed[tom_midi_value] = false; // force the value to be false regardless of previous state
    }
  }
}

void loop()
{
  digitalWrite(LED_BUILTIN, HIGH);
  updateTouches();
  digitalWrite(LED_BUILTIN, LOW);
}
