package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class RunnableMotor implements Runnable {
    DcMotor theMotor;
    boolean done = false;
    private double newPower;
    private String motorName;
    private int napLength = 25;

    public RunnableMotor (DcMotor aMotor) {
        theMotor = aMotor;
        motorName = aMotor.getDeviceName();
    }

    public void setPower (double newPower) {
        this.newPower = newPower;
    }

    public void run() {
        try {
            while (true) {

                // If we're done, get the heck out of here!
                if (done) {
                    throw new InterruptedException(motorName + " done.");
                }

                // Check to see if the user has asked for any of the services.
                if (theMotor.getCurrentPosition() >= 360 && newPower > 0) {
                    theMotor.setPower(0);
                    newPower = 0;
                }

                // We've moved to this position.  Wait for another command.
                theMotor.setPower(this.newPower);
                Thread.sleep (napLength);
            }
        } catch (InterruptedException e) {
            // Maybe say if we're done or not?
        }
    }
}

