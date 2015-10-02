SUMMARY = "CCSP CcspSnmpPa component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspSnmpPa"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library net-snmp openssl"

SRC_URI = "\
    git://github.com/belvedere-yocto/CcspSnmpPa.git;protocol=git;branch=${CCSP_GIT_BRANCH} \
    "

SRCREV = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools

CFLAGS_append = " \
    -I=${includedir}/dbus-1.0 \
    -I=${libdir}/dbus-1.0/include \
    -I=${includedir}/ccsp \
    "

CFLAGS_append_qemux86 += -D_COSA_SIM_

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

FILES_${PN} = " \
    ${bindir}/snmp_subagent \
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
    ${libdir}/libsnmp_plugin.so* \
    ${libdir}/libsnmp_custom.so* \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/snmp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
