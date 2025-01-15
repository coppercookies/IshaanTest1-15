package org.firstinspires.ftc.teamcode.OtherTest;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="ChassisTeleOp", group="Iterative OpMode")
public class ChassisTeleOp extends OpMode {
    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();


    private DcMotor rightFront, leftFront, rightBack, leftBack;
    boolean isX = false;


    @Override
    public void init() {
        // Init Drive ---------------------------------------------------------
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);


    }


    @Override
    public void start() {
        runtime.reset();
    }


    @Override
    public void loop() {
        NewDrive();
    }


    private void NewDrive() {
        double vertical;
        double strafe;
        double turn;
        double RFPower, RBPower, LFPower, LBPower;
        double drift_motor_power = 0.3;

        if (gamepad2.x) {
            isX = true;
        } else if (gamepad2.b) {
            isX = false;
        }

        if (isX) {
            telemetry.addData("Mode", "X Mode (Drift)");
            if (gamepad2.right_stick_x > 0) {
                LFPower = drift_motor_power;
                RFPower = -drift_motor_power;
                LBPower = drift_motor_power;
                RBPower = -drift_motor_power;
            } else if (gamepad2.right_stick_x < 0) {
                LFPower = -drift_motor_power;
                RFPower = drift_motor_power;
                LBPower = -drift_motor_power;
                RBPower = drift_motor_power;
            } else if (gamepad2.left_stick_y < 0) {
                LFPower = drift_motor_power;
                RFPower = drift_motor_power;
                LBPower = drift_motor_power;
                RBPower = drift_motor_power;
            } else if (gamepad2.left_stick_y > 0) {
                LFPower = -drift_motor_power;
                RFPower = -drift_motor_power;
                LBPower = -drift_motor_power;
                RBPower = -drift_motor_power;
            } else if (gamepad2.dpad_right) {
                //telemetry.addData("Strafe Right", true);
                LFPower = drift_motor_power;
                RFPower = -drift_motor_power;
                LBPower = -drift_motor_power;
                RBPower = drift_motor_power;
            } else if (gamepad2.dpad_left) {
//            telemetry.addData("Strafe Left", true);
                LFPower = -drift_motor_power;
                RFPower = drift_motor_power;
                LBPower = drift_motor_power;
                RBPower = -drift_motor_power;
            } else {
                LFPower = 0;
                RFPower = 0;
                LBPower = 0;
                RBPower = 0;
            }
        } else {
            telemetry.addData("Mode", "B Mode (Normal Drive)");

            vertical = -gamepad2.left_stick_y;
            strafe = -gamepad2.left_stick_x;
            turn = -gamepad2.right_stick_x;

            RFPower = (turn + (vertical + strafe));
            RBPower = (turn + (vertical - strafe));
            LFPower = ((-turn) + (vertical - strafe));
            LBPower = ((-turn) + (vertical + strafe));
        }

        rightFront.setPower(RFPower);
        rightBack.setPower(RBPower);
        leftFront.setPower(LFPower);
        leftBack.setPower(LBPower);
    }

}

