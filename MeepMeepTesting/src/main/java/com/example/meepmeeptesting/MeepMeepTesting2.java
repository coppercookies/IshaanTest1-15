package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        //Code goes in here
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(14, -61.5, Math.toRadians(180)))
//drop specimen 1
                        .waitSeconds(4)
                        .strafeToConstantHeading(new Vector2d(10,-31.5))
                        .waitSeconds(1)
                        .strafeToConstantHeading(new Vector2d(10,-33))



//                //Go to block 1


                        .splineToLinearHeading(new Pose2d(36,-15,Math.toRadians(360)),Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(36, -5), Math.toRadians(90))

                //strafe in front of block 1
                        .splineToConstantHeading(new Vector2d(48.2, -5), Math.toRadians(270))

                        .splineToConstantHeading(new Vector2d(48.2, -51), Math.toRadians(90))
                                .waitSeconds(0.01)

                        //Go to block 2

                        .splineToConstantHeading(new Vector2d(48.2, -5), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(58, -5), Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(58, -57), Math.toRadians(270.00))

                //get away from 2nd pushed block and change heading

                        //pick up specimen 1
                        .strafeToLinearHeading(new Vector2d(45,-45),Math.toRadians(360))
                        .strafeToConstantHeading(new Vector2d(31.3,-58.2))
                        .waitSeconds(0.7)


                        //drop specimen 2 and come back
                        .strafeToLinearHeading(new Vector2d(4, -30.6),Math.toRadians(180))
                        .waitSeconds(1)
                        .strafeToLinearHeading(new Vector2d(31.6, -54),Math.toRadians(360))
                        .strafeTo(new Vector2d(31.6,-57.6))
                        .waitSeconds(0.7)


                        //drop specimen 3 and come back
                        .strafeToLinearHeading(new Vector2d(6, -30.6),Math.toRadians(180))
                        .waitSeconds(1)

                        .strafeToLinearHeading(new Vector2d(31.6, -54),Math.toRadians(360))
                        .strafeTo(new Vector2d(31.6,-57.6))
                        .waitSeconds(0.7)


                        //drop specimen 4 and park
                        .strafeToLinearHeading(new Vector2d(8, -30.6),Math.toRadians(180))
                        .waitSeconds(1)
                        .strafeToLinearHeading(new Vector2d(47,-56),Math.toRadians(90), new TranslationalVelConstraint(100))

                        .build()
        );



        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}