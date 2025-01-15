//24-25 Copper Cookies TeleOp
package org.firstinspires.ftc.teamcode.OtherTest;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="ChassisTeleOp^2", group="Iterative OpMode")
public class ExChassisTeleOp extends OpMode {
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

//        Without the power thing
//        vertical = -gamepad2.left_stick_y;
//        strafe = -gamepad2.left_stick_x;
//        turn = -gamepad2.right_stick_x;

        double exponent = 2;
        if (gamepad2.left_stick_y < 0) {
            vertical = Math.pow(gamepad2.left_stick_y, exponent);
        } else if (gamepad2.left_stick_y > 0) {
            vertical = -Math.pow(gamepad2.left_stick_y, exponent);
        } else {
            vertical = 0;
        }
        if (gamepad2.left_stick_x < 0) {
            strafe = Math.pow(gamepad2.left_stick_x, exponent);
        } else if (gamepad2.left_stick_x > 0){
            strafe = -Math.pow(gamepad2.left_stick_x, exponent);
        } else {
            strafe = 0;
        }
        if (gamepad2.right_stick_x < 0) {
            turn = Math.pow(gamepad2.right_stick_x, exponent);
        } else if (gamepad2.right_stick_x > 0) {
            turn = -Math.pow(gamepad2.right_stick_x, exponent);
        } else {
            turn = 0;
        }



        if (gamepad2.x) {
            isX = true;
        } else if (gamepad2.b) {
            isX = false;
        }

        if (isX) {
            telemetry.addData("Mode", "X Mode");
            RFPower = (turn + (vertical + strafe));
            RBPower = (turn + (vertical - strafe));
            LFPower = ((-turn) + (vertical - strafe));
            LBPower = ((-turn) + (vertical + strafe));

            if (gamepad2.dpad_right) {
                LFPower = 1;
                RFPower = -1;
                LBPower = -1;
                RBPower = 1;
            } else if (gamepad2.dpad_left) {
                LFPower = -1;
                RFPower = 1;
                LBPower = 1;
                RBPower = -1;
            }

            rightFront.setPower(RFPower / 2.6);
            rightBack.setPower(RBPower  / 2.6);
            leftFront.setPower(LFPower  / 2.6);
            leftBack.setPower(LBPower   / 2.6);

        } else {
            telemetry.addData("Mode", "B Mode");
            RFPower = (turn + (vertical + strafe));
            RBPower = (turn + (vertical - strafe));
            LFPower = ((-turn) + (vertical - strafe));
            LBPower = ((-turn) + (vertical + strafe));

            double GbStrafePower = 0.8;

            if (gamepad2.dpad_right) {
                LFPower = GbStrafePower;
                RFPower = -GbStrafePower;
                LBPower = -GbStrafePower;
                RBPower = GbStrafePower;
            } else if (gamepad2.dpad_left) {
                LFPower = -GbStrafePower;
                RFPower = GbStrafePower;
                LBPower = GbStrafePower;
                RBPower = -GbStrafePower;
            }
            rightFront.setPower(RFPower /1.5);
            rightBack.setPower(RBPower  /1.5);
            leftFront.setPower(LFPower  /1.5);
            leftBack.setPower(LBPower   /1.5);

        }
    }
}

