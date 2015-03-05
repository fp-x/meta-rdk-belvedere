SUMMARY = "Custom package group for CCSP bits used in RDK-B"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-rdkb-ccsp \
"

# CCSP components used in RDK-B
RDEPENDS_packagegroup-rdkb-ccsp = "\
	ccsp-common-library \
	hal \
	ccsp-cm-agent \
	ccsp-cr \
	ccsp-dmcli \
	ccsp-lm-lite \
	ccsp-misc \
	ccsp-mta-agent \
	ccsp-p-and-m \
	ccsp-psm \
	ccsp-snmp-pa \
	ccsp-tr069-pa \
	ccsp-wecb-controller \
	ccsp-wifi-agent \
	reboot-manager \
	test-and-diagnostic \
"
                      
