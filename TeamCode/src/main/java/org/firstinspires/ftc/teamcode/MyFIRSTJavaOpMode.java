package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RunnableMotor;

@TeleOp
public class MyFIRSTJavaOpMode extends LinearOpMode {
    private DcMotor motorTest;
    private Servo servoTest;
    RunnableMotor threadMotor;
    Thread threadGuy;

    @Override
    public void runOpMode() {
        motorTest = hardwareMap.get(DcMotor.class, "motorTest");
        //servoTest = hardwareMap.get(Servo.class, "servoTest");
        threadMotor = new RunnableMotor(motorTest);
        threadGuy = new Thread(threadMotor);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        threadGuy.start(); //start my motor interface
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            boolean up = gamepad1.right_bumper;
            boolean down = gamepad1.left_bumper;
            float speed = gamepad1.left_stick_y;
            threadMotor.setPower(speed);
        }
    }
}
