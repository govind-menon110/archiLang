import org.junit.Test;
import org.apache.commons.lang3.Validate;
import org.junit.After;

import powerGrid.*;
import core.*;
public class powerPlant {
    
   @Test
   public void overallCycleTestModel() {
       // Instantiate some of the basic assets here
       TransmissionGrid grid = new TransmissionGrid("TransmissionGrid");
       GridTransmissionSwitch gridSwitch = new GridTransmissionSwitch("PowerGridSwitch");
       PowerPlant plant = new PowerPlant("PowerPlant");
       FeedWaterTank fwaterTank = new FeedWaterTank("FeedWaterTank");
       //RotatingEquipment rotEquipment = new RotatingEquipment("RotatingEquipment");
       Fuel fuel = new Fuel("Fuel");
       // Then connect them with each other
       gridSwitch.addTransmissionGrid(grid);
       plant.addTransmissionGrid(grid);
       plant.addFeedWaterTank(fwaterTank);
       // Steam is condensed in the cooler by the coolant. The condensate is then pumped to the feedwater tank
       Coolant coolant = new Coolant("Coolant");
       Valve coolantValve = new Valve("CoolantValve");
       Cooler cooler = new Cooler("Cooler");
       coolant.addValveCoolant(coolantValve);
       cooler.addCoolantCooler(coolant);
       plant.addCooler(cooler);
       // QUESTION: How is Cooler connected with PumpCompressor (AuxilaryRotatingEquipment)???
       PumpCompressor coolerPump1 = new PumpCompressor("CoolerPump1");
       PumpCompressor coolerPump2 = new PumpCompressor("CoolerPump2");
       // QUESTION: How is coolerPump1 and coolerPump2 connected with coolerValve???

       Valve coolerValve = new Valve("CoolerValve");
       cooler.addValveCooler(coolerValve);
       // From the tank the redundant feedwater pumps pump the water in the heater.
       PumpCompressor feedWaterPump1 = new PumpCompressor("FeedWaterPump1");
       PumpCompressor feedWaterPump2 = new PumpCompressor("FeedWaterPump2");
       PumpCompressor feedWaterPump3 = new PumpCompressor("FeedWaterPump3");
       Valve waterPumpValve1 = new Valve("WaterPumpValve1");
       Valve waterPumpValve2 = new Valve("WaterPumpValve2");
       Valve waterPumpValve3 = new Valve("WaterPumpValve3");
       // Heater (to High pressure turbine)
       Heater heater1 = new Heater("Heater1");
       heater1.addHeaterFuel(fuel);
       plant.addHeater(heater1);
       Valve heaterValve1 = Valve("HeaterValve1");
       Valve heaterValve2 = Valve("HeaterValve2");
       // Heater2 (to intermediate pressure turbine)
       Heater heater2 = new Heater("Heater2");
       heater2.addHeaterFuel(fuel);
       plant.addHeater(heater2);
       Valve heater2Valve1 = Valve("Heater2Valve1");
       Valve heater2Valve2 = Valve("Heater2Valve2");
       // Turbines
       Turbine highPressTurbine = new Turbine("HighPressureTurbine");
       Turbine IntermediatePressTurbine = new Turbine("IntermediatePressureTurbine");
       Turbine lowPressTurbine = new Turbine("LowPressureTurbine");
       // QUESTION: How are turbines connected to the generator? How is then the generator connected to the transmission grid?
       // All turbines are connected to the generator by a solid shaft
       Generator generator = new Generator("Generator");
       // Hydraulic Control System
       Controller hydrSysController = new Controller("HydraulicSysController");
       HydraulicControlSystem hydrCtrlSystem = new HydraulicControlSystem("HydraulicCtrlSys");
       hydrCtrlSystem.addConroller(hydrSysController);
       // Add Valves to HydraulicControlSystem
       hydrCtrlSystem.addValve(waterPumpValve1);
       hydrCtrlSystem.addValve(waterPumpValve2);
       hydrCtrlSystem.addValve(waterPumpValve3);
       hydrCtrlSystem.addValve(heaterValve1);
       hydrCtrlSystem.addValve(heaterValve2);
       hydrCtrlSystem.addValve(heater2Valve1);
       hydrCtrlSystem.addValve(heater2Valve2);
       hydrCtrlSystem.addValve(coolantValve);
       hydrCtrlSystem.addValve(coolerValve);
       
       Attacker attacker = new Attacker();
       // TODO Entry point
       //attacker.addAttackPoint(TODO);
       attacker.attack();
       
       // TODO Assertions
    }
   
    @After
    public void deleteModel() {
        Asset.allAssets.clear();
        AttackStep.allAttackSteps.clear();
        Defense.allDefenses.clear();
    }
    
}
