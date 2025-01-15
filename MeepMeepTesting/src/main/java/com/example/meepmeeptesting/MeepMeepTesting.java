package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep mm = new MeepMeep(700);


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(mm)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(73, 73, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(9, -61.5, Math.toRadians(180)))
                .waitSeconds(3)
                //drop specimen 1
                .strafeToConstantHeading(new Vector2d(0,-31.5))
                .waitSeconds(1)

                .strafeToLinearHeading(new Vector2d(34,-47),Math.toRadians(360))
                .strafeToConstantHeading(new Vector2d(38,-5))
                .strafeToConstantHeading(new Vector2d(48,-5))
                //push block 1
                .strafeToConstantHeading(new Vector2d(48,-57))
                //go for second block and push
                .strafeToConstantHeading(new Vector2d(48,-5))
                .strafeToConstantHeading(new Vector2d(58.5,-5))
                .strafeToConstantHeading(new Vector2d(58.5,-57))

                //pick up specimen 1
                .strafeToConstantHeading(new Vector2d(58.5, -52))
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
                .strafeToLinearHeading(new Vector2d(47,-56),Math.toRadians(90))

                .build()

        );

        mm.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}