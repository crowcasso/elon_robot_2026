package edu.elon.robotics.hardware;

/*
 *  Defines the hardware on the robot.
 *
 *  @author J. Hollingsworth and K. Altmann
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class KiwiBot {

    // drivebase motors
    public DcMotor motorLeft;
    public DcMotor motorRight;
    public DcMotor motorAux;

    // array of motors - useful when performing the same task for each motor
    private final DcMotor[] drivebase;

    // which ratio are the motors using
    public final KiwiDriveRatio ratio;

    public KiwiBot(HardwareMap hardwareMap, boolean isAuto) {

        // access the motors defined in the configuration
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorAux = hardwareMap.dcMotor.get("motorAux");

        // drop the drivebase motors into an array
        // we are only doing this to allow us to write loops to control the drivebase
        drivebase = new DcMotor[]{motorLeft, motorRight, motorAux};

        // set the direction and zero power behavior for all drivebase motors
        // set the direction FORWARD for AndyMark Neverest motors
        // set the direction REVERSE for GoBilda YellowJacket motors
        for (DcMotor motor : drivebase) {
            motor.setDirection(DcMotor.Direction.FORWARD);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        // all drivebase encoders reset to 0
        resetDriveEncoders();

        // choose a ratio based on the isAuto boolean
        ratio = new KiwiDriveRatio(isAuto);
    }

    public void resetDriveEncoders() {
        // all drivebase encoders reset to 0
        for (DcMotor motor : drivebase) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void startMove(double drive, double strafe, double turn) {
        /*
         * How much power should we apply to the left,
         * right, and aux motor?
         *
         * If all 3 motors apply the same power in the
         * same direction, the robot will turn in place.
         */
        ratio.computeRatio(drive, strafe, turn);

        // apply the power to the motors
        motorLeft.setPower(ratio.powerLeft);
        motorRight.setPower(ratio.powerRight);
        motorAux.setPower(ratio.powerAux);
    }

    public void stopMove() {
        startMove(0, 0, 0);
    }
}
