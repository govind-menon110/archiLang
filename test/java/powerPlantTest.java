import org.junit.Test;
import org.junit.After;

import powerGrid.*;
import core.*;
public class powerPlantTest {
    
   @Test
   public void overallCycleTestModel() {
       System.out.println("### " + Thread.currentThread().getStackTrace()[1].getMethodName()); // Printing the test's name
       // Instantiate some of the basic assets here
       TransmissionGrid grid = new TransmissionGrid("TransmissionGrid");
       GridTransmissionSwitch gridSwitch = new GridTransmissionSwitch("PowerGridSwitch");
       PowerPlant plant = new PowerPlant("PowerPlant");
       FeedWaterTank fwaterTank = new FeedWaterTank("FeedWaterTank");
       Fuel fuel = new Fuel("Fuel");
       AuxiliariesRotatingEquipment auxRotEquipment = new AuxiliariesRotatingEquipment("AuxRotEquipment");
       AuxiliariesNonRotatingEquipment heaterAuxNonRotEquipment = new AuxiliariesNonRotatingEquipment("HeaterAuxNonRotEquipment");
       AuxiliariesNonRotatingEquipment coolerAuxNonRotEquipment = new AuxiliariesNonRotatingEquipment("CoolerAuxNonRotEquipment");
       // Then connect them with each other
       gridSwitch.addTransmissionGridGridTransmissionSwitch(grid);
       plant.addTransmissionGridPowerPlant(grid);
       plant.addFeedWaterTankPowerPlant(fwaterTank);
       // Steam is condensed in the cooler by the coolant. The condensate is then pumped to the feedwater tank
       Coolant coolant = new Coolant("Coolant");
       Valve coolantValve = new Valve("CoolantValve");
       Cooler cooler = new Cooler("Cooler");
       Controller coolerController = new Controller("CoolerController");
       coolant.addValveCoolant(coolantValve);
       coolant.addAuxiliariesNonRotatingEquipmentCoolant(coolerAuxNonRotEquipment);
       cooler.addControllerCooler(coolerController);
       cooler.addCoolantCooler(coolant);
       cooler.addAuxiliariesNonRotatingEquipmentCooler(coolerAuxNonRotEquipment);
       plant.addCoolerPowerPlant(cooler);
       // Question: How is Cooler connected with PumpCompressor (AuxilaryRotatingEquipment)? Answer: maybe via the Auxiliaries!
       PumpCompressor coolerPump1 = new PumpCompressor("CoolerPump1");
       PumpCompressor coolerPump2 = new PumpCompressor("CoolerPump2");
       coolerPump1.addAuxiliariesNonRotatingEquipmentPumpCompressor(coolerAuxNonRotEquipment);
       coolerPump2.addAuxiliariesNonRotatingEquipmentPumpCompressor(coolerAuxNonRotEquipment);
       // Question: How is coolerPump1 and coolerPump2 connected with coolerValve? Answer: again the same?
       Valve coolerValve = new Valve("CoolerValve");
       cooler.addValveCooler(coolerValve);
       // From the tank the redundant feedwater pumps pump the water in the heater.
       PumpCompressor feedWaterPump1 = new PumpCompressor("FeedWaterPump1");
       PumpCompressor feedWaterPump2 = new PumpCompressor("FeedWaterPump2");
       PumpCompressor feedWaterPump3 = new PumpCompressor("FeedWaterPump3");
       Valve waterPumpValve1 = new Valve("WaterPumpValve1");
       Valve waterPumpValve2 = new Valve("WaterPumpValve2");
       Valve waterPumpValve3 = new Valve("WaterPumpValve3");
       feedWaterPump1.addAuxiliariesNonRotatingEquipmentPumpCompressor(coolerAuxNonRotEquipment); // This is corrected
       feedWaterPump2.addAuxiliariesNonRotatingEquipmentPumpCompressor(coolerAuxNonRotEquipment);
       feedWaterPump3.addAuxiliariesNonRotatingEquipmentPumpCompressor(coolerAuxNonRotEquipment);
       // Heater (to High pressure turbine)
       Heater heater1 = new Heater("Heater1");
       heater1.addFuelHeater(fuel);
       heater1.addAuxiliariesNonRotatingEquipmentHeater(heaterAuxNonRotEquipment);
       plant.addHeaterPowerPlant(heater1);
       Valve heaterValve1 = new Valve("HeaterValve1");
       Valve heaterValve2 = new Valve("HeaterValve2");
       // Heater2 (to intermediate pressure turbine)
       Heater heater2 = new Heater("Heater2");
       heater2.addFuelHeater(fuel);
       heater2.addAuxiliariesNonRotatingEquipmentHeater(heaterAuxNonRotEquipment);
       plant.addHeaterPowerPlant(heater2);
       Valve heater2Valve1 = new Valve("Heater2Valve1");
       Valve heater2Valve2 = new Valve("Heater2Valve2");
       // Turbines
       Turbine highPressTurbine = new Turbine("HighPressureTurbine");
       Turbine intermediatePressTurbine = new Turbine("IntermediatePressureTurbine");
       Turbine lowPressTurbine = new Turbine("LowPressureTurbine");
       highPressTurbine.addAuxiliariesRotatingEquipmentRotatingEquipment(auxRotEquipment);
       intermediatePressTurbine.addAuxiliariesRotatingEquipmentRotatingEquipment(auxRotEquipment);
       lowPressTurbine.addAuxiliariesRotatingEquipmentRotatingEquipment(auxRotEquipment);
       // QUESTION: How are turbines connected to the generator? How is then the generator connected to the transmission grid?
       // All turbines are connected to the generator by a solid shaft
       Controller loadController = new Controller("LoadController");
       Generator generator = new Generator("Generator");
       generator.addAuxiliariesRotatingEquipmentRotatingEquipment(auxRotEquipment);
       generator.addControllerGenerator(loadController);
       // Measurements
       Measurements turbineAndGenMeasurements = new Measurements("TurbineAndGeneratorMeasurements");
       highPressTurbine.addMeasurementsTurbine(turbineAndGenMeasurements);
       intermediatePressTurbine.addMeasurementsTurbine(turbineAndGenMeasurements);
       lowPressTurbine.addMeasurementsTurbine(turbineAndGenMeasurements);
       generator.addMeasurementsRotatingEquipment(turbineAndGenMeasurements);
       Measurements onlyGenMeasurements = new Measurements("OnlyGeneratorMeasurements");
       generator.addMeasurementsRotatingEquipment(onlyGenMeasurements);
       Measurements transmissionGridMeasurements = new Measurements("transmissionGridMeasurements");
       grid.addMeasurementsTransmissionGrid(transmissionGridMeasurements);
       // Hydraulic Control System
       Controller hydrSysController = new Controller("HydraulicSysController");
       HydraulicControlSystem hydrCtrlSystem = new HydraulicControlSystem("HydraulicCtrlSys");
       hydrCtrlSystem.addControllerHydraulicControlSystem(hydrSysController);
       hydrCtrlSystem.addControllerAuxiliaries(hydrSysController);
       // Add Valves to HydraulicControlSystem
       hydrCtrlSystem.addValveHydraulicControlSystem(waterPumpValve1);
       hydrCtrlSystem.addValveHydraulicControlSystem(waterPumpValve2);
       hydrCtrlSystem.addValveHydraulicControlSystem(waterPumpValve3);
       hydrCtrlSystem.addValveHydraulicControlSystem(heaterValve1);
       hydrCtrlSystem.addValveHydraulicControlSystem(heaterValve2);
       hydrCtrlSystem.addValveHydraulicControlSystem(heater2Valve1);
       hydrCtrlSystem.addValveHydraulicControlSystem(heater2Valve2);
       hydrCtrlSystem.addValveHydraulicControlSystem(coolantValve);
       hydrCtrlSystem.addValveHydraulicControlSystem(coolerValve);
       // Creating the attacker!
       Attacker attacker = new Attacker();
       // TODO Entry point
       //attacker.addAttackPoint(TODO);
       attacker.attack();
       // TODO Assertions
       plant.powerOutage.assertUncompromised();
       plant.damage.assertUncompromised();
    }
   
    @After
    public void deleteModel() {
        Asset.allAssets.clear();
        AttackStep.allAttackSteps.clear();
        Defense.allDefenses.clear();
    }
    
}
