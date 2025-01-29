package org.firstinspires.ftc.teamcode.RRTestFiles;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.MecanumDrive;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Config
@Autonomous(name = "RRTest1225Parallel", group = "RRTest")
public class RRTest1225Parallel extends LinearOpMode {

    @Override
    public void runOpMode() {

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));

        DcMotor arm = hardwareMap.get(DcMotor.class,  "motor");

        Action Traj1 = drive.actionBuilder(drive.localizer.getPose())
                .lineToX(10)
                .build();

        Action Traj2 = drive.actionBuilder(new Pose2d(15,20,0))
                .splineTo(new Vector2d(5,5), Math.toRadians(90))
                .build();



        while(!isStopRequested() && !opModeIsActive()) {

        }

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        // Example of a drive action

                        // This action and the following action do the same thing
                        // Only that this action uses a Lambda expression to reduce complexity

                        new Action[]{Traj1, new Action() {
                            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                                telemetry.addLine("Action!");
                                telemetry.update();
                                return false;
                            }
                        }, (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        }, new ParallelAction( // several actions being run in parallel
                                Traj2, // Run second trajectory
                                (telemetryPacket) -> { // Run some action
                                    arm.setPower(1);
                                    return false;
                                }
                        ), drive.actionBuilder(new Pose2d(15, 10, Math.toRadians(125))) // Another way of running a trajectory (not recommended because trajectories take time to build and will slow down your code, always try to build them beforehand)
                                .splineTo(new Vector2d(25, 15), 0)
                                .build()})
        );

        Actions.runBlocking(
                new SequentialAction(

                )

        );


    }

}
