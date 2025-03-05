#include <FastLED.h>

#define NUM_LEDS 50
#define LED_PIN 9
#define BRIGHTNESS 200
#define SPARKLE_PROBABILITY 5  // Probability (in percentage) of a sparkle occurring per loop iteration
#define DAMPING_FACTOR 100       // Damping factor to control fade rate (0-255)

CRGB leds[NUM_LEDS];

void setup() {
  FastLED.addLeds<WS2812, LED_PIN, RGB>(leds, NUM_LEDS);
  FastLED.setBrightness(BRIGHTNESS);
}

void loop() {
  // Randomly choose LEDs to sparkle
  for (int i = 0; i < NUM_LEDS; i++) {
    if (random(100) < SPARKLE_PROBABILITY) {
      leds[i] = CRGB::White;  // Set the LED to white for the sparkle
    }
  }

  FastLED.show();
  delay(50);  // Short delay for visibility

  // Apply damping factor to fade all LEDs
  for (int i = 0; i < NUM_LEDS; i++) {
    leds[i].fadeToBlackBy(DAMPING_FACTOR);  // Apply damping factor to control fade rate
  }

  FastLED.show();
  delay(50);  // Short delay before the next loop iteration
}
