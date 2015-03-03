SUMMARY = "CCSP CcspSnmpPa component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspSnmpPa"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library net-snmp openssl"

SRC_URI = "\
    git://github.com/ccsp-yocto/CcspSnmpPa.git;protocol=git;branch=daisy;rev=daisy \
    "

SRC_URI[md5sum] = "d338d61e396d5038025339bf5bdb169d"
SRC_URI[sha256sum] = "e6f5a166c0e0f775dc09261f992abb561b781f4a992ef2c0081edcf6b265df24"

S = "${WORKDIR}/git"

inherit autotools

CFLAGS_append = " \
    -I=${includedir}/dbus-1.0 \
    -I=${libdir}/dbus-1.0/include \
    -I=${includedir}/ccsp \
    "

LDFLAGS_append = " \
    -ldbus-1 \
    "

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/snmp
    install -m 644 ${WORKDIR}/git/config/snmpd.conf -t ${D}/usr/ccsp/snmp
    install -m 777 ${WORKDIR}/git/scripts/run_snmpd.sh -t ${D}/usr/ccsp/snmp
    install -m 777 ${WORKDIR}/git/scripts/run_subagent.sh -t ${D}/usr/ccsp/snmp
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

PACKAGES += "${PN}-ccsp"

FILES_${PN}-ccsp = " \
    ${prefix}/ccsp/snmp/snmpd.conf \
    ${prefix}/ccsp/snmp/run_snmpd.sh \
    ${prefix}/ccsp/snmp/run_subagent.sh \
    ${prefix}/ccsp/snmp/Ccsp_CLAB-WIFI-MIB.xml \
    ${prefix}/ccsp/snmp/CcspMibList.xml \
    ${prefix}/ccsp/snmp/Ccsp_SA-RG-MIB-DeviceMgmt.xml \
    ${prefix}/ccsp/snmp/Ccsp_SA-RG-MIB-Hotspot.xml \
    ${prefix}/ccsp/snmp/Ccsp_SA-RG-MIB-Lan-Dhcp.xml \
    ${prefix}/ccsp/snmp/Ccsp_SA-RG-MIB-MoCA.xml \
    ${prefix}/ccsp/snmp/Ccsp_SA-RG-MIB-NTP.xml \
    ${prefix}/ccsp/snmp/Ccsp_SA-RG-MIB-routing.xml \
    ${prefix}/ccsp/snmp/Ccsp_SA-RG-MIB-Tr069Pa.xml \
    ${prefix}/ccsp/snmp/Ccsp_SA-RG-MIB-Vlan.xml \
    ${prefix}/ccsp/snmp/Ccsp_SA-RG-MIB-WanDns.xml \
    ${prefix}/ccsp/snmp/Ccsp_SA-RG-WiFi-MIB.xml \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/snmp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
