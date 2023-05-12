package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {
  
  Joystick stick1 = new Joystick(0);
  Joystick stick2 = new Joystick(1);
  PWMVictorSPX derecha1 = new PWMVictorSPX(3);
  PWMVictorSPX derecha2 = new PWMVictorSPX(2);
  PWMVictorSPX izquierda1 = new PWMVictorSPX(1);
  PWMVictorSPX izquierda2 = new PWMVictorSPX(0);
  PWMVictorSPX C1 = new PWMVictorSPX(6);
  PWMVictorSPX C2 = new PWMVictorSPX(5);
  
  SpeedControllerGroup LGroup = new SpeedControllerGroup(izquierda1, izquierda2);
  SpeedControllerGroup RGroup = new SpeedControllerGroup(derecha1, derecha2);
  DifferentialDrive MDrive = new DifferentialDrive(LGroup, RGroup);
  Compressor compressor = new Compressor(0);
  Solenoid velo = new Solenoid(7);
  DoubleSolenoid Pcargo = new DoubleSolenoid (0,3);
  Solenoid Phatch = new Solenoid(4);
  Solenoid vent1 = new Solenoid(5);
  Solenoid vent2 = new Solenoid(6);
  
  @Override
  public void robotInit() {
  edu.wpi.first.cameraserver.CameraServer.getInstance().startAutomaticCapture(0);
  edu.wpi.first.cameraserver.CameraServer.getInstance().startAutomaticCapture(1);
  compressor.setClosedLoopControl(true);
  
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    
  }

  @Override
  public void autonomousPeriodic() {
    
      //chasis con reversa
  if (stick1.getRawButton(6)) {
    MDrive.tankDrive(stick1.getRawAxis(1), stick1.getRawAxis(5));
  } else {
    MDrive.tankDrive(-stick1.getRawAxis(1), -stick1.getRawAxis(5));
    }
  //velocidades
  if (stick1.getRawButton(1)) {
    velo.set(true);
  }
  else if (stick1.getRawButton(2)) {
    velo.set(false);
  }
  //ventosa
  if (stick2.getRawButton(3)) {
    vent1.set(true);
    } else {
      vent1.set(false);
    }
  if (stick2.getRawButton(4)) {
    vent2.set(true);
    } else {
      vent2.set(false);
    }
  //pistones
  if (stick2.getRawButton(1)) {
    Phatch.set(true);
    }
  else if (stick2.getRawButton(2)) {
      Phatch.set(false);
    }
  if (stick2.getRawButton(8)) {
    Pcargo.set(DoubleSolenoid.Value.kForward);
    }
  if (stick2.getRawButton(7)) {
    Pcargo.set(DoubleSolenoid.Value.kReverse); 
  } 
  //cargo
  if (stick2.getRawButton(5)) {
    C1.set(-0.4);
    C2.set(0.4);
    }
  else if (stick2.getRawButton(6)) {
    C1.set(0.6);
    C2.set(-0.6);
    } else {
      C1.set(0.0);
      C2.set(0.0);
    } 
  }

  @Override
  public void teleopPeriodic() {
  //chasis con reversa
  if (stick1.getRawButton(6)) { //antes 6, ahora Y"4"
    MDrive.tankDrive(stick1.getRawAxis(1), stick1.getRawAxis(5));
  } else {
    MDrive.tankDrive(-stick1.getRawAxis(1), -stick1.getRawAxis(5));
    }
  //velocidades
  if (stick1.getRawButton(1)) { //antes 1, ahora LB "5"
    velo.set(true);
  }
  else if (stick1.getRawButton(2)) { // antes 2, ahora RB "6"
    velo.set(false);
  }
  //ventosa
  if (stick2.getRawButton(4)) {
    vent1.set(true);
    } else {
      vent1.set(false);
    }
  if (stick2.getRawButton(3)) {
    vent2.set(true);
    } else {
      vent2.set(false);
    }
  //pistones
  if (stick2.getRawButton(8)) {
    Phatch.set(true);
    }
  else if (stick2.getRawButton(7)) {
      Phatch.set(false);
    }
  if (stick2.getRawButton(1)) {//le cambie de 8 a 1
   Pcargo.set(Value.kForward);
    }
  else if (stick2.getRawButton(2)) {//le cambie de 7 a 2 
    Pcargo.set(Value.kReverse); 
    } 
  //cargo
  if (stick2.getRawButton(5)) {
    C1.set(-0.4);
    C2.set(0.4);
    }
  else if (stick2.getRawButton(6)) {
    C1.set(0.6);
    C2.set(-0.6);
    } else {
      C1.set(0.0);
      C2.set(0.0);
    } 
  }

  @Override
  public void testPeriodic() {

  }
}
