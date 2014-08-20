SUMMARY = "CCSP CcspSnmpPa component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspSnmpPa"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "CcspCommonLibrary net-snmp openssl"

SRC_URI = "\
git://github.com/ccsp-yocto/CcspSnmpPa.git;protocol=git;branch=daisy;rev=daisy \
    "

SRC_URI[md5sum] = "d338d61e396d5038025339bf5bdb169d"
SRC_URI[sha256sum] = "e6f5a166c0e0f775dc09261f992abb561b781f4a992ef2c0081edcf6b265df24"

S = "${WORKDIR}/git"

inherit autotools

PACKAGECONFIG ??= "CcspCommonLibrary net-snmp openssl"

export INCLUDES = " -I${STAGING_DIR_HOST}/usr/include/dbus-1.0 \
 -I${STAGING_DIR_HOST}/usr/lib/dbus-1.0/include \
 -I${STAGING_DIR_HOST}/usr/lib/net-snmp \
 -I${STAGING_DIR_HOST}/usr/include/ccsp \
"

export LDFLAGS = " -L${STAGING_DIR_HOST}/usr/lib \
 -ldbus-1 \
 -lnetsnmp \
 -lssl \
"

FILES_${PN} = " \
    ${WORKDIR}/config/snmpd.conf \
    ${WORKDIR}/scripts/run_snmpd.sh \
    ${WORKDIR}/scripts/run_subagent.sh \
    ${WORKDIR}/Mib2DmMapping/Ccsp_CLAB-WIFI-MIB.xml \
    ${WORKDIR}/Mib2DmMapping/CcspMibList.xml \
    ${WORKDIR}/Mib2DmMapping/Ccsp_SA-RG-MIB-DeviceMgmt.xml \
    ${WORKDIR}/Mib2DmMapping/Ccsp_SA-RG-MIB-Hotspot.xml \
    ${WORKDIR}/Mib2DmMapping/Ccsp_SA-RG-MIB-Lan-Dhcp.xml \
    ${WORKDIR}/Mib2DmMapping/Ccsp_SA-RG-MIB-MoCA.xml \
    ${WORKDIR}/Mib2DmMapping/Ccsp_SA-RG-MIB-NTP.xml \
    ${WORKDIR}/Mib2DmMapping/Ccsp_SA-RG-MIB-routing.xml \
    ${WORKDIR}/Mib2DmMapping/Ccsp_SA-RG-MIB-Tr069Pa.xml \
    ${WORKDIR}/Mib2DmMapping/Ccsp_SA-RG-MIB-Vlan.xml \
    ${WORKDIR}/Mib2DmMapping/Ccsp_SA-RG-MIB-WanDns.xml \
    ${WORKDIR}/Mib2DmMapping/Ccsp_SA-RG-WiFi-MIB.xml \
"

do_install_append () {
    # Config files and scripts
    mkdir -p ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/config/snmpd.conf -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/scripts/run_snmpd.sh -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/scripts/run_subagent.sh -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/Mib2DmMapping/Ccsp_CLAB-WIFI-MIB.xml -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/Mib2DmMapping/CcspMibList.xml -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/Mib2DmMapping/Ccsp_SA-RG-MIB-DeviceMgmt.xml -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/Mib2DmMapping/Ccsp_SA-RG-MIB-Hotspot.xml -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/Mib2DmMapping/Ccsp_SA-RG-MIB-Lan-Dhcp.xml -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/Mib2DmMapping/Ccsp_SA-RG-MIB-MoCA.xml -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/Mib2DmMapping/Ccsp_SA-RG-MIB-NTP.xml -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/Mib2DmMapping/Ccsp_SA-RG-MIB-routing.xml -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/Mib2DmMapping/Ccsp_SA-RG-MIB-Tr069Pa.xml -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/Mib2DmMapping/Ccsp_SA-RG-MIB-Vlan.xml -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/Mib2DmMapping/Ccsp_SA-RG-MIB-WanDns.xml -t ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/Mib2DmMapping/Ccsp_SA-RG-WiFi-MIB.xml -t ${D}/usr/ccsp/snmp
}

