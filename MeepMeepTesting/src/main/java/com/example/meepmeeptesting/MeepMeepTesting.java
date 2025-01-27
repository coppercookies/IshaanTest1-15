package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.AccelConstraint;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep mm = new MeepMeep(700);


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(mm)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(14, -61.5, Math.toRadians(180)))
                //drop specimen 1
                .strafeToConstantHeading(new Vector2d(10,-31.5))
                .waitSeconds(1)

                .strafeToConstantHeading(new Vector2d(10,-33))




//                //Go to block 1
                .splineToLinearHeading(new Pose2d(27,-40,Math.toRadians(90)),Math.toRadians(360))
                .splineToLinearHeading(new Pose2d(46,-5,Math.toRadians(90)),Math.toRadians(360))
                .strafeToLinearHeading(new Vector2d(46,-57),Math.toRadians(90))

                //Go to block 2
                .splineToConstantHeading(new Vector2d(46, -5), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(58, -5), Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(58, -57), Math.toRadians(270.00))




                //pick up specimen 1
                .strafeToConstantHeading(new Vector2d(58.5, -52))
                .strafeToLinearHeading(new Vector2d(58.5,-52.01),Math.toRadians(360), new AngularVelConstraint(Math.PI))
                .strafeToConstantHeading(new Vector2d(31.3,-58.2))
                .waitSeconds(1)


                //drop specimen 2 and come back
                .strafeToLinearHeading(new Vector2d(4, -30.6),Math.toRadians(180))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(31.6, -54),Math.toRadians(360))
                .strafeTo(new Vector2d(31.6,-57.6))
                .waitSeconds(1)


                //drop specimen 3 and come back
                .strafeToLinearHeading(new Vector2d(6, -30.6),Math.toRadians(180))
                .waitSeconds(1)

                .strafeToLinearHeading(new Vector2d(31.6, -54),Math.toRadians(360))
                .strafeTo(new Vector2d(31.6,-57.6))
                .waitSeconds(1)


                //drop specimen 4 and park
                .strafeToLinearHeading(new Vector2d(8, -30.6),Math.toRadians(180))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(47,-56),Math.toRadians(90), new TranslationalVelConstraint(100))

                .build()

        );

        mm.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}