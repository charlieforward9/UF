//www.elegoo.com
//2016.12.9

/*
  LiquidCrystal Library - Hello World

 Demonstrates the use a 16x2 LCD display.  The LiquidCrystal
 library works with all LCD displays that are compatible with the
 Hitachi HD44780 driver. There are many of them out there, and you
 can usually tell them by the 16-pin interface.

 This sketch prints "Hello World!" to the LCD
 and shows the time.

  The circuit:
 * LCD RS pin to digital pin 7
 * LCD Enable pin to digital pin 8
 * LCD D4 pin to digital pin 9
 * LCD D5 pin to digital pin 10
 * LCD D6 pin to digital pin 11
 * LCD D7 pin to digital pin 12
 * LCD R/W pin to ground
 * LCD VSS pin to ground
 * LCD VCC pin to 5V
 * 10K resistor:
 * ends to +5V and ground
 * wiper to LCD VO pin (pin 3)

 Library originally added 18 Apr 2008
 by David A. Mellis
 library modified 5 Jul 2009
 by Limor Fried (http://www.ladyada.net)
 example added 9 Jul 2009
 by Tom Igoe
 modified 22 Nov 2010
 by Tom Igoe

 This example code is in the public domain.

 http://www.arduino.cc/en/Tutorial/LiquidCrystal
 */

// include the library code:
#include <LiquidCrystal.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(7, 8, 9, 10, 11, 12);

void setup() {
  // set up the LCD's number of columns and rows:
  lcd.begin(16, 2);
}
void loop() {
  // Print a message to the LCD.
  lcd.println("Tay Keith!      ");
  delay(500);
  lcd.clear();
  lcd.println("Fuck these      ");
  delay(500);
  lcd.setCursor(0,1);
  lcd.println("niggas up!!!      ");
  delay(2500);
  lcd.clear();
  lcd.println("I paid some      ");
  delay(500);
  lcd.setCursor(0,1);
  lcd.println("extra before we      ");
  delay(500);
  lcd.clear();
    lcd.println("even come out      ");
  delay(800);
  lcd.setCursor(0,1);
  lcd.println("And don't even      ");
  delay(500);
  lcd.clear();
    lcd.println("wear it to show      ");
  delay(500);
  lcd.setCursor(0,1);
  lcd.println("I ain't playin'      ");
  delay(900);
  lcd.clear();
    lcd.println("I hit the bitch      ");
  delay(700);
  lcd.setCursor(0,1);
  lcd.println("and I gave her      ");
  delay(700);
  lcd.clear();
    lcd.println("some racks      ");
  delay(700);
  lcd.setCursor(0,1);
  lcd.println("And I pull up      ");
  delay(500);
  lcd.clear();
    lcd.println("my pants, she      ");
  delay(500);
  lcd.setCursor(0,1);
  lcd.println("know I ain't      ");
  delay(500);
  lcd.clear();
  lcd.println("staying         ");
  delay(800);
  lcd.setCursor(0,1);
  lcd.println("Go on a store      ");
  delay(500);
  lcd.clear();
  lcd.println("run and get       ");
  delay(500);  
  lcd.setCursor(0,1);
  lcd.println("rubber bands      ");
  delay(500);
  lcd.clear();
  lcd.println("I done got rich,         ");
  delay(600);
  lcd.setCursor(0,1);
  lcd.println("I done put on          ");
  delay(500);
  lcd.clear(); 
  lcd.println("my mans                ");
  delay(900);
  lcd.setCursor(0,1);
  lcd.println("Choppas in traffic           ");
  delay(500);
  lcd.clear();
  lcd.println("that's just how           ");
  delay(500);
  lcd.setCursor(0,1);
  lcd.println("I'm livin'                ");
  delay(500);
  lcd.clear();
  lcd.println("------------------");
  delay(500);
  lcd.setCursor(0,1);
  lcd.println("-------------------");
  delay(500);
  lcd.clear();
  lcd.println("------------------");
  delay(500);
  lcd.setCursor(0,1);
  lcd.println("-------------------");
  delay(500);
  lcd.clear();
  lcd.println("------------------");
  delay(500);
  lcd.setCursor(0,1);
  lcd.println("-------------------");
  delay(500);
  lcd.clear();
  lcd.println("------------------");
  delay(500);
  lcd.setCursor(0,1);
  lcd.println("-------------------");
  delay(500);
  lcd.clear();
  
}
