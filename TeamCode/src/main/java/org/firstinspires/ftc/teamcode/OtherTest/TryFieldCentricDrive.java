package org.firstinspires.ftc.teamcode.OtherTest;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "Field Centric Mecanum Drive")
public class TryFieldCentricDrive extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        DcMotor leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        DcMotor rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        Deadline gamepadRateLimit = new Deadline(500, TimeUnit.MILLISECONDS);

        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        ));
        imu.initialize(parameters);

        waitForStart();

        while (opModeIsActive()) {
            double vertical = -gamepad2.left_stick_y;
            double strafe = gamepad2.left_stick_x;
            double turn = -gamepad2.right_stick_x;

            //max power oover here is 0.8
            double drivePower = 0.85 - (0.6 * gamepad2.right_trigger);

            if (gamepadRateLimit.hasExpired() && gamepad2.a) {
                imu.resetYaw();
                gamepadRateLimit.reset();
            }

            double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double adjustedVertical = vertical * Math.cos(heading) - strafe * Math.sin(heading);
            double adjustedStrafe = vertical * Math.sin(heading) + strafe * Math.cos(heading);

            double max = Math.max(Math.abs(adjustedStrafe) + Math.abs(adjustedVertical) + Math.abs(turn), 1);

            double RFPower = ((turn + (adjustedVertical - adjustedStrafe)) / max) * drivePower;
            double RBPower = ((turn + (adjustedVertical + adjustedStrafe)) / max) * drivePower;
            double LFPower = (((-turn) + (adjustedVertical + adjustedStrafe)) / max) * drivePower;
            double LBPower = (((-turn) + (adjustedVertical - adjustedStrafe)) / max) * drivePower;

            rightFront.setPower(RFPower);
            rightBack.setPower(RBPower);
            leftFront.setPower(LFPower);
            leftBack.setPower(LBPower);
        }
    }
}
