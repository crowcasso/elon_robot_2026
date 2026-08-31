package edu.elon.robotics.autonomous;

/*
 * General autonomous methods.
 */

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import edu.elon.robotics.hardware.KiwiBot;

public class AutoCommon extends LinearOpMode {

    protected KiwiBot robot;
    protected final TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new KiwiBot(hardwareMap, true);
    }

}