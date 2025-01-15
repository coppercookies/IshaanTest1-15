package org.firstinspires.ftc.teamcode.OtherTest;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class Servo_SliderTeleOpTest extends OpMode {

    DcMotor arm;
    Servo pivotIntake;
    @Override
    public void init() {
        arm = hardwareMap.get(DcMotor.class, "arm");
        pivotIntake = hardwareMap.get(ServoImplEx.class,"pivotIntake");
    }
    @Override
    public void loop() {

        double armPower;
        if (gamepad1.y) {
            armPower = 0.8;
        } else if (gamepad1.a) {
            armPower = -0.8;
        } else {
            armPower = 0;
        }
        arm.setPower(armPower);

        double armPos = arm.getCurrentPosition();

        //change 2000, to whatever the arm's full lnegth is
        //The max makes sure it never goes below 0, and the min makes sure it never excedes 1 incase the arm goes too much or too littele
        double servoPosition = Math.max(0, Math.min(armPos / 5000, 1));

        pivotIntake.setPosition(servoPosition);

        telemetry.addData("armmPos", armPos);
        telemetry.addData("servoPos", servoPosition);
        telemetry.update();
    }
    //Another approach idea
    /*
     * see how many degrees for ex. 2000 encoder ticks is, and then ex. 4000 ticks, and one more value
     * then do regresion and get the slope of the line
     * the slope of the line tells you how many encoder ticks one degree measure is
     * then you make the servo go negative of whatever degree measure you got from the arm (found by slope)
     */

}
