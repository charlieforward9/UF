//www.elegoo.com
//2018.12.19
#include <Servo.h>
Servo myservo;

void setup(){
  myservo.attach(9);
} 
void loop(){
  myservo.write(45);// move servos to center position -> 30°
  delay(3000);
  myservo.write(90);// move servos to center position -> 90°
  delay(6000);
}
