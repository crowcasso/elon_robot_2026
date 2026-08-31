package edu.elon.robotics.teleop;

/*
 *  Basic teleop driving using the left and right gamepad sticks.
 *
 *  @author J. Hollingsworth and K. Altmann
 */

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import edu.elon.robotics.hardware.KiwiBot;

@TeleOp(name = "Drive Robot", group = "TeleOp")
public class DriveRobot extends LinearOpMode {

    // represents the robot hardware (i.e., the robot).
    private KiwiBot robot;

    // provides continuous output while running
    private final TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new KiwiBot(hardwareMap, false);

        // block and wait until start is pressed
        waitForStart();

        // main loop
        while (opModeIsActive()){
            stickDriving();

            // show the encoder values for each motor
            panelsTelemetry.debug("encoder R: " + robot.motorRight.getCurrentPosition());
            panelsTelemetry.debug("encoder L: " + robot.motorLeft.getCurrentPosition());
            panelsTelemetry.debug("encoder A: " + robot.motorAux.getCurrentPosition());
            panelsTelemetry.update(telemetry);
        }
    }

    public void stickDriving() {
        /*
         * Read the gamepad joysticks and use that information
         * to drive the robot.
         */
        double drive  = -gamepad1.left_stick_y;
        double strafe = -gamepad1.left_stick_x;
        double turn   = gamepad1.right_stick_x;

        /*
         * Telemetry shows up at the bottom of the
         * drive station. It's a good way to help
         * you debug your code.
         */
        panelsTelemetry.debug("drive: " + drive);
        panelsTelemetry.debug("strafe: " + strafe);
        panelsTelemetry.debug("turn: " +  turn);

        // call startMove to move the robot
        robot.startMove(drive, strafe, turn);
    }
}
