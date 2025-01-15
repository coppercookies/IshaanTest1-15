package org.firstinspires.ftc.teamcode.RRTestFiles;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "HomeTest1224", group = "Autonomous")

public class HomeTest1224 extends LinearOpMode {

    public class Arm {
        private DcMotor arm;

        public Arm(HardwareMap hardwareMap) {
            arm = hardwareMap.get(DcMotor.class, "armMotor");
            arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        public class ArmUp implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    arm.setPower(0.8);
                    initialized = true;
                }

                double pos = arm.getCurrentPosition();
                packet.put("armPos", pos);
                if (pos < 3000.0) {
                    return true;
                } else {
                    arm.setPower(0);
                    return false;
                }
            }
        }

        public Action armUp() {
            return new ArmUp();
        }

        public class ArmDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    arm.setPower(-0.8);
                    initialized = true;
                }

                double pos = arm.getCurrentPosition();
                packet.put("armPos", pos);
                if (pos > 100.0) {
                    return true;
                } else {
                    arm.setPower(0);
                    return false;
                }
            }
        }

        public Action armDown() {
            return new ArmDown();
        }
    }

    public class Holder {
        private Servo holder;

        public Holder(HardwareMap hardwareMap) {
            holder = hardwareMap.get(Servo.class, "holder");
        }

        public class CloseHolder implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                holder.setPosition(0.55);
                return false;
            }
        }

        public Action closeHolder() {
            return new CloseHolder();
        }

        public class OpenHolder implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                holder.setPosition(1.0);
                return false;
            }
        }

        public Action openHolder() {
            return new OpenHolder();
        }
    }

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(-39, -65, Math.toRadians(180));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        // make a Holder instance
        Holder holder = new Holder(hardwareMap);
        // make a Arm instance
        Arm arm = new Arm(hardwareMap);

        TrajectoryActionBuilder traj1 = drive.actionBuilder(initialPose)
                .splineTo(new Vector2d(-54, -54), Math.toRadians(225.00));

        Action trajectoryActionChosen;
        trajectoryActionChosen = traj1.build();

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionChosen,
                        arm.armUp(),
                        holder.openHolder(),
                        arm.armDown()
                )
        );


        /*
        //Go to basket with pre-load and drop sample
                .strafeToLinearHeading(new Vector2d(-50, -50),Math.toRadians(225))
                .waitSeconds(4)
                //Go to first sample and go straight to pick up
                .strafeToLinearHeading(new Vector2d(-50,-40),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-50,-30),Math.toRadians(90))
                //Go to basket with first sample
                .strafeToLinearHeading(new Vector2d(-50, -50),Math.toRadians(225))
                .waitSeconds(4)
                //Go to second sample and pick up
                .strafeToLinearHeading(new Vector2d(-60,-40),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-60,-30),Math.toRadians(90))
                //Go to basket with second sample
                .strafeToLinearHeading(new Vector2d(-50, -50),Math.toRadians(225))
                .waitSeconds(4)

                //Go to third sample and pick up
                .strafeToLinearHeading(new Vector2d(-60,-40),Math.toRadians(135))
                .strafeToLinearHeading(new Vector2d(-60,-30),Math.toRadians(135))
                //Go to basket with third sample
                .strafeToLinearHeading(new Vector2d(-50, -50),Math.toRadians(225))
                .waitSeconds(4)
                .build()
            */

    }

}

