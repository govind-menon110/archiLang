import org.junit.Test;
import org.junit.After;

import powerGrid.*;
import core.*;
public class powerPlantTest {
    
   @Test
   public void overallCycleTestModelWithIT() {
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
       plant.addGeneratorPowerPlant(generator);
       //UPDATE: For the IT test case I needed to add the following connections! Which seem logical to me!
       loadController.addRotatingEquipmentController(highPressTurbine);
       loadController.addRotatingEquipmentController(intermediatePressTurbine);
       loadController.addRotatingEquipmentController(lowPressTurbine);
       highPressTurbine.addGeneratorRotatingEquipment(generator);
       intermediatePressTurbine.addGeneratorRotatingEquipment(generator);
       lowPressTurbine.addGeneratorRotatingEquipment(generator);
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
       // Now model the IT infrastructure of the plant
       EngineeringStation engStation = new EngineeringStation("EngineeringStation");
       DCS dcsNetwork = new DCS("DCSnetwork");
       SIS sisNetwork = new SIS("SISnetwork");
       IDPS idps = new IDPS("EngStationIDPS");
       PLC hydraulicPLC = new PLC("HydaraulicsPLC");
       PLC generationPLC = new PLC("GenerationPLC");
       engStation.addDCSEngineeringStation(dcsNetwork);
       engStation.addSISEngineeringStation(sisNetwork);
       engStation.addIDPSEngineeringStation(idps);
       engStation.addPLCEngineeringStation(hydraulicPLC);
       engStation.addPLCEngineeringStation(generationPLC);
       // Connect also the PLC to a network, here I choose DCS
       dcsNetwork.addPLCDCS(hydraulicPLC);
       dcsNetwork.addPLCDCS(generationPLC);
       // And now connect PLCs to the controllers
       hydraulicPLC.addControllerPLC(hydrSysController);
       generationPLC.addControllerPLC(loadController);

       // Creating the attacker!
       Attacker attacker = new Attacker();
       // Attacker has compromissed Engineering Station and has access
       System.out.println("Attacker's entry point: EngineeringStation.access");
       attacker.addAttackPoint(engStation.access);
       attacker.attack();
       // Assertions
       engStation.access.assertCompromisedInstantaneously();
       hydraulicPLC.changeFirmware.assertCompromisedInstantaneously();
       generationPLC.changeFirmware.assertCompromisedInstantaneously();
       hydrSysController.noHydraulicControlOfValves.assertCompromisedInstantaneously();
       hydrCtrlSystem.manipulateValves.assertCompromisedInstantaneously();
       heaterValve1.closeValves.assertCompromisedInstantaneously();
       heater2Valve1.closeValves.assertCompromisedInstantaneously();
       highPressTurbine.stopRotation.assertCompromisedInstantaneouslyFrom(generator.stopConnectedTurbines);
       highPressTurbine.systemFailure.assertUncompromised();
       intermediatePressTurbine.stopRotation.assertCompromisedInstantaneouslyFrom(generator.stopConnectedTurbines);
       intermediatePressTurbine.systemFailure.assertUncompromised();
       lowPressTurbine.stopRotation.assertCompromisedInstantaneously();
       lowPressTurbine.systemFailure.assertCompromisedInstantaneously();
       generator.stopElectricityProduction.assertCompromisedInstantaneously();
       // This is the ultimate goal of an attacker!
       plant.damage.assertUncompromised();
       plant.powerOutage.assertCompromisedInstantaneously();
    }

   @Test
   public void fuelAttackTestCase() {
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
       Heater highPressureHeater = new Heater("Heater1");
       highPressureHeater.addFuelHeater(fuel);
       highPressureHeater.addAuxiliariesNonRotatingEquipmentHeater(heaterAuxNonRotEquipment);
       plant.addHeaterPowerPlant(highPressureHeater);
       Valve heaterValve1 = new Valve("HeaterValve1");
       Valve heaterValve2 = new Valve("HeaterValve2");
       // Heater2 (to intermediate pressure turbine)
       Heater intermediatePressureHeater = new Heater("Heater2");
       intermediatePressureHeater.addFuelHeater(fuel);
       intermediatePressureHeater.addAuxiliariesNonRotatingEquipmentHeater(heaterAuxNonRotEquipment);
       plant.addHeaterPowerPlant(intermediatePressureHeater);
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
       plant.addGeneratorPowerPlant(generator);
       //UPDATE: For the IT test case I needed to add the following connections!
       loadController.addRotatingEquipmentController(highPressTurbine);
       loadController.addRotatingEquipmentController(intermediatePressTurbine);
       loadController.addRotatingEquipmentController(lowPressTurbine);
       highPressTurbine.addGeneratorRotatingEquipment(generator);
       intermediatePressTurbine.addGeneratorRotatingEquipment(generator);
       lowPressTurbine.addGeneratorRotatingEquipment(generator);
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
       //UPDATE: For this test case I needed to add the following connections! Which do seem logical enough to me!
       highPressureHeater.addRotatingEquipmentHeater(highPressTurbine);
       intermediatePressureHeater.addRotatingEquipmentHeater(intermediatePressTurbine);

       // Creating the attacker!
       Attacker attacker = new Attacker();
       System.out.println("Attacker's entry point: fuel.shutDownCoalMills");
       attacker.addAttackPoint(fuel.shutDownCoalMills);
       attacker.attack();
       // Assertions
       highPressureHeater.noHeatInput.assertCompromisedInstantaneously();
       intermediatePressureHeater.noHeatInput.assertCompromisedInstantaneously();
       highPressTurbine.stopRotation.assertCompromisedInstantaneously();
       intermediatePressTurbine.stopRotation.assertCompromisedInstantaneously();
       lowPressTurbine.stopRotation.assertCompromisedInstantaneouslyFrom(generator.stopConnectedTurbines);
       generator.stopElectricityProduction.assertCompromisedInstantaneously();
       plant.damage.assertUncompromised();
       // This is the goal of the attacker
       plant.powerOutage.assertCompromisedInstantaneously();
    }

