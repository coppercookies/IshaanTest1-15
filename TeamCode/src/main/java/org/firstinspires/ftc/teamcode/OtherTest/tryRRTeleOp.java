package org.firstinspires.ftc.teamcode.OtherTest;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@TeleOp(name = "FieldCentricTeleOp", group = "TeleOp")
public class tryRRTeleOp extends OpMode {

    private MecanumDrive drive;

    @Override
    public void init() {
        Pose2d startPose = new Pose2d(9, -61.5, Math.toRadians(180));
        drive = new MecanumDrive(hardwareMap, startPose);
        drive.localizer.setPose(startPose);

    }

    @Override
    public void loop() {

        drive.setDrivePowers(new PoseVelocity2d(
                new Vector2d(
                        -gamepad2.left_stick_y,
                        -gamepad2.left_stick_x
                ),
                -gamepad2.right_stick_x
        ));

        drive.updatePoseEstimate();

        Pose2d pose = drive.localizer.getPose();

        if (gamepad2.b) {
            Actions.runBlocking(
                    drive.actionBuilder(pose)
                            .splineTo(new Vector2d(30, 30), Math.PI / 2)
                            .splineTo(new Vector2d(0, 60), Math.PI)
                            .build());
        } else if (gamepad2.x) {
            Actions.runBlocking(
                    drive.actionBuilder(pose)
                            .endTrajectory()
                            .build()
            );
        }

        telemetry.addData("x", pose.position.x);
        telemetry.addData("y", pose.position.y);
        telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
        telemetry.update();
    }
}
