package src;

import org.cloudbus.cloudsim.*;
import java.util.*;

public class Scheduler {

    public static void main(String[] args) {
        Log.printLine("Starting IWRR Load Balancer Simulation...");

        try {
            int numUsers = 1;
            Calendar calendar = Calendar.getInstance();
            boolean traceFlag = false;

            CloudSim.init(numUsers, calendar, traceFlag);

            Datacenter datacenter = createDatacenter("Datacenter_1");
            DatacenterBroker broker = new DatacenterBroker("Broker");

            List<Vm> vmlist = createVMs(broker.getId(), 5);
            List<Cloudlet> cloudletList = createCloudlets(broker.getId(), 20);

            Map<Integer, List<Cloudlet>> vmMap = new HashMap<>();
            IWRRLoadBalancer.assignCloudlets(cloudletList, vmlist, vmMap);

            broker.submitVmList(vmlist);
            broker.submitCloudletList(cloudletList);

            CloudSim.startSimulation();

            List<Cloudlet> newList = broker.getCloudletReceivedList();
            CloudSim.stopSimulation();

            Log.printLine("========== Simulation Finished ==========");
            for (Cloudlet cloudlet : newList) {
                Log.printLine("Cloudlet ID: " + cloudlet.getCloudletId() +
                        " VM ID: " + cloudlet.getVmId() +
                        " Status: " + cloudlet.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Datacenter createDatacenter(String name) throws Exception {
        List<Host> hostList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            List<Pe> peList = new ArrayList<>();
            peList.add(new Pe(0, new PeProvisionerSimple(1000)));

            hostList.add(new Host(
                    i,
                    new RamProvisionerSimple(2048),
                    new BwProvisionerSimple(10000),
                    1000000,
                    peList,
                    new VmSchedulerTimeShared(peList)
            ));
        }

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                "x86", "Linux", "Xen", hostList,
                10.0, 3.0, 0.05, 0.1, 0.1
        );

        return new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), new LinkedList<>(), 0);
    }

    private static List<Vm> createVMs(int brokerId, int numVMs) {
        List<Vm> vmlist = new ArrayList<>();

        for (int i = 0; i < numVMs; i++) {
            vmlist.add(new Vm(i, brokerId, 1000, 1, 2048, 1000, 10000, "Xen", new CloudletSchedulerTimeShared()));
        }
        return vmlist;
    }

    private static List<Cloudlet> createCloudlets(int brokerId, int numCloudlets) {
        List<Cloudlet> list = new ArrayList<>();
        UtilizationModel um = new UtilizationModelFull();

        for (int i = 0; i < numCloudlets; i++) {
            Cloudlet cl = new Cloudlet(i, 40000 + i * 1000, 1, 300, 300, um, um, um);
            cl.setUserId(brokerId);
            list.add(cl);
        }
        return list;
    }
}
