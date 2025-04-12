package src;

import org.cloudbus.cloudsim.*;
import java.util.*;

public class IWRRLoadBalancer {

    public static Vm findBestVM(List<Vm> vmList, Map<Integer, List<Cloudlet>> vmCloudletsMap) {
        Vm selectedVm = null;
        double minLoad = Double.MAX_VALUE;

        for (Vm vm : vmList) {
            List<Cloudlet> assigned = vmCloudletsMap.getOrDefault(vm.getId(), new ArrayList<>());
            double load = 0;
            for (Cloudlet cl : assigned) {
                load += cl.getCloudletLength();
            }

            if (load < minLoad) {
                minLoad = load;
                selectedVm = vm;
            }
        }
        return selectedVm;
    }

    public static void assignCloudlets(List<Cloudlet> cloudletList, List<Vm> vmList, Map<Integer, List<Cloudlet>> vmCloudletsMap) {
        for (Cloudlet cloudlet : cloudletList) {
            Vm bestVm = findBestVM(vmList, vmCloudletsMap);
            cloudlet.setVmId(bestVm.getId());
            vmCloudletsMap.computeIfAbsent(bestVm.getId(), k -> new ArrayList<>()).add(cloudlet);
        }
    }
}
