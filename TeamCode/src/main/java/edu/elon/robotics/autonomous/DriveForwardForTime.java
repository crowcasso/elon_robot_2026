package edu.elon.robotics.autonomous;

/*
 *  An example autonomous program that drives the robot
 *  forward for some given time.
 *
 *  @author J. Hollingsworth
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Drive Forward For Time", group="examples")
public class DriveForwardForTime extends AutoCommon {

    @Override
    public void runOpMode() throws InterruptedException {

        // set up the robot for autonomous mode
        super.runOpMode();

        // any initialization should be done before waitForStart()

        // pause between initialization and run
        waitForStart();

        // drive forward at 0.5 speed for 2 seconds then stop
        robot.startMove(0.5, 0, 0);
        sleep(2000);
        robot.stopMove();
    }

}