    @Test
   public void lubricationAttackTestCase() {
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
       Heater highPressureHeater = new Heater("Heater1");
       highPressureHeater.addFuelHeater(fuel);
       highPressureHeater.addAuxiliariesNonRotatingEquipmentHeater(heaterAuxNonRotEquipment);
       plant.addHeaterPowerPlant(highPressureHeater);
       Valve heaterValve1 = new Valve("HeaterValve1");
       Valve heaterValve2 = new Valve("HeaterValve2");
       // Heater2 (to intermediate pressure turbine)
       Heater intermediatePressureHeater = new Heater("Heater2");
       intermediatePressureHeater.addFuelHeater(fuel);
       intermediatePressureHeater.addAuxiliariesNonRotatingEquipmentHeater(heaterAuxNonRotEquipment);
       plant.addHeaterPowerPlant(intermediatePressureHeater);
       Valve heater2Valve1 = new Valve("Heater2Valve1");
       Valve heater2Valve2 = new Valve("Heater2Valve2");
       // Turbines
       Turbine highPressTurbine = new Turbine("HighPressureTurbine");
       Turbine intermediatePressTurbine = new Turbine("IntermediatePressureTurbine");
       Turbine lowPressTurbine = new Turbine("LowPressureTurbine");
       highPressTurbine.addAuxiliariesRotatingEquipmentRotatingEquipment(auxRotEquipment);
       intermediatePressTurbine.addAuxiliariesRotatingEquipmentRotatingEquipment(auxRotEquipment);
       lowPressTurbine.addAuxiliariesRotatingEquipmentRotatingEquipment(auxRotEquipment);
       // Connect the heaters with the turbines
       highPressureHeater.addRotatingEquipmentHeater(highPressTurbine);
       intermediatePressureHeater.addRotatingEquipmentHeater(intermediatePressTurbine);
       // QUESTION: How are turbines connected to the generator? How is then the generator connected to the transmission grid?
       // All turbines are connected to the generator by a solid shaft
       Controller loadController = new Controller("LoadController");
       Generator generator = new Generator("Generator");
       generator.addAuxiliariesRotatingEquipmentRotatingEquipment(auxRotEquipment);
       generator.addControllerGenerator(loadController);
       plant.addGeneratorPowerPlant(generator);
       //UPDATE: For the IT test case I needed to add the following connections!
       loadController.addRotatingEquipmentController(highPressTurbine);
       loadController.addRotatingEquipmentController(intermediatePressTurbine);
       loadController.addRotatingEquipmentController(lowPressTurbine);
       highPressTurbine.addGeneratorRotatingEquipment(generator);
       intermediatePressTurbine.addGeneratorRotatingEquipment(generator);
       lowPressTurbine.addGeneratorRotatingEquipment(generator);
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
       // For this test case to work I added the lubrication asset
       Lubrication lubrication = new Lubrication("Lubrication");
       plant.addLubricationPowerPlant(lubrication);
       // And connect it with
       lubrication.addRotatingEquipmentLubrication(highPressTurbine);
       lubrication.addRotatingEquipmentLubrication(intermediatePressTurbine);
       lubrication.addRotatingEquipmentLubrication(lowPressTurbine);

       
       // Creating the attacker!
       Attacker attacker = new Attacker();
       System.out.println("Attacker's entry point: lubrication.reducedFlow");
       attacker.addAttackPoint(lubrication.reducedFlow);
       attacker.attack();
       // Assertions
       highPressTurbine.systemFailure.assertCompromisedInstantaneously();
       intermediatePressTurbine.systemFailure.assertCompromisedInstantaneously();
       lowPressTurbine.systemFailure.assertCompromisedInstantaneously();
       // It may also lead to damage
       highPressTurbine.damageEquipment.assertCompromisedInstantaneously();
       intermediatePressTurbine.damageEquipment.assertCompromisedInstantaneously();
       lowPressTurbine.damageEquipment.assertCompromisedInstantaneously();
       // Those are achieved after I created the connection systemFailure -> stopRotation
       highPressTurbine.stopRotation.assertCompromisedInstantaneously();
       intermediatePressTurbine.stopRotation.assertCompromisedInstantaneously();
       lowPressTurbine.stopRotation.assertCompromisedInstantaneously();
       generator.stopElectricityProduction.assertCompromisedInstantaneously();
       // Final target
       plant.damage.assertUncompromised();
       plant.powerOutage.assertCompromisedInstantaneously();
    }
   
    @After
    public void deleteModel() {
        Asset.allAssets.clear();
        AttackStep.allAttackSteps.clear();
        Defense.allDefenses.clear();
    }
    
}
