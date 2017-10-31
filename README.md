# BEACON-Broker-Elasticity-Manager

The BEACON-Broker-Elasticity-Manager [BB_ELA], is a component designed to manage the elasticity policy that have to be applicated on VMs deployed via BB. 
The Policy applied to a deployed stack and the parameters used by BB_ELA are provide at the Manifest instantiation time, as resource of the Beacon Service Manifest; in particular are required by the systems the following elements:

i) Policy types: actually is present only SunLight Policy

ii) Granularity Check: interval time between two different condition checks, expressed in millis

ii) Threshold: hour of the day in which a VM have to be "migrate among clouds"

This component interacts with other modules of the Beacon Architecture (BB) via REST WS and, at the same time, expose WS for BB.

To instantiate it, the deployer have to create a directory in the path: "/home/beacon/beaconConf/" with the configuration files listed below according with the template provided in the template folder:

i) configuration_bigDataPlugin.xml

i) configuration_SunLightPolicy.xml

It is tested on Tomcat 8.0.27
