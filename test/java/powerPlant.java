import org.junit.Test;
import org.junit.After;

import powerGrid.*;
import core.*;
public class powerPlant {
    
   @Test
   public void overallCycleTestModel() {
       // Instantiate all the basic assets
       TransmissionGrid grid = new TransmissionGrid("TransmissionGrid");
       GridTransmissionSwitch gridSwitch = new GridTransmissionSwitch("PowerGridSwitch");
       PowerPlant plant = new PowerPlant("PowerPlant");
       FeedWaterTank fwaterTank = new FeedWaterTank("FeedWaterTank");
       Heater heater = new Heater("Heater");
       Cooler cooler = new Cooler("Cooler");
       Turbine turbine1 = new Turbine("Turbine1");
       Generator generator1 = new Generator("Generator1");
       RotatingEquipment rotEquipment = new RotatingEquipment("RotatingEquipment");
       Fuel fuel = new Fuel("Fuel");
       Coolant coolant = new Coolant("Coolant");
       // Connect them with each other
       gridSwitch.addTransmissionGrid(grid);
       plant.addTransmissionGrid(grid);
       plant.addFeedWaterTank(fwaterTank);
       plant.addCooler(cooler);
       plant.addHeater(heater);
       cooler.addCoolantCooler(coolant);
       heater.addHeaterFuel(fuel);

       Controller hydrSysController = new Controller("HydraulicSysController");
       HydraulicControlSystem hydrCtrlSystem = new HydraulicControlSystem("HydraulicCtrlSys");
       hydrCtrlSystem.addConroller(hydrSysController);
       // Add Valves to HydraulicControlSystem
       Valve pumpValve1 = new Valve("PumpValve1");
       Valve pumpValve2 = new Valve("PumpValve2");
       Valve pumpValve3 = new Valve("PumpValve3");
       hydrCtrlSystem.addValve(pumpValve1);
       hydrCtrlSystem.addValve(pumpValve2);
       hydrCtrlSystem.addValve(pumpValve3);
       // Not complete, TODO: complete with all connected Valves

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
