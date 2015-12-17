

export LD_LIBRARY_PATH=$PWD:.:$PWD/lib:/lib:/usr/lib:$LD_LIBRARY_PATH
export DBUS_SYSTEM_BUS_ADDRESS=unix:path=/var/run/dbus/system_bus_socket

# Start coredump
if [ -f "$PWD/core_compr" ]; then
        if ! [ -e "/var/core" ]; then
                mkdir -p /var/core/
        fi
        echo "|$PWD/core_compr /var/core %p %e" >/proc/sys/kernel/core_pattern
        ulimit -c unlimited

        ./core_report.sh &
fi

# Start Config Recover
echo "Starting Config Recover Daemon ..."
conf_rec &

cp ccsp_msg.cfg /tmp

Subsys="eRT."

echo "Elected subsystem is $Subsys"

    echo "CcspCrSsp -subsys $Subsys"
    CcspCrSsp -subsys $Subsys
    echo "PsmSsp -subsys $Subsys"
    PsmSsp -subsys $Subsys
if [ -e ./pam ]; then
    sleep 1
	cd pam
		echo "CcspPandMSsp -subsys $Subsys"
		CcspPandMSsp -subsys $Subsys

    # double background to detach the script from the tty
	((sh ./email_notification_monitor.sh 12 &) &)
	cd ..
fi

#sleep 3
if [ -e ./rm ]; then
cd rm
    echo "CcspRmSsp -subsys $Subsys"
	CcspRmSsp -subsys $Subsys
cd ..
fi


if [ "x"$1 = "xpam" ] || [ "x"$2 = "xpam" ]; then
  exit 0
fi


# Tr069Pa, as well as SecureSoftwareDownload and FirmwareUpgrade

if [ -e ./wecb ]; then
    sleep 5
    cd wecb
         echo "CcspWecbController -subsys $Subsys"
        CcspWecbController -subsys $Subsys 
    cd ..
fi

if [ -e ./tr069pa ]; then
    sleep 30
    cd tr069pa
        echo "CcspTr069PaSsp -subsys $Subsys"
        CcspTr069PaSsp -subsys $Subsys
    cd ..
fi

if [ -e ./tr069pa ]; then
    # add firewall rule to allow incoming packet for port 7547
    sysevent setunique GeneralPurposeFirewallRule " -A INPUT -i erouter0 -p tcp --dport=7547 -j ACCEPT "
fi

if [ -e ./cm ]; then
    sleep 5
    cd cm
        echo "CcspCMAgentSsp -subsys $Subsys"
        CcspCMAgentSsp -subsys $Subsys 
    cd ..
fi

if [ -e ./mta ]; then
    sleep 30
    cd mta
        echo "CcspMtaAgentSsp -subsys $Subsys"
        CcspMtaAgentSsp -subsys $Subsys 
    cd ..
fi

if [ -e ./ssd ]; then
    cd ssd
    sleep 1
        echo "CcspSsdSsp -subsys $Subsys"
        CcspSsdSsp -subsys $Subsys
    cd ..
fi

if [ -e ./fu ]; then
    cd fu
    sleep 1
        echo "CcspFuSsp -subsys $Subsys"
        CcspFuSsp -subsys $Subsys
    cd ..
fi

if [ -e ./tad ]; then
	cd tad
	#delay TaD in order to reduce CPU overload and make PAM ready early
	sleep 3
        echo "CcspTandDSsp -subsys $Subsys"
		CcspTandDSsp -subsys $Subsys
	cd ..
fi

sleep 1

if [ -e ./ccspRecoveryManager ]; then
        echo "ccspRecoveryManager -subsys $Subsys &"
        ccspRecoveryManager -subsys $Subsys &
fi


if [ -e ./lm ]; then
    cd lm
    sleep 5
    CcspLMLite &
fi

